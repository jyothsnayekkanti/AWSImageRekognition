package com.josh.awsimagerekognition.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.ByteBuffer;

public class RawImagesInput {

    private ByteBuffer sourceImage;
    private ByteBuffer targetImage;

    public RawImagesInput(){}

    public RawImagesInput(ByteBuffer sourceImage, ByteBuffer targetImage){
        this.sourceImage = sourceImage;
        this.targetImage = targetImage;
    }

    @JsonProperty
    public ByteBuffer getSourceImage() {
        return sourceImage;
    }

    @JsonProperty
    public ByteBuffer getTargetImage() {
        return targetImage;
    }
}
