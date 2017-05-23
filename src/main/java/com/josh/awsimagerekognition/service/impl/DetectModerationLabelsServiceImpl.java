package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.josh.awsimagerekognition.service.DetectLabelsService;

import java.io.IOException;
import java.io.InputStream;

public class DetectModerationLabelsServiceImpl implements DetectLabelsService {
    @Override
    public DetectLabelsResult detectLabelsGivenS3Image(String bucketName, String filePath) {
        return null;
    }

    @Override
    public DetectLabelsResult detectLabelsGivenExternalImage(String imageURL) throws IOException {
        return null;
    }

    @Override
    public DetectLabelsResult detectLabelsGivenImage(InputStream fileInputStream) throws IOException {
        return null;
    }
}
