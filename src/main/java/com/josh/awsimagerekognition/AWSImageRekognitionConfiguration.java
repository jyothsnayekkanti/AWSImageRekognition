package com.josh.awsimagerekognition;

import com.amazonaws.regions.Regions;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class AWSImageRekognitionConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty
    private String profilesConfigFilePath;

    @JsonProperty
    private String profileName = "default";

    @JsonProperty
    private Float compareFacesSimilarityThreshold;

    @JsonProperty
    private long imageInputStreamMaxSizeBinary;

    @JsonProperty
    private String region = Regions.DEFAULT_REGION.getName();

    @JsonProperty
    private Float detectLabelsMinConfidence;

    @JsonProperty
    private int detectLabelsMaxLabels;

    @JsonProperty
    private Float detectModerationLabelsMinConfidence;

    public String getProfilesConfigFilePath(){ return profilesConfigFilePath; }

    public String getProfileName(){ return profileName; }

    public String getRegion(){ return region; }

    public Float getCompareFacesSimilarityThreshold() {
        return compareFacesSimilarityThreshold;
    }

    public long getImageInputStreamMaxSizeBinary() {
        return imageInputStreamMaxSizeBinary;
    }

    public Float getDetectLabelsMinConfidence() { return detectLabelsMinConfidence; }

    public int getDetectLabelsMaxLabels(){ return detectLabelsMaxLabels; }

    public Float getDetectModerationLabelsMinConfidence() { return detectModerationLabelsMinConfidence; }
}
