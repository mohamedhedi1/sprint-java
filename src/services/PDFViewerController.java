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

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;

public class PDFViewerController {
    
    @FXML
    private WebView webView;
    
    private File pdfFile;
    
    public void setPdfFile(File file) {
        pdfFile = file;
    }
    
    public void setPdfFilePath(String filePath) {
        pdfFile = new File(filePath);
    }
    
    public void initialize() {
        if (pdfFile != null) {
            try {
                webView.getEngine().load(pdfFile.toURI().toURL().toString());
            } catch (IOException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("An error occurred while loading the PDF file.");
                alert.showAndWait();
            }
        }
    }

}
