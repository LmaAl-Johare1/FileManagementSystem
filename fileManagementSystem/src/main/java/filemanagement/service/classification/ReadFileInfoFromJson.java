package filemanagement.service.classification;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import filemanagement.model.FileModel;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ReadFileInfoFromJson {

    public JsonNode readFileJsonForFiles() throws JsonReadingException, NoFileException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(new File("C:\\Users\\hp\\Desktop\\ADVANCEEEE\\FileManagementSystem\\fileManagementSystem\\fileData.json"));
        } catch (IOException e) {
            throw new JsonReadingException(e.getMessage());
        }
        return root;
    }
    public List<FileModel> getFileInfoFromJson() throws JsonReadingException, NoFileException {
        List<FileModel> fileModels = new ArrayList<>();
        JsonNode filesNode = readFileJsonForFiles().path("files");
        Iterator<JsonNode> elements = filesNode.elements();
        while (elements.hasNext()) {
            JsonNode file = elements.next();
            FileModel fileModel =  FileModel.getInstance();
            fileModel.setPath(Path.of(String.valueOf(file.path("filePath"))));
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