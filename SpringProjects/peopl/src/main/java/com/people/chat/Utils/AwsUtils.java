package com.people.chat.Utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.DefaultRequest;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.http.AmazonHttpClient;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudformation.model.Output;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.google.gson.JsonObject;
import com.people.chat.Service.ErrorResponseHandler;

import org.springframework.web.multipart.MultipartFile;

public class AwsUtils {
    static BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA2CWWH2YPGVUXDNWQ",
            "3T4HOA8WZL/i5bbn8VlmPQyshMQvWMDBMwzznd/o");

    static AmazonS3 s3client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.AP_SOUTH_1).build();

    public static void makePostReqAPIGateway(String url, JsonObject jsonObject) {
        InputStream content = new ByteArrayInputStream(jsonObject.toString().getBytes());

        DefaultRequest<?> request = new DefaultRequest<>("execute-api");
        request.setHttpMethod(HttpMethodName.POST);
        request.setEndpoint(URI.create(url));
        request.setContent(content);
        request.addHeader("Content-Type", "application/json");

        AWS4Signer signer = new AWS4Signer();
        signer.setRegionName("us-east-1");
        signer.setServiceName(request.getServiceName());
        signer.sign(request, awsCreds);

        new AmazonHttpClient(new ClientConfiguration()).requestExecutionBuilder()
                .executionContext(new ExecutionContext(true)).request(request)
                .errorResponseHandler((new ErrorResponseHandler())).execute(new HttpResponseHandler<Output>() {

                    @Override
                    public Output handle(HttpResponse response) throws Exception {
                        return null;
                    }

                    @Override
                    public boolean needsConnectionLeftOpen() {
                        return false;
                    }
                });
    }

    public static String uploadProfilePic(MultipartFile profilePic, String socialId) {
        String bucketName = "profileimg";
        File convFile;
        try {
            convFile = CommonUtils.convertMultiPartToFile(profilePic);
            String fileName = "profilePic_" + System.currentTimeMillis() + ".jpeg";
            String key = socialId + "/" + fileName;
            System.out.println("Key = " + key);
            s3client.putObject(new PutObjectRequest(bucketName, key, convFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            convFile.delete();
            return fileName;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void uploadProfilePic(String imgUrl, String profileId) {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new URL(imgUrl).openStream());
            String bucket = "profileimg";
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpeg"); // TODO - ADD CONTENT LENGTH
            String key = profileId + "/profilePic.jpeg";
            PutObjectRequest por = new PutObjectRequest(bucket, key, in, meta);
            PutObjectResult result = s3client.putObject(por.withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    
    //-----------------------------------------------------CV UPLOAD DOWN BELOW-------------------------------------------
    
    public static boolean uploadCV(MultipartFile cv, String socialId) {
        String bucketName = "profileimg";
        File convFile;
        try {
            convFile = CommonUtils.convertMultiPartToFile(cv);
            String fileName = "cv_" + socialId + ".pdf";
            String key = socialId + "/" + fileName;
            System.out.println("Key = " + key);
            s3client.putObject(new PutObjectRequest(bucketName, key, convFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            convFile.delete();
            System.out.println(key);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
 
//    public static boolean deleteCV(String CVUrl) 
//    {
//    	int count=0;
//    	int i=0;
//    	while(true) 
//    	{
//    		if(CVUrl.charAt(i)=='/')
//    			count++;
//    		i++;
//    		if(count==3)
//    			break;	
//    		
//    	}
//    	String key=CVUrl.substring(i);
//    	System.out.println(key);
//    	try 
//    	{
//    		s3client.deleteObject("profileimg", key);
//    		return true;
//    		
//    	}
//    	catch (Exception e) {
//    		e.printStackTrace();
//    		return false;
//			// TODO: handle exception
//		}
//    		
//    }
    
    public static boolean isCVUploaded(String socialId) 
    {
    	boolean status=false;
    	 String bucketName = "profileimg";
    	 String fileName = "cv_" + socialId + ".pdf";
         String key = socialId + "/" + fileName;
    	 
         status = s3client.doesObjectExist(bucketName,key);
    	
    	
    	return status;
    	
    }
    
    //--------------------------------------------------Registered Company Image Upload Down Below-----------------------
    
    public static boolean uploadCompanyImg(MultipartFile image, int regcompId) {
        String bucketName = "companyimgbucket";
        File convFile;
        try {
            convFile = CommonUtils.convertMultiPartToFile(image);
            String fileName = "profileImg.jpeg";
            String key = regcompId + "/" + fileName;
            System.out.println("Key = " + key);
            s3client.putObject(new PutObjectRequest(bucketName, key, convFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            convFile.delete();
            System.out.println(key);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    public static boolean isCompanyImgUploaded(int regCompId ) 
    {
    	boolean status=false;
    	String bucketName = "companyimgbucket";
    	String fileName = "profileImg.jpeg";
        String key = regCompId + "/" + fileName;
    	
        status = s3client.doesObjectExist(bucketName,key);
    	return status;
    	
    }
    
    
    
    
}