package name.kocian.photos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * @author Michal Kocian
 */
@Data
public class Thumb {

    @Expose
    @SerializedName("m")
    private String thumb;
}
