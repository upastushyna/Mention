package com.mention.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public final class AmazonS3Configuration {

  public static final AmazonS3 S3_BUILDER = AmazonS3ClientBuilder.standard()
      .withCredentials(new AWSStaticCredentialsProvider(
          new BasicAWSCredentials(
              "AKIAJOCEWSXTNDQY2STA",
              "tm5KR0myirsZTXaKAfdv2u4mUQeIOOT1vL/v52N7")))
      .withRegion("eu-central-1") .build() ;

  public static final String BUCKET_NAME = "mention-project";
}
