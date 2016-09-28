package name.kocian.photos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Michal Kocian
 */
@Data
public class Flicker {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("link")
    private String link;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("modified")
    private String modified;

    @Expose
    @SerializedName("generator")
    private String generator;

    @Expose
    @SerializedName("items")
    private List<Photo> photos = new ArrayList<>();
}
