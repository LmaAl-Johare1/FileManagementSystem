package filemanagement;

import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.service.classification.*;
import filemanagement.exception.NameNotFoundException;
import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException, NameNotFoundException, NoDataInFileJsonException {
        ReadFileInfoFromJson reads = new ReadFileInfoFromJson();
        System.out.println(reads.getFileInfoFromJson());
        ClassifyByType classifyByType=new ClassifyByType();
        System.out.println("Sort by Type :" +classifyByType.classify());
        System.out.println(reads.getFileInfoFromJson());
        ClassifyBySize classifyBySize = new ClassifyBySize();
        System.out.println("Sort by Size :" + classifyBySize.classify());
        System.out.println(reads.getFileInfoFromJson());
        ClassifyByCustomCategory classifyByCustomCategory= new ClassifyByCustomCategory();
        System.out.println("Sort by Custom Category :" +classifyByCustomCategory.classify());
        System.out.println(reads.getFileInfoFromJson());
        ClassifyByName classifyByName = new ClassifyByName();
        System.out.println("Sort by Name :" +classifyByName.classify());

}
}
