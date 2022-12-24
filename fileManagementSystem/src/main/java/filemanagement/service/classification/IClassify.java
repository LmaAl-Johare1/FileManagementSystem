package filemanagement.service.classification;
import filemanagement.model.FileModel;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NameNotFoundException;
import filemanagement.service.exception.NoFileException;

import java.util.List;

public interface IClassify {
    List<FileModel> classify() throws JsonReadingException, NoFileException, NameNotFoundException;
}