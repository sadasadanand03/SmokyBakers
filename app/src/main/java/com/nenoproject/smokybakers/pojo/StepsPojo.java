package com.nenoproject.smokybakers.pojo;

/**
 * Created by sadanandk on 8/9/2017.
 */

@SuppressWarnings("ALL")
public class StepsPojo {

    private String shortDescription;
    private String description;
    private String videoURL;

    public StepsPojo(String shortDescription, String description, String videoURL) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
    }

    public String getShortDescription() {
        return shortDescription;
    }
    public String getDescription() {
        return description;
    }
    public String getVideoURL() {
        return videoURL;
    }

}
