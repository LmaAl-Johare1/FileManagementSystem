package filemanagement.permission.service.filerepository;

import filemanagement.exception.NoFileException;
import filemanagement.permission.IPermission;
import filemanagement.permission.service.ReadFile;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExportFile  implements IPermission {

    public static void exportFile() throws NoFileException{
        ReadFile.printFileName();
        ReadFile.printFileData();
        String data = ReadFile.fileData;
                JFrame frame = new JFrame("File Chooser ");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(true);
                frame.add(fileChooser);
                frame.setSize(500, 500);
                frame.setVisible(true);
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filePath = file.getAbsolutePath();
                    file = new File(filePath);

                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(data.getBytes());
                        fileOutputStream.close();
                        frame.setVisible(false);
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + e.getMessage());
                    }
                }
            }

    @Override
    public void permission() throws NoFileException {
        exportFile();
        System.out.println("Enter File number you want to export it please : ");

    }
}




