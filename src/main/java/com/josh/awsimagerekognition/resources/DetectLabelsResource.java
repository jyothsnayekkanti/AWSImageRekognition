package com.josh.awsimagerekognition.resources;

import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.josh.awsimagerekognition.api.S3ImageParameters;
import com.josh.awsimagerekognition.service.DetectLabelsService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("api/detectLabels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetectLabelsResource {

    private DetectLabelsService detectLabelsService;

    public DetectLabelsResource(DetectLabelsService detectLabelsService) {
        this.detectLabelsService = detectLabelsService;
    }

    @Path("/s3")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public DetectLabelsResult detectLabelsGivenS3Images(S3ImageParameters s3ImageParameters){

        DetectLabelsResult detectLabelsResult = detectLabelsService.detectLabelsGivenS3Image(s3ImageParameters.getBucketName(), s3ImageParameters.getFilePath());


        return detectLabelsResult;
    }

    @Path("/external")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response detectLabelsGivenExternalImages(String imageURL){

        DetectLabelsResult detectLabelsResult = null;
        try {
            detectLabelsResult = detectLabelsService.detectLabelsGivenExternalImage(imageURL);
            return Response.ok(detectLabelsResult).build();
        } catch (IOException e) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
        }
    }

    @Path("/raw")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response detectLabelsGivenImages(
            @FormDataParam("file") InputStream fileInputStream){
        DetectLabelsResult detectLabelsResult = null;
        try {
            detectLabelsResult = detectLabelsService.detectLabelsGivenImage(fileInputStream);
            return Response.ok(detectLabelsResult).build();
        } catch (IOException e) {
            return Response.status(Response.Status.PRECONDITION_FAILED).entity(e.getMessage()).build();
        }
    }

}
