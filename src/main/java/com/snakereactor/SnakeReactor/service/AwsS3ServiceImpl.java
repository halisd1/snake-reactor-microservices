package com.snakereactor.SnakeReactor.service;

import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

import java.io.File;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    private static Logger logger = LoggerFactory.getLogger(AwsS3ServiceImpl.class);

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 amazonS3Client;

    public void uploadFile(String fileDirectory, String fileName){
        File chartPng = new File(fileDirectory);

        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, chartPng));
    }
}
