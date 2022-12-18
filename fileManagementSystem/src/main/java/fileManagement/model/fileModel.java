package fileManagement.model;
import java.nio.file.Path;

public class fileModel {
    private Path filePath;
    private String fileNameEncy;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String fileData;

    public fileModel(String fileNameEncy, String fileType, Path filePath, String fileSize,String fileName , String fileData) {
        this.fileNameEncy = fileNameEncy;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileSize=fileSize;
        this.fileData=fileData;

    }
    public fileModel() {
    }

    public String getFileNameEncy() {
        return fileNameEncy;
    }
    public void setFileNameEncy(String fileNameEncy) {
        this.fileNameEncy = fileNameEncy;
    }
    public Path getPath() {
        return filePath;
    }
    public void setPath(Path path) {
        this.filePath = path;
    }
    public String getType() {
        return fileType;
    }
    public void setType(String type) {
        this.fileType = type;
    }
    public String getFileSize() {
        return fileSize;
    }
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileData() {
        return fileData;
    }
    public void setFileData(String fileData) {
        this.fileData = fileData;
    }
    @Override
    public String toString() {
        return " files [FilePath "+ filePath + ", FileNameEncy = " + fileNameEncy + "FileName = "+ fileName +", FileType = " + fileType +", FileType = "+  fileSize +" , FileData = " + fileData+"]";
    }
}
