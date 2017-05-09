package com.josh.awsimagerekognition.health;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.codahale.metrics.health.HealthCheck;

public class AWSCredentialsHealthCheck extends HealthCheck {

    private String profilesConfigFilePath;
    private String profileName;

    public AWSCredentialsHealthCheck(String profilesConfigFilePath, String profileName){
        this.profilesConfigFilePath = profilesConfigFilePath;
        this.profileName = profileName;
    }

    @Override
    protected Result check() throws Exception {
        try {
            System.out.println("Health Check");
            AWSCredentials credentials = new ProfileCredentialsProvider(profilesConfigFilePath, profileName).getCredentials();
            return Result.healthy();
        } catch (Exception e) {
            return Result.unhealthy("Problem in loading credentials");
        }
    }
}
