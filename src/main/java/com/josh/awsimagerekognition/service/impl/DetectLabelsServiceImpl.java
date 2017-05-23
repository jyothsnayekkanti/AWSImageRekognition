package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.DetectLabelsService;
import com.josh.awsimagerekognition.utils.ImageUtility;

import java.io.IOException;
import java.io.InputStream;

public class DetectLabelsServiceImpl implements DetectLabelsService {

    private AmazonRekognition amazonRekognition;
    private Float minConfidence;
    private int maxLabels;
    private ImageUtility imageUtility;

    public DetectLabelsServiceImpl(AmazonRekognitionClientService amazonRekognitionClientService, Float minConfidence, int maxLabels, ImageUtility imageUtility) {
        this.amazonRekognition = amazonRekognitionClientService.getAmazonRekognitionClient();
        this.minConfidence = minConfidence;
        this.maxLabels = maxLabels;
        this.imageUtility = imageUtility;
    }

    @Override
    public DetectLabelsResult detectLabelsGivenS3Image(String bucketName, String filePath) {
        Image image = imageUtility.getImageUtil(bucketName, filePath);
        return callDetectLabels(image, maxLabels, minConfidence);
    }

    @Override
    public DetectLabelsResult detectLabelsGivenExternalImage(String imageURL) throws IOException {
        return null;
    }

    @Override
    public DetectLabelsResult detectLabelsGivenImage(InputStream fileInputStream) throws IOException {
        return null;
    }

    private DetectLabelsResult callDetectLabels(Image image, int maxLabels, Float minConfidence){
        DetectLabelsRequest detectLabelsRequest = new DetectLabelsRequest()
                .withImage(image)
                .withMaxLabels(maxLabels)
                .withMinConfidence(minConfidence);
        return amazonRekognition.detectLabels(detectLabelsRequest);
    }

}
