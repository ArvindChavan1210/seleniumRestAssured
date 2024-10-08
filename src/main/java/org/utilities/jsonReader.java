package org.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class jsonReader {

    public static HashMap<String, Object> readData(String requriedField,String filepath) {
        HashMap<String, Object> requiredResult = null;
        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(fis);
            requiredResult = new HashMap<>();
            if (jsonNode.has(requriedField)) {
                if (!jsonNode.get(requriedField).isObject() && !jsonNode.get(requriedField).isArray()) {
                    requiredResult.put(requriedField, jsonNode.get(requriedField).asText());
                } else {
                    requiredResult.put(requriedField, findField(jsonNode.get(requriedField)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return requiredResult;
    }

    private static Object findField(JsonNode node){
        if(node.isObject()){
         HashMap<String, Object> resultMap=new HashMap<>();
         node.fieldNames().forEachRemaining(field-> {
             resultMap.put(field,findField(node.get(field)));
         });
         return resultMap;
        } else if(node.isArray()) {
            ArrayList<Object> resultList=new ArrayList<>();
            for(JsonNode arrayElement:node){
                resultList.add(findField(arrayElement));
            }
            return resultList;
        } else {
            return node.asText();
        }

    }

    public static void main(String[] args) {

    }
}
