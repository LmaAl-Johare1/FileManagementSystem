package filemanagement.permission.service.classification;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.log.Loggers;
import filemanagement.permission.IPermission;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ClassifyBySize implements IClassify, IPermission {
    @Override
    public List<FileModel> classify() throws JsonReadingException, NoDataInFileJsonException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> fileModels = read.getFileInfoFromJson();
        if (fileModels.isEmpty()) {
            throw new NoDataInFileJsonException("No data in file");
        }
        List<FileModel> sortedFileModels = new ArrayList<>(fileModels);

        Collections.sort(sortedFileModels, new Comparator<FileModel>() {
            @Override
            public int compare(FileModel object1, FileModel object2) {
                return object1.getFileName().compareTo(object2.getFileName());
            }
        });

        return sortedFileModels;
    }
    public void writeFileJsonForFiles() throws IOException, NoDataInFileJsonException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        root = read.readFileJsonForFiles();
        ((ObjectNode) root).set("files", mapper.valueToTree(classify()));
<<<<<<< HEAD
        mapper.writeValue(new FileWriter("./files.json"), root);
=======
        mapper.writeValue(new FileWriter("fileManagementSystem/files.json"+".json"), root);
>>>>>>> dafddc778f87ce524d4854d785b8462191317beb
        Loggers.logInfo("Added to JSON file");
    }

    @Override
    public void permission() throws IOException, NameNotFoundException, NoDataInFileJsonException {
        writeFileJsonForFiles();
        System.out.println("Sort by Size :" + classify());
    }

}