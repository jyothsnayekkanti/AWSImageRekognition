package com.josh.awsimagerekognition.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalImageURL {

    private String imageURL;

    public ExternalImageURL(){}

    public ExternalImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    @JsonProperty
    public String getImageURL() {
        return imageURL;
    }
}
