package com.josh.awsimagerekognition;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AWSImageRekognitionApplication extends Application<AWSImageRekognitionConfiguration> {

    public static void main(String[] args) throws Exception {
        new AWSImageRekognitionApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AWSImageRekognitionConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AWSImageRekognitionConfiguration awsImageRekognitionConfiguration, Environment environment) throws Exception {

    }
}
