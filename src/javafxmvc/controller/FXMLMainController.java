package javafxmvc.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
//import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FXMLMainController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void home(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void page1(MouseEvent event) {
        loadPage("/javafxmvc/view/FXMLCadastro.fxml");
    }

    @FXML
    private void page3(MouseEvent event) {
        loadPage("/javafxmvc/view/FXMLMatchPet.fxml");
    }

    @FXML
    private void page4(MouseEvent event) {
        loadPage("/javafxmvc/view/FXMLGrafico.fxml");
    }

    @FXML
    private void page5(MouseEvent event) {
        loadPage("/javafxmvc/view/FXMLRelatorio.fxml");
    }

    @FXML
    private void page6(MouseEvent event) {
        loadPage("/javafxmvc/view/FXMLSobre.fxml");
    }
    
    
    @FXML
    private void loadPage(String page){
        Parent root = null;
        
        try {
            root =javafx.fxml.FXMLLoader.load(getClass().getResource(page));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bp.setCenter(root);
    }
    
}
