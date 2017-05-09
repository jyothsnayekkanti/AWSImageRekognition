package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
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
        Image source = getImageUtil(sourceBucketName, sourceFilePath);
        Image target = getImageUtil(targetBucketName, targetFilePath);
        Float similarityThreshold = 70F;

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);

        return amazonRekognition.compareFaces(compareFacesRequest);
    }

    @Override
    public CompareFacesResult compareFacesGivenExternalImages(String source, String target) {
        return null;
    }

    @Override
    public CompareFacesResult compareFacesGivenImages(ByteBuffer sourceImage, ByteBuffer targetImage) {
        return null;
    }

    private Image getImageUtil(String bucket, String key) {
        return new Image()
                .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(key));
    }

}
