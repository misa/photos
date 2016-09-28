package name.kocian.photos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author Michal Kocian
 */
@Data
public class Photo {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("link")
    private String link;

    @Expose
    @SerializedName("media")
    private Thumb thumb;

    @Expose
    @SerializedName("date_taken")
    private String dateTaken;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("published")
    private String published;

    @Expose
    @SerializedName("author")
    private String author;

    @Expose
    @SerializedName("author_id")
    private String authorId;

    @Expose
    @SerializedName("tags")
    private String tags;
}
