package com.josh.awsimagerekognition.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalImagesInput {

    private String sourceLink;
    private String targetLink;

    public ExternalImagesInput(){}

    public ExternalImagesInput(String source, String target){
        this.sourceLink = source;
        this.targetLink = target;
    }

    @JsonProperty
    public String getSourceLink() {
        return sourceLink;
    }

    @JsonProperty
    public String getTargetLink() {
        return targetLink;
    }
}
