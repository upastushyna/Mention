package com.mention.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AmazonS3Configuration {

  public static final String BUCKET_NAME = "mention-project";

  public static AmazonS3 getAmazonS3() throws IOException {
    Properties prop = new Properties();
    InputStream input = new FileInputStream("mentionserver/src/main/resources/config.properties");
    prop.load(input);
    String key = prop.getProperty("Access_key");
    String secret = prop.getProperty("Secret_key");

    AmazonS3 s3Builder = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(
                key,
                secret)))
        .withRegion("eu-central-1") .build() ;
    return s3Builder;
  }
}
