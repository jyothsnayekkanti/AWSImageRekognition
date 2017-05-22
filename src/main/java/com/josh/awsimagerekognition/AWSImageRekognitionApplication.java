package com.josh.awsimagerekognition;

import com.josh.awsimagerekognition.health.AWSCredentialsHealthCheck;
import com.josh.awsimagerekognition.resources.CompareFacesResource;
import com.josh.awsimagerekognition.resources.DetectLabelsResource;
import com.josh.awsimagerekognition.resources.ImageRekognitionResource;
import com.josh.awsimagerekognition.service.AmazonRekognitionClientService;
import com.josh.awsimagerekognition.service.CompareFacesService;
import com.josh.awsimagerekognition.service.CredentialService;
import com.josh.awsimagerekognition.service.DetectLabelsService;
import com.josh.awsimagerekognition.service.impl.AmazonRekognitionClientServiceImpl;
import com.josh.awsimagerekognition.service.impl.CompareFacesServiceImpl;
import com.josh.awsimagerekognition.service.impl.CredentialServiceImpl;
import com.josh.awsimagerekognition.service.impl.DetectLabelsServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class AWSImageRekognitionApplication extends Application<AWSImageRekognitionConfiguration> {

    public static void main(String[] args) throws Exception {
        new AWSImageRekognitionApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AWSImageRekognitionConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AWSImageRekognitionConfiguration configuration, Environment environment) throws Exception {

        final CredentialService credentialService = new CredentialServiceImpl(configuration.getProfilesConfigFilePath(), configuration.getProfileName());
        final AmazonRekognitionClientService amazonRekognitionClientService = new AmazonRekognitionClientServiceImpl(credentialService, configuration.getRegion());
        final CompareFacesService compareFacesService = new CompareFacesServiceImpl(amazonRekognitionClientService, configuration.getCompareFacesSimilarityThreshold(), configuration.getImageInputStreamMaxSizeBinary());
        final DetectLabelsService detectLabelsService = new DetectLabelsServiceImpl(amazonRekognitionClientService, configuration.getDetectLabelsMinConfidence(), configuration.getMaxLabels(), configuration.getImageInputStreamMaxSizeBinary());

        environment.jersey().register(MultiPartFeature.class);

        final AWSCredentialsHealthCheck awsCredentialsHealthCheck = new AWSCredentialsHealthCheck(configuration.getProfilesConfigFilePath(), configuration.getProfileName());
        environment.healthChecks().register("awsCredentialsHealthCheck", awsCredentialsHealthCheck);

        environment.jersey().register(credentialService);
        environment.jersey().register(amazonRekognitionClientService);
        environment.jersey().register(detectLabelsService);
        environment.jersey().register(new ImageRekognitionResource(amazonRekognitionClientService));
        environment.jersey().register(new CompareFacesResource(compareFacesService));
        environment.jersey().register(new DetectLabelsResource(detectLabelsService));


    }
}
