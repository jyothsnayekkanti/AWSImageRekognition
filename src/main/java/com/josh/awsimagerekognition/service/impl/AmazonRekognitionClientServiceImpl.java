package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.CredentialService;

import java.nio.ByteBuffer;

public class AmazonRekognitionClientServiceImpl implements AmazonRekognitionClientService {

    private AWSCredentials credentials;
    private String region;

    public AmazonRekognitionClientServiceImpl(CredentialService credentialService, String region){
        this.credentials = credentialService.getAWSCredentials();
        this.region = region;
    }

    public AmazonRekognition getAmazonRekognitionClient(){

        AmazonRekognition amazonRekognitionClient = null;

        amazonRekognitionClient = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        return amazonRekognitionClient;
    }




}
