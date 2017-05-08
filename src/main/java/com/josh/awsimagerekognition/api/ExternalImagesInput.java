package com.josh.awsimagerekognition.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalImagesInput {

    private String source;
    private String target;

    public ExternalImagesInput(){}

    public ExternalImagesInput(String source, String target){
        this.source = source;
        this.target = target;
    }

    @JsonProperty
    public String getSource() {
        return source;
    }

    @JsonProperty
    public String getTarget() {
        return target;
    }
}
