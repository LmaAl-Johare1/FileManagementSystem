package filemanagement.service;

import filemanagement.model.FileModel;
import filemanagement.service.exception.UnableToReadFile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Import {
    public static void addFile(Path path, String filename, String name, String fileData, JSONArray filesArray, JSONObject data, String fileSize) throws UnableToReadFile {
        FileModel fileModel =  FileModel.getInstance();
        fileModel.setPath(path);
        fileModel.setFileName(filename);
        fileModel.setFileNameEncy(GetFile.encryptName(filename+"."+GetFile.getExtension(name)));
        fileModel.setFileData(fileData);
        fileModel.setFileType(GetFile.getExtension(name));
        fileModel.setFileSize(fileSize);
        ArrayList<FileModel> array = new ArrayList<>();
        array.add( fileModel.getInstance(fileModel.getFileNameEncy() + "", fileModel.getFileType() + "", Path.of(fileModel.getPath() + ""), fileModel.getFileSize() + "", fileModel.getFileName() + "", fileModel.getFileData() + ""));
        filesArray.put(array);
        try (FileWriter fw = new FileWriter("./files.json")) {
            fw.write(data.toString());
        }
        catch (IOException e) {
            throw new UnableToReadFile();
        }
    }
}
