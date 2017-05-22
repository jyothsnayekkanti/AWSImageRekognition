package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.DetectLabelsService;

import java.io.IOException;
import java.io.InputStream;

public class DetectLabelsServiceImpl implements DetectLabelsService {

    private AmazonRekognition amazonRekognition;
    private Float minConfidence;
    private int maxLabels;
    private long imageInputStreamMaxSizeBinary;

    public DetectLabelsServiceImpl(AmazonRekognitionClientService amazonRekognitionClientService, Float minConfidence, int maxLabels, long imageInputStreamMaxSizeBinary) {
        this.amazonRekognition = amazonRekognitionClientService.getAmazonRekognitionClient();
        this.minConfidence = minConfidence;
        this.maxLabels = maxLabels;
        this.imageInputStreamMaxSizeBinary = imageInputStreamMaxSizeBinary;
    }

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
