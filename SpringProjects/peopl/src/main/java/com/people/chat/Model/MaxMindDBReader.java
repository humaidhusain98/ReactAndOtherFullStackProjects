package com.people.chat.Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MaxMindDBReader {

    private static DatabaseReader dbReader = null;

    private MaxMindDBReader() {

    }

    public static DatabaseReader getReader() {
        if (dbReader == null) {
            try {
                File database = new File("/var/app/current/GeoLite2-City.mmdb");

                // Resource resource = new ClassPathResource("GeoLite2-City.mmdb");
                // InputStream inputStream = resource.getInputStream();
                // File database = resource.getFile();

                dbReader = new DatabaseReader.Builder(database).fileMode(com.maxmind.db.Reader.FileMode.MEMORY_MAPPED)
                        .withCache(new CHMCache()).build();
                // resource = null;
                //System.gc();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dbReader;
    }

}