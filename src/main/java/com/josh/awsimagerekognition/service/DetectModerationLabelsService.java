package com.josh.awsimagerekognition.service;

import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;

import java.io.IOException;
import java.io.InputStream;

public interface DetectModerationLabelsService {
    DetectModerationLabelsResult detectModerationLabelsGivenS3Image(String bucketName, String filePath);

    DetectModerationLabelsResult detectModerationLabelsGivenExternalImage(String imageURL) throws IOException;

    DetectModerationLabelsResult detectModerationLabelsGivenImage(InputStream fileInputStream) throws IOException;
}
