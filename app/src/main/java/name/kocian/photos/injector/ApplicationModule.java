package name.kocian.photos.injector;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import name.kocian.photos.BuildConfig;
import name.kocian.photos.rest.BusinessService;
import name.kocian.photos.utils.NetworkHelper;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Application module
 *
 * @author Michal Kocian
 */
@Module
public class ApplicationModule {

    public static String URL = BuildConfig.REST_BASE_URL;

    /**
     * KVB application
     */
    private final Context mApplication;

    /**
     * Constructor
     *
     * @param application Application
     */
    public ApplicationModule(Context application) {

        mApplication = application;
    }

    /**
     * Provide application context
     *
     * @return Application context
     */
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    Context provideApplicationContext() {

        return this.mApplication;
    }

    /**
     * Web services
     *
     * @return Business service
     */
    @Provides
    @Singleton
    public BusinessService provideBusinessService(Gson gson) {

        // Create HTTP client
        OkHttpClient.Builder clientBuilder = new OkHttpClient()
                .newBuilder()
                .connectTimeout(16, TimeUnit.SECONDS)
                .readTimeout(16, TimeUnit.SECONDS)
                .writeTimeout(16, TimeUnit.SECONDS);

        // Logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(interceptor);

        // Cache for requests
        try {
            long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MB
            //noinspection ConstantConditions
            Cache cache = new Cache(mApplication.getCacheDir(), SIZE_OF_CACHE);
            clientBuilder.cache(cache);

            clientBuilder.addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR);
            clientBuilder.addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE);
        } catch (NullPointerException e) {
            Timber.e("Cache not active");
        }

        OkHttpClient client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationModule.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(BusinessService.class);
    }

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR = chain -> {
        Response originalResponse = chain.proceed(chain.request());
        String cacheControl = originalResponse.header("Cache-Control");
        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 5000)
                    .build();
        } else {
            return originalResponse;
        }
    };

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkHelper.isNetworkAvailable(mApplication)) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached")
                        .build();
            }
            return chain.proceed(request);
        }
    };

    /**
     * GSON library
     *
     * @return GSON
     */
    @Provides
    @Singleton
    public Gson provideGson() {

        return new GsonBuilder()
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
