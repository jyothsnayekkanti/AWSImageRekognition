package com.josh.awsimagerekognition.resources;

import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.josh.awsimagerekognition.api.ExternalImagesInput;
import com.josh.awsimagerekognition.api.S3ImagesInput;
import com.josh.awsimagerekognition.service.CompareFacesService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("api/compareFaces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompareFacesResource {

    private CompareFacesService compareFacesService;

    public CompareFacesResource(CompareFacesService compareFacesService) {
        this.compareFacesService = compareFacesService;
    }

    @Path("/s3")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public CompareFacesResult compareFacesGivenS3Images(S3ImagesInput s3ImagesInput){

        CompareFacesResult compareFacesResult = compareFacesService.compareFacesGivenS3Images(s3ImagesInput.getSourceBucketName(), s3ImagesInput.getSourceFilePath(), s3ImagesInput.getTargetBucketName(), s3ImagesInput.getTargetFilePath());


        return compareFacesResult;
    }

    @Path("/external")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response compareFacesGivenExternalImages(ExternalImagesInput externalImagesInput){

        CompareFacesResult compareFacesResult = null;
        try {
            compareFacesResult = compareFacesService.compareFacesGivenExternalImages(externalImagesInput.getSourceLink(), externalImagesInput.getTargetLink());
            return Response.ok(compareFacesResult).build();
        } catch (IOException e) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
        }
    }

    @Path("/raw")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response compareFacesGivenImages(
            @FormDataParam("sourceFile") InputStream fileInputStreamSource,
            @FormDataParam("targetFile") InputStream fileInputStreamTarget){
        CompareFacesResult compareFacesResult = null;
        try {
            compareFacesResult = compareFacesService.compareFacesGivenImages(fileInputStreamSource, fileInputStreamTarget);
            return Response.ok(compareFacesResult).build();
        } catch (IOException e) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
        }
    }

}
