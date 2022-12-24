package filemanagement.service.authenticate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import filemanagement.model.UserModel;

import filemanagement.service.exception.JsonReadingException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadUserInfoFromJson {
    public JsonNode readFileJson() throws JsonReadingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(new File("C:\\Users\\lmaar\\OneDrive\\Desktop\\FileManagement\\fileManagementSystem\\userData.json"));
        } catch (IOException e) {
            throw new JsonReadingException(e.getMessage());
        }
        return root;
    }
    public List<UserModel> getUserInfoFromJson() throws JsonReadingException {
        List<UserModel> userModels = new ArrayList<>();
        JsonNode usersNode = readFileJson().path("users");
        Iterator<JsonNode> elements = usersNode.elements();
        while (elements.hasNext()) {
            JsonNode user = elements.next();
            UserModel userModel =  UserModel.getInstance();
            userModel.setName(user.path("name").asText());
            userModel.setId(user.path("id").asInt());
            userModel.setPassword(user.path("password").asText());
            userModels.add(userModel);
        }
        return userModels;
    }


}
