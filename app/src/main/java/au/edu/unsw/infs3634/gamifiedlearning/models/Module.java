package au.edu.unsw.infs3634.gamifiedlearning.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Module {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("submodules")
    @Expose
    private List<Submodule> submodules = null;
    @SerializedName("sources")
    @Expose
    private List<Source> sources = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Submodule> getSubmodules() {
        return submodules;
    }

    public void setSubmodules(List<Submodule> submodules) {
        this.submodules = submodules;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

}