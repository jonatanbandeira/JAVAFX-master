/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafxmvc.model.domain.Pet;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLPage3_1Controller implements Initializable {
    
    @FXML
    private TableView<Pet> tableViewPets;
    @FXML
    private TableColumn<Pet, String> tableColumnPetNomePet;
    @FXML
    private TableColumn<Pet, String> tableColumnPetNomeDono;
    @FXML
    private Label labelPetTelefone;
    @FXML
    private Label labelPetEmail;   
    @FXML
    private Label labelPetPorte;
    @FXML
    private Label labelPetSexo;
    @FXML
    private Label labelPetCidade;   
    @FXML
    private Label labelPetRaca; 
    
    @FXML
    private List<Pet> listPets;

     private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Pet pet;
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Pet getPet() {
        return this.pet;
    }
    
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
