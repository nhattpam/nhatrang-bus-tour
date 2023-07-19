package com.grouppro.nhatrangbustour.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
@Slf4j
public class JsonFileReader {
    private final String LIST_ADMIN_EMAIL_PATH="src/main/resources/admin.json";
    private final String LIST_ADMIN_EMAIL_NAME="emails";
    public List<String> getAdminEmails(){
        List<String> result=new ArrayList<>();
        try {
            Reader reader = new FileReader(LIST_ADMIN_EMAIL_PATH);
            JSONParser jsonParser = new JSONParser();
            Object obj=jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray emailArray= (JSONArray) jsonObject.get(LIST_ADMIN_EMAIL_NAME);
            Iterator iterator=emailArray.iterator();
            while (iterator.hasNext()){
                result.add(iterator.next().toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
