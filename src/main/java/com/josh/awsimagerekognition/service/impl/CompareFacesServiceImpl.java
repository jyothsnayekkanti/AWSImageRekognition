package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.josh.awsimagerekognition.api.RawImagesInput;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.CompareFacesService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class CompareFacesServiceImpl implements CompareFacesService {

    private AmazonRekognition amazonRekognition;
    private Float similarityThreshold;
    private long imageInputStreamMaxSizeBinary;

    public CompareFacesServiceImpl(AmazonRekognitionClientService amazonRekognitionClientService,Float similarityThreshold, long imageInputStreamMaxSizeBinary) {
        this.amazonRekognition = amazonRekognitionClientService.getAmazonRekognitionClient();
        this.similarityThreshold = similarityThreshold;
        this.imageInputStreamMaxSizeBinary = imageInputStreamMaxSizeBinary;
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
    public CompareFacesResult compareFacesGivenImages(InputStream sourceImage, InputStream targetImage) throws IOException {
        Image source = getImageUtilGivenImageBytes(getBinaryImageFronInputStream(sourceImage));
        Image target = getImageUtilGivenImageBytes(getBinaryImageFronInputStream(targetImage));

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);

        return amazonRekognition.compareFaces(compareFacesRequest);
    }

    private Image getImageUtil(String bucket, String key) {
        return new Image()
                .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(key));
    }

    private Image getImageUtilGivenImageBytes(ByteBuffer image) {
        return new Image()
                .withBytes(image);
    }

    private ByteBuffer getBinaryImageFronInputStream(InputStream inputStream) throws IOException {
        return ByteBuffer.wrap(IOUtils.toByteArray(new RawImagesInput(inputStream, imageInputStreamMaxSizeBinary )));
    }

}
