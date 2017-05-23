package com.josh.awsimagerekognition.resources;

import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import com.josh.awsimagerekognition.api.ExternalImageURL;
import com.josh.awsimagerekognition.api.S3ImageParameters;
import com.josh.awsimagerekognition.service.DetectModerationLabelsService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("api/detectModerationLabels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetectModerationLabelsResource {

    private DetectModerationLabelsService detectModerationLabelsService;

    public DetectModerationLabelsResource(DetectModerationLabelsService detectModerationLabelsService) {
        this.detectModerationLabelsService = detectModerationLabelsService;
    }

    @Path("/s3")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public DetectModerationLabelsResult detectModerationLabelsGivenS3Images(S3ImageParameters s3ImageParameters){

        DetectModerationLabelsResult detectModerationLabelsResult = detectModerationLabelsService.detectModerationLabelsGivenS3Image(s3ImageParameters.getBucketName(), s3ImageParameters.getFilePath());


        return detectModerationLabelsResult;
    }

    @Path("/external")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response detectModerationLabelsGivenExternalImages(ExternalImageURL imageURL){

        DetectModerationLabelsResult detectModerationLabelsResult = null;
        try {
            detectModerationLabelsResult = detectModerationLabelsService.detectModerationLabelsGivenExternalImage(imageURL.getImageURL());
            return Response.ok(detectModerationLabelsResult).build();
        } catch (IOException e) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
        }
    }

    @Path("/raw")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response detectModerationLabelsGivenImages(
            @FormDataParam("file") InputStream fileInputStream){
        DetectModerationLabelsResult detectModerationLabelsResult = null;
        try {
            detectModerationLabelsResult = detectModerationLabelsService.detectModerationLabelsGivenImage(fileInputStream);
            return Response.ok(detectModerationLabelsResult).build();
        } catch (IOException e) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
        }
    }

}
