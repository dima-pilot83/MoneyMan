package dima;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dima.controller.ServerController;
import dima.entity.Pipeline;
import dima.entity.Task;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dima.entity.enums.DoAction;

import java.io.IOException;
import java.util.List;

import static org.hibernate.internal.util.ConfigHelper.getResourceAsStream;

/**
 * Created by Dmitriy on 18.05.2018.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //JsonNode root = mapper.readTree(getResourceAsStream("/person.yml")) = string;

     ServerController s =new ServerController();
     s.handleRequest("/person.yml");
    }
}

