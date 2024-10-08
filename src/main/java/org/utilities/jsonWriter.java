package org.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class jsonWriter {

    public static void updateJson(String nodeName, String updateWith,String filePath){
        try {
            File f = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(f);
            if(jsonNode.isObject()) {
                ObjectNode objectNode=(ObjectNode) jsonNode;
                objectNode.put(nodeName,updateWith);
            } else if(jsonNode.isArray()) {
                ArrayNode arrayNode=(ArrayNode) jsonNode;
                for(int i=0; i<arrayNode.size(); i++){
                    JsonNode arrayElement=arrayNode.get(i);
                    if(arrayElement.isObject() && arrayElement.has(nodeName)) {
                        ObjectNode objectNode=(ObjectNode) arrayElement;
                        objectNode.put(nodeName,updateWith);
                    }
                }
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f,jsonNode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        updateJson("firstname","John","src/test/java/org/restAssured/herokuAPI/data/createBookingBody.json");
    }

}
