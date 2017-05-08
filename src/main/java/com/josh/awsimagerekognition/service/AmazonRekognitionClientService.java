package com.josh.awsimagerekognition.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.CompareFacesResult;

import java.nio.ByteBuffer;

public interface AmazonRekognitionClientService {

    AmazonRekognition getAmazonRekognitionClient();

}
