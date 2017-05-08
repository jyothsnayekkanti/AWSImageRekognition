package com.josh.awsimagerekognition.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class S3ImagesInput {

    private String sourceBucketName;
    private String sourceFilePath;
    private String targetBucketName;
    private String targetFilePath;

    public S3ImagesInput(){

    }

    public S3ImagesInput(String sourceBucketName, String sourceFilePath, String targetBucketName, String targetFilePath){
        this.sourceBucketName = sourceBucketName;
        this.sourceFilePath = sourceFilePath;
        this.targetBucketName = targetBucketName;
        this.targetFilePath = targetFilePath;
    }

    @JsonProperty
    public String getSourceBucketName() {
        return sourceBucketName;
    }

    @JsonProperty
    public String getSourceFilePath() {
        return sourceFilePath;
    }

    @JsonProperty
    public String getTargetBucketName() {
        return targetBucketName;
    }

    @JsonProperty
    public String getTargetFilePath() {
        return targetFilePath;
    }
}
