package com.josh.awsimagerekognition.service;

import com.amazonaws.services.rekognition.model.CompareFacesResult;

import java.io.IOException;
import java.io.InputStream;

public interface CompareFacesService{

    CompareFacesResult compareFacesGivenS3Images(String sourceBucketName, String sourceFilePath, String targetBucketName, String targetFilePath);

    CompareFacesResult compareFacesGivenExternalImages(String source, String target);

    CompareFacesResult compareFacesGivenImages(InputStream sourceImage, InputStream targetImage)  throws IOException;

}
