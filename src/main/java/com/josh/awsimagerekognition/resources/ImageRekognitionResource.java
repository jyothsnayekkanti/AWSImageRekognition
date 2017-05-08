package com.josh.awsimagerekognition.resources;

import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api/imagerekognition")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageRekognitionResource {

    private AmazonRekognitionClientService amazonRekognitionClientService;

    public ImageRekognitionResource(AmazonRekognitionClientService amazonRekognitionClientService){
        this.amazonRekognitionClientService = amazonRekognitionClientService;
    }

}
