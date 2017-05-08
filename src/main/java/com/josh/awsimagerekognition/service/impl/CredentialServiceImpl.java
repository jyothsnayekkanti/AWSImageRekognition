package com.josh.awsimagerekognition.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.josh.awsimagerekognition.service.CredentialService;

public class CredentialServiceImpl implements CredentialService {

    private String profilesConfigFilePath;
    private String profileName;

    public CredentialServiceImpl(String profilesConfigFilePath, String profileName){
        this.profilesConfigFilePath = profilesConfigFilePath;
        this.profileName = profileName;
    }

    @Override
    public AWSCredentials getAWSCredentials() {
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider(profilesConfigFilePath, profileName).getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location, and is in valid format.", e);
        }
        return credentials;
    }


}
