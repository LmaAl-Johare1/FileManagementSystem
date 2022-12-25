package filemanagement.permission.service.classification;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoFileException;

import java.util.List;

public interface IClassify {
    List<FileModel> classify() throws JsonReadingException, NoFileException, NameNotFoundException, NoDataInFileJsonException;
}