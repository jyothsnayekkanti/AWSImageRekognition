package com.josh.awsimagerekognition.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class S3ImageParameters {

    private String bucketName;
    private String filePath;

    public S3ImageParameters(){

    }

    public S3ImageParameters(String bucketName, String filePath){
        this.bucketName = bucketName;
        this.filePath = filePath;
    }

    @JsonProperty
    public String getBucketName() {
        return bucketName;
    }

    @JsonProperty
    public String getFilePath() {
        return filePath;
    }

}
