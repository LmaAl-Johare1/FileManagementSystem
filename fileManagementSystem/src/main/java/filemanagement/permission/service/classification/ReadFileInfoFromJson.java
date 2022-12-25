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
            root = mapper.readTree(new File("fileManagementSystem/files.json"));
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
            FileModel fileModel =  FileModel.getInstance();
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