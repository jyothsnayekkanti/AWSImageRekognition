package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.DetectModerationLabelsService;
import com.josh.awsimagerekognition.utils.ImageUtility;

import java.io.IOException;
import java.io.InputStream;

public class DetectModerationLabelsServiceImpl implements DetectModerationLabelsService {

    private AmazonRekognition amazonRekognition;
    private Float minConfidence;
    private ImageUtility imageUtility;

    public DetectModerationLabelsServiceImpl(AmazonRekognitionClientService amazonRekognitionClientService, Float minConfidence, ImageUtility imageUtility) {
        this.amazonRekognition = amazonRekognitionClientService.getAmazonRekognitionClient();
        this.minConfidence = minConfidence;
        this.imageUtility = imageUtility;
    }

    @Override
    public DetectModerationLabelsResult detectModerationLabelsGivenS3Image(String bucketName, String filePath) {
        Image image = imageUtility.getImageUtil(bucketName, filePath);
        return callDetectModerationLabels(image, minConfidence);
    }

    @Override
    public DetectModerationLabelsResult detectModerationLabelsGivenExternalImage(String imageURL) throws IOException {
        Image image = imageUtility.getImageUtilGivenImageBytes(imageUtility.getBinaryImageFromURL(imageURL));
        return callDetectModerationLabels(image, minConfidence);
    }

    @Override
    public DetectModerationLabelsResult detectModerationLabelsGivenImage(InputStream imageInputStream) throws IOException {
        Image image = imageUtility.getImageUtilGivenImageBytes(imageUtility.getBinaryImageFronInputStream(imageInputStream));
        return callDetectModerationLabels(image, minConfidence);
    }

    private DetectModerationLabelsResult callDetectModerationLabels(Image image, Float minConfidence){
        DetectModerationLabelsRequest detectModerationLabelsRequest = new DetectModerationLabelsRequest()
                .withImage(image)
                .withMinConfidence(minConfidence);
        return amazonRekognition.detectModerationLabels(detectModerationLabelsRequest);
    }

}
