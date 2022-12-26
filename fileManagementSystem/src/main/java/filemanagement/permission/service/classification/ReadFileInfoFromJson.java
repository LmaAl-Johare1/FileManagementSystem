package filemanagement.permission.service.classification;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ReadFileInfoFromJson {
    public JsonNode readFileJsonForFiles() throws JsonReadingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
<<<<<<< HEAD
            root = mapper.readTree(new File("./files.json"));
=======
            root = mapper.readTree(new File("C:\\Users\\Msi\\Desktop\\finaaaaal\\FileManagementSystem\\files.json"));
>>>>>>> dafddc778f87ce524d4854d785b8462191317beb
        } catch (IOException e) {
            throw new JsonReadingException(e.getMessage());
        }
        return root;
    }
    public List<FileModel> getFileInfoFromJson() throws JsonReadingException {
        List<FileModel> fileModels = new ArrayList<>();
        JsonNode filesNode = readFileJsonForFiles().path("files");
        for (JsonNode fileArray : filesNode) {
            JsonNode file = fileArray.get(0);
            FileModel fileModel = new FileModel();
            fileModel.setPath(Path.of(file.path("path").asText()));
            fileModel.setFileNameEncy(file.path("fileNameEncy").asText());
            fileModel.setFileName(file.path("fileName").asText());
            fileModel.setFileType(file.path("fileType").asText());
            fileModel.setFileSize(file.path("fileSize").asText());
            fileModel.setFileData(file.path("fileDate").asText());
            fileModels.add(fileModel);
        }
        return fileModels;
    }
}