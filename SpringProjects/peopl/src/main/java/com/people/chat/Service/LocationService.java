package com.people.chat.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.people.chat.Model.MaxMindDBReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class LocationService {

    public static String getCity(String ip) throws IOException, GeoIp2Exception {

        
        // File database = resource.getFile();

        DatabaseReader dbReader = MaxMindDBReader.getReader();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String countryName = response.getCountry().getName();
        String cityName = response.getCity().getName();
        String postal = response.getPostal().getCode();
        String state = response.getLeastSpecificSubdivision().getName();

        return cityName + ", " + countryName;
        // return null;
    }

    public static void main(String[] args) throws IOException, GeoIp2Exception {
        System.out.println(getCity("27.63.132.22"));
    }
}