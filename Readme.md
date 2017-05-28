# AWSImageRekognition

This project simplifies using Amazon Recognition in your applications with an **API tailored to Java programming language**.
It uses AWS SDK for Java and exposes key features of Amazon Image Rekognition as **webservices** which can be easily plugged into your application.

Amazon Rekognition is a service that enables you to add image analysis to your applications. With
Rekognition, you can detect objects, scenes, and faces in images. You can also search and compare faces.
The Rekognition API enables you to quickly add sophisticated deep learning-based visual search and
image classification to your applications. Rekognition is built to analyze images at scale and integrates
seamlessly with Amazon S3, AWS Lambda, and other AWS services.

#### Use cases exposed in this project include
* [Face-based user verification](#face-based-user-verification)
* [Demographic analysis](#detecting-labels)
* [Image Moderation](#moderating-images)

APIs for each use cases above can take input images as follows
* From an S3 bucket or
* As an external link or
* Uploaded as a file.

## Pre-requisites:
* Setup AWS account
* Create an IAM User

## Getting started:
* Create an config file with AWS credentials at the root or a local path which needs to be provided in the awsimagerekognition.yaml file. A config file contains user and aws access details. Following is an example config file with two users default(admin) and an APIUser.

```
[default]
aws_access_key_id=XXXXXXXXXXXXXXXXXXX
aws_secret_access_key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

[APIUser]
aws_access_key_id=XXXXXXXXXXXXXXXXXX
aws_secret_access_key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
```

## Installing

* Click [here](https://github.com/jyothsnayekkanti/AWSImageRekognition/releases) for the latest release.
* Create an yaml file awsimagerekognition.yaml with all the required properties.

```
profilesConfigFilePath: <path to the config file eg: E:/awsCredentials/credentials.txt>
profileName: default
region: us-west-2
compareFacesSimilarityThreshold: 70F
imageInputStreamMaxSizeBinary: 5242880
detectLabelsMaxLabels: 6
detectLabelsMinConfidence: 70F
detectModerationLabelsMinConfidence: 70F
```
Don't forget to update config file path and other properties as per your requirement in the yaml file.

## Running the tests

* Run the jar file or deploy it as a service
```
java -jar <josh-aws-imagerekognition-1.0>.jar server awsimagerekognition.yaml
```



## Built with

* [Dropwizard](http://www.dropwizard.io/1.1.0/docs/) - The web framework used
* [Maven](https://maven.apache.org) - Dependency Management

## API Documentation

### Detecting Labels

You can use **detectLabels** API to detect labels in an image. For each label, a name and a confidence value in the analysis is returned.

Sample request to detect labels in an image from S3 bucket

```
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
	"bucketName": "<s3BucketName>",
	"filePath": "<filename>"
}' "http://localhost:8080/api/detectLabels/s3"
```

Sample request to detect labels in an image given image link

```
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
	"imageURL": "http://clv.h-cdn.co/assets/15/15/640x640/square-1428596092-home-sweet-home-living-room-0515.jpg"
}' "http://localhost:8080/api/detectLabels/external"
```

Sample request to detect labels in an image by uploading an image

```
curl -X POST -H "Content-Type: multipart/form-data" -H "Cache-Control: no-cache"  -F "file=@<path/to/image.jpg>" "http://localhost:8080/api/detectLabels/raw"
```

The following is an example response.

```
{
 "Labels": [
 {
 "Confidence": 98.4629,
 "Name": "beacon"
 },
 {
 "Confidence": 98.4629,
 "Name": "building"
 },
 {
 "Confidence": 98.4629,
 "Name": "lighthouse"
 },
 {
 "Confidence": 87.7924,
 "Name": "rock"
 },
 {
 "Confidence": 68.1049,
 "Name": "sea"
 }
 ]
}

```
 The response shows that the API detected five labels (that is, beacon, building, lighthouse, rock, and
sea). Each label has an associated level of confidence. For example, the detection algorithm is 98.4629%
confident that the image contains a building.

### Face-based user verification
To compare a face in the source image with each face in the target image, use the **compareFaces** API. If the source image contains more than one face, the service detects the largest face and uses it
for comparison. The API returns an array of face matches, source face information, image orientation, and an array of
unmatched faces.

Sample request to compare faces in source and target images from S3 bucket

```
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
	"sourceBucketName": "<s3SourceBucketName>",
	"sourceFilePath": "<sourceFilename>",
	"targetBucketName": "<s3TargetBucketName>",
	"targetFilePath": "<targetFilename>"
}' "http://localhost:8080/api/compareFaces/s3"
```

Sample request to compare faces in source and target images given, images link

```
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
	"sourceLink": "https://s-media-cache-ak0.pinimg.com/736x/b0/9f/28/b09f2888813aec6ed5c01761186bdf1a.jpg",
	"targetLink": "https://wallpaperbrowse.com/media/images/Funny-Smile-Wwe-Rock-Celebrities-Image.jpg"
}' "http://localhost:8080/api/compareFaces/external"
```

Sample request to compare faces in source and target images  by uploading images

```
curl -X POST -H "Content-Type: multipart/form-data" -H "Cache-Control: no-cache"  -F "sourceFile=@<path/to/sourceImage.jpg>" -F "targetFile=@<path/to/targetImage.jpg>" "http://localhost:8080/api/compareFaces/raw"
```


The following is an example response.

```
{
"FaceMatches": [{
 "Face": {
 "BoundingBox": {
 "Width": 0.5521978139877319,
 "Top": 0.1203877404332161,
 "Left": 0.23626373708248138,
 "Height": 0.3126954436302185
 },
 "Confidence": 99.98751068115234,
 "Pose": {
 "Yaw": -82.36799621582031,
 "Roll": -62.13221740722656,
 "Pitch": 0.8652129173278809
 },
 "Quality": {
 "Sharpness": 99.99880981445312,
 "Brightness": 54.49755096435547
 },
 "Landmarks": [{
 "Y": 0.2996366024017334,
 "X": 0.41685718297958374,
 "Type": "eyeLeft"
 },
 {
 "Y": 0.2658946216106415,
 "X": 0.4414493441581726,
 "Type": "eyeRight"
 },
 {
 "Y": 0.3465650677680969,
 "X": 0.48636093735694885,
 "Type": "nose"
 },
 {
 "Y": 0.30935320258140564,
 "X": 0.6251809000968933,
 "Type": "mouthLeft"
 },
 {
 "Y": 0.26942989230155945,
 "X": 0.6454493403434753,
 "Type": "mouthRight"
 }
 ]
 },
 "Similarity": 100.0
 }],
 "SourceImageOrientationCorrection": "ROTATE_90",
 "TargetImageOrientationCorrection": "ROTATE_90",
 "UnmatchedFaces": [{
 "BoundingBox": {
 "Width": 0.4890109896659851,
 "Top": 0.6566604375839233,
 "Left": 0.10989011079072952,
 "Height": 0.278298944234848
 },
 "Confidence": 99.99992370605469,
 "Pose": {
 "Yaw": 51.51519012451172,
 "Roll": -110.32493591308594,
 "Pitch": -2.322134017944336
 },
 "Quality": {
 "Sharpness": 99.99671173095703,
 "Brightness": 57.23163986206055
 },
"Landmarks": [{
 "Y": 0.8288310766220093,
 "X": 0.3133862614631653,
 "Type": "eyeLeft"
 },
 {
 "Y": 0.7632885575294495,
 "X": 0.28091415762901306,
 "Type": "eyeRight"
 },
 {
 "Y": 0.7417283654212952,
 "X": 0.3631140887737274,
 "Type": "nose"
 },
 {
 "Y": 0.8081989884376526,
 "X": 0.48565614223480225,
 "Type": "mouthLeft"
 },
 {
 "Y": 0.7548204660415649,
 "X": 0.46090251207351685,
 "Type": "mouthRight"
 }
 ]
 }],
 "SourceImageFace": {
 "BoundingBox": {
 "Width": 0.5521978139877319,
 "Top": 0.1203877404332161,
 "Left": 0.23626373708248138,
 "Height": 0.3126954436302185
 },
 "Confidence": 99.98751068115234
 }
}
```

### Moderating Images

Detects explicit or suggestive adult content in a specified JPEG or PNG format image. Use **detectModerationLabels** to moderate images depending on your requirements. For example, you might want to filter images that contain nudity, but not images containing suggestive content.

Sample request to detect explicit or suggestive adult content in an image from S3 bucket

```
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
	"bucketName": "<s3BucketName>",
	"filePath": "<filename>"
}' "http://localhost:8080/api/detectModerationLabels/s3"
```

Sample request to detect explicit or suggestive adult content in an image given image link

```
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache"  -d '{
	"imageURL": "http://clv.h-cdn.co/assets/15/15/640x640/square-1428596092-home-sweet-home-living-room-0515.jpg"
}' "http://localhost:8080/api/detectModerationLabels/external"
```

Sample request to detect explicit or suggestive adult content in an image by uploading an image

```
curl -X POST -H "Content-Type: multipart/form-data" -H "Cache-Control: no-cache"  -F "file=@<path/to/image.jpg>" "http://localhost:8080/api/detectModerationLabels/raw"
```

The following is an example response.

```
{
 "ModerationLabels": [
 {
 "Confidence": 99.24723052978516,
 "ParentName": "",
 "Name": "Explicit Nudity"
 },
 {
 "Confidence": 99.24723052978516,
 "ParentName": "Explicit Nudity",
 "Name": "Graphic Male Nudity"
 },
 {
 "Confidence": 88.25341796875,
 "ParentName": "Explicit Nudity",
 "Name": "Sexual Activity"
 }
 ]
}
```

## References

For more information on AWS Image Rekognition, Non-Storage API operations refer to [Amazon Rekognition](http://docs.aws.amazon.com/rekognition/latest/dg/how-it-works-non-storage.html)




