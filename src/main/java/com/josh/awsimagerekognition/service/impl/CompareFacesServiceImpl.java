package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.CompareFacesService;
import com.josh.awsimagerekognition.utils.ImageUtility;

import java.io.IOException;
import java.io.InputStream;

public class CompareFacesServiceImpl implements CompareFacesService {

    private AmazonRekognition amazonRekognition;
    private Float similarityThreshold;
    private ImageUtility imageUtility;

    public CompareFacesServiceImpl(AmazonRekognitionClientService amazonRekognitionClientService, Float similarityThreshold, ImageUtility imageUtility) {
        this.amazonRekognition = amazonRekognitionClientService.getAmazonRekognitionClient();
        this.similarityThreshold = similarityThreshold;
        this.imageUtility = imageUtility;
    }

    @Override
    public CompareFacesResult compareFacesGivenS3Images(String sourceBucketName, String sourceFilePath, String targetBucketName, String targetFilePath) {
        Image source = imageUtility.getImageUtil(sourceBucketName, sourceFilePath);
        Image target = imageUtility.getImageUtil(targetBucketName, targetFilePath);
        return callCompareFaces(source, target, similarityThreshold);
    }

    @Override
    public CompareFacesResult compareFacesGivenExternalImages(String sourceLink, String targetLink) throws IOException {
        Image source = imageUtility.getImageUtilGivenImageBytes(imageUtility.getBinaryImageFromURL(sourceLink));
        Image target = imageUtility.getImageUtilGivenImageBytes(imageUtility.getBinaryImageFromURL(targetLink));
        return callCompareFaces(source, target, similarityThreshold);
    }

    @Override
    public CompareFacesResult compareFacesGivenImages(InputStream sourceImage, InputStream targetImage) throws IOException {
        Image source = imageUtility.getImageUtilGivenImageBytes(imageUtility.getBinaryImageFronInputStream(sourceImage));
        Image target = imageUtility.getImageUtilGivenImageBytes(imageUtility.getBinaryImageFronInputStream(targetImage));
        return callCompareFaces(source, target, similarityThreshold);

    }

    private CompareFacesResult callCompareFaces(Image source,Image target, Float similarityThreshold){
        CompareFacesRequest compareFacesRequest = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);
        return amazonRekognition.compareFaces(compareFacesRequest);
    }

}
