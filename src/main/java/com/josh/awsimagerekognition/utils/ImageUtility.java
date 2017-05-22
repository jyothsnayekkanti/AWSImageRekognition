package com.josh.awsimagerekognition.utils;

import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.josh.awsimagerekognition.api.RawImagesInput;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public class ImageUtility {

    private long imageInputStreamMaxSizeBinary;

    public ImageUtility(long imageInputStreamMaxSizeBinary){
        this.imageInputStreamMaxSizeBinary = imageInputStreamMaxSizeBinary;
    }

    public Image getImageUtil(String bucket, String key) {
        return new Image()
                .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(key));
    }

    public Image getImageUtilGivenImageBytes(ByteBuffer image) {
        return new Image()
                .withBytes(image);
    }

    public ByteBuffer getBinaryImageFronInputStream(InputStream inputStream) throws IOException {
        return ByteBuffer.wrap(IOUtils.toByteArray(new RawImagesInput(inputStream, imageInputStreamMaxSizeBinary )));
    }

    public ByteBuffer getBinaryImageFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
        return ByteBuffer.wrap(IOUtils.toByteArray(new RawImagesInput(inputStream, imageInputStreamMaxSizeBinary )));
    }

}
