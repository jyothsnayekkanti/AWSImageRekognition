package com.josh.awsimagerekognition.service;

import com.amazonaws.services.rekognition.model.CompareFacesResult;

import java.nio.ByteBuffer;

public interface CompareFacesService{

    CompareFacesResult compareFacesGivenS3Images(String sourceBucketName, String sourceFilePath, String targetBucketName, String targetFilePath);

    CompareFacesResult compareFacesGivenExternalImages(String source, String target);

    CompareFacesResult compareFacesGivenImages(ByteBuffer sourceImage, ByteBuffer targetImage);

}
