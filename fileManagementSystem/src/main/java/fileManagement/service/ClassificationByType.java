/*package fileManagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileManagement.model.UserModel;
import fileManagement.service.exception.JsonReadingException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClassificationByType {

    public JsonNode read() throws JsonReadingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(new File("fileManagementSystem/userData.json"));
        } catch (IOException e) {
            throw new JsonReadingException(e.getMessage());
        }
        return root;
    }

    public List<UserModel> sortUsersByName() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<UserModel> users = mapper.readValue(new File("fileManagementSystem/userData.json"), new TypeReference<List<UserModel>>() {
        });

        List<UserModel> sortedUsers = users.stream()
                .sorted(Comparator.comparing(UserModel::getName))
                .collect(Collectors.toList());

        return sortedUsers;

    }
}*/