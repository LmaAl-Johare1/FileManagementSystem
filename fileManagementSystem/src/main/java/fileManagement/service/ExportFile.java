package filemanagement.service;

import filemanagement.service.exception.NoFileException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExportFile {

    public static void exportFile() throws NoFileException {
        ReadFile.PrintFileName();
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
                    if (!filePath.endsWith(".txt")) {
                        filePath = filePath + ".txt";
                    }
                    file = new File(filePath);

                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(data.getBytes());
                        fileOutputStream.close();
                        frame.setVisible(false);
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + e.getMessage());
                    }
                }
            }
        }




