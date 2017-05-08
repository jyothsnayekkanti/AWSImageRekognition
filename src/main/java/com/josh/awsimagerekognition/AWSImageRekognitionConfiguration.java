package com.josh.awsimagerekognition;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class AWSImageRekognitionConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String profilesConfigFilePath;

    @JsonProperty
    private String profileName;

    @JsonProperty
    private String region;

    public String getProfilesConfigFilePath(){ return profilesConfigFilePath; }

    public String getProfileName(){ return profileName; }

    public String getRegion(){ return region; }

}
