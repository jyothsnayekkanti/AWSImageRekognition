package com.josh.awsimagerekognition;

import com.josh.awsimagerekognition.health.AWSCredentialsHealthCheck;
import com.josh.awsimagerekognition.resources.CompareFacesResource;
import com.josh.awsimagerekognition.resources.DetectLabelsResource;
import com.josh.awsimagerekognition.resources.DetectModerationLabelsResource;
import com.josh.awsimagerekognition.resources.ImageRekognitionResource;
import com.josh.awsimagerekognition.service.*;
import com.josh.awsimagerekognition.service.impl.*;
import com.josh.awsimagerekognition.utils.ImageUtility;
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
        final ImageUtility imageUtility = new ImageUtility(configuration.getImageInputStreamMaxSizeBinary());
        final CompareFacesService compareFacesService = new CompareFacesServiceImpl(amazonRekognitionClientService, configuration.getCompareFacesSimilarityThreshold(), imageUtility);
        final DetectLabelsService detectLabelsService = new DetectLabelsServiceImpl(amazonRekognitionClientService, configuration.getDetectLabelsMinConfidence(), configuration.getDetectLabelsMaxLabels(), imageUtility);
        final DetectModerationLabelsService detectModerationLabelsService = new DetectModerationLabelsServiceImpl(amazonRekognitionClientService, configuration.getDetectModerationLabelsMinConfidence(), imageUtility);

        environment.jersey().register(MultiPartFeature.class);

        final AWSCredentialsHealthCheck awsCredentialsHealthCheck = new AWSCredentialsHealthCheck(configuration.getProfilesConfigFilePath(), configuration.getProfileName());
        environment.healthChecks().register("awsCredentialsHealthCheck", awsCredentialsHealthCheck);

        environment.jersey().register(new ImageRekognitionResource(amazonRekognitionClientService));
        environment.jersey().register(new CompareFacesResource(compareFacesService));
        environment.jersey().register(new DetectLabelsResource(detectLabelsService));
        environment.jersey().register(new DetectModerationLabelsResource(detectModerationLabelsService));


    }
}
