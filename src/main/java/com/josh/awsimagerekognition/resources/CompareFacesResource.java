package com.josh.awsimagerekognition.resources;

import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.CompareFacesService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.nio.ByteBuffer;

@Path("api/compareFaces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompareFacesResource {

    private CompareFacesService compareFacesService;

    public CompareFacesResource(CompareFacesService compareFacesService) {
        this.compareFacesService = compareFacesService;
    }

    @Path("/s3")
    @POST
    public CompareFacesResult compareFacesGivenS3Images(String sourceBucketName, String sourceFilePath, String targetBucketName, String targetFilePath){

        CompareFacesResult compareFacesResult = compareFacesService.compareFacesGivenS3Images(sourceBucketName, sourceFilePath, targetBucketName, targetFilePath);

        return compareFacesResult;
    }

    @Path("/external")
    @POST
    public CompareFacesResult compareFacesGivenExternalImages(String source, String target){

        CompareFacesResult compareFacesResult = compareFacesService.compareFacesGivenExternalImages(source, target);

        return compareFacesResult;
    }

    @Path("/raw")
    @POST
    public CompareFacesResult compareFacesGivenImages(ByteBuffer sourceImage, ByteBuffer targetImage){

        CompareFacesResult compareFacesResult = compareFacesService.compareFacesGivenImages(sourceImage, targetImage);

        return compareFacesResult;
    }

}
