package com.josh.awsimagerekognition.service;

import com.amazonaws.services.rekognition.model.DetectLabelsResult;

import java.io.IOException;
import java.io.InputStream;

public interface DetectLabelsService {
    public DetectLabelsResult detectLabelsGivenS3Image(String bucketName, String filePath);

    public DetectLabelsResult detectLabelsGivenExternalImage(String imageURL) throws IOException;

    public DetectLabelsResult detectLabelsGivenImage(InputStream fileInputStream) throws IOException;
}
