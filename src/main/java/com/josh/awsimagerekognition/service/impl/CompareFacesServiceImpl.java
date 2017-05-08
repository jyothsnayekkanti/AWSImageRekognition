package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.CompareFacesService;

import java.nio.ByteBuffer;

public class CompareFacesServiceImpl implements CompareFacesService {

    private AmazonRekognition amazonRekognition;

    public CompareFacesServiceImpl(AmazonRekognitionClientService amazonRekognitionClientService) {
        this.amazonRekognition = amazonRekognitionClientService.getAmazonRekognitionClient();
    }

    @Override
    public CompareFacesResult compareFacesGivenS3Images(String sourceBucketName, String sourceFilePath, String targetBucketName, String targetFilePath) {
        return null;
    }

    @Override
    public CompareFacesResult compareFacesGivenExternalImages(String source, String target) {
        return null;
    }

    @Override
    public CompareFacesResult compareFacesGivenImages(ByteBuffer sourceImage, ByteBuffer targetImage) {
        return null;
    }
}
