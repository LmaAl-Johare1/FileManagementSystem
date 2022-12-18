package fileManagement.model;
import java.nio.file.Path;

public class file {
    private Path filepath;
    private String fileName;
    private String filetype;

    public file(String fileName, String filetype, Path filepath) {
        this.fileName=fileName;
        this.filetype=filetype;
        this.filepath=filepath;

    }

    public file() {

    }
    //private String size;




    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Path getPath() {
        return filepath;
    }

    public void setPath(Path path) {
        this.filepath = path;
    }

    public String getType() {
        return filetype;
    }

    public void setType(String type) {
        this.filetype = type;
    }

    /*public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }*/
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return " files [FilePath "+ filepath + ", FileName = " + fileName + ", FileType = " + filetype + " ]";
    }
}
