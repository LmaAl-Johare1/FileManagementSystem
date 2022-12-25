package filemanagement.permission.service.classification;
import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.IPermission;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassifyBySize implements IClassify, IPermission {
    private static ClassifyBySize instance;

    private ClassifyBySize() {}
    public static synchronized ClassifyBySize getInstance() {
        if (instance == null) {
            instance = new ClassifyBySize();
        }
        return instance;
    }
    @Override
    public List<FileModel> classify() throws JsonReadingException, NoFileException, NoDataInFileJsonException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> fileModels = read.getFileInfoFromJson();
        if (fileModels.isEmpty()) {
            throw new NoDataInFileJsonException("No data in file");
        }
        List<FileModel> sortedFileModels = new ArrayList<>(fileModels);

        Collections.sort(sortedFileModels, new Comparator<FileModel>() {
            @Override
            public int compare(FileModel o1, FileModel o2) {
                return o1.getFileName().compareTo(o2.getFileName());
            }
        });

        return sortedFileModels;
    }

    @Override
    public void permission() throws NoFileException, NameNotFoundException, JsonReadingException, NoDataInFileJsonException {
        System.out.println("Sort by Size :" + classify());
    }
}