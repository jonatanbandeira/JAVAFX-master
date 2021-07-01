package javafxmvc;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxmvc.model.dao.PetDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Pet;


public class FXMLPage5Controller implements Initializable {
    
       @FXML
       private TableView<Pet> tableViewPet;
       @FXML
       private TableColumn<Pet, String> tableColumnPetCidade;
       @FXML
       private TableColumn<Pet, Integer> tableColumnPetQtdPets;
       @FXML
       private TableColumn<Pet, Integer> tableColumnPetQtdFemea;
       @FXML
       private TableColumn<Pet, Integer> tableColumnPetQtdMacho;
       @FXML
       private Button buttonImprimir;
       
       private List<Pet> listPet;
       private ObservableList<Pet> observableListPet;
       
       //Atributos para manipulação de Banco de Dados
       private final Database database = DatabaseFactory.getDatabase("postgresql");
       private final Connection connection = database.conectar();
       private final PetDAO petDAO = new PetDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        petDAO.setConnection(connection);
        
    }
    
    public void carregarTableViewPets(){
        tableColumnPetCidade.setCellValueFactory(new PropertyValueFactory<>("cdCidade"));
        tableColumnPetQtdPets.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnPetQtdFemea.setCellValueFactory(new PropertyValueFactory<>(""));
        tableColumnPetQtdMacho.setCellValueFactory(new PropertyValueFactory<>(""));
        
        listPet = petDAO.listar();
        
        observableListPet = FXCollections.observableArrayList(listPet);
        tableViewPet.setItems(observableListPet);
    }
    
}