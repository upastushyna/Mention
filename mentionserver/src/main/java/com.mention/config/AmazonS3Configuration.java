package com.mention.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@PropertySource("config.properties")
@Component
public final class AmazonS3Configuration {

  @Value("${Access_key}")
  private String accessKey;

  @Value("${Secret_key}")
  private String secretKey;

  public static final String BUCKET_NAME = "mention-project";

  public AmazonS3 getAmazonS3() {
      return AmazonS3ClientBuilder.standard()
          .withCredentials(new AWSStaticCredentialsProvider(
              new BasicAWSCredentials(accessKey,
                  secretKey)))
          .withRegion("eu-central-1") .build() ;
  }
}
