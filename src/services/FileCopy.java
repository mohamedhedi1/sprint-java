/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Mohamed
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopy {

    public static void copyFile(File sourceFile, File destinationFile) throws IOException {
        Path sourcePath = sourceFile.toPath();
        Path destinationPath = destinationFile.toPath();
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }
}