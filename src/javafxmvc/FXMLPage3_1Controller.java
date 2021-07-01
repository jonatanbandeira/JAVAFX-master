package javafxmvc;


import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxmvc.model.dao.PetDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Pet;

public class FXMLPage3_1Controller implements Initializable {
    
    @FXML
    private TableView<Pet> tableViewPets;
    @FXML
    private TableColumn<Pet, String> tableColumnPetNomePet;
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
    private Label labelPetNomePet;   
    @FXML
    private Label labelPetNomeDono; 
    
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
    

    @FXML
    private ObservableList<Pet> observableListPets;
    
     //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final PetDAO PetDAO = new PetDAO();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PetDAO.setConnection(connection);    
        carregarTableViewPets();
        
         // Limpando a exibição dos detalhes do cliente
        selecionarItemTableViewPets(null);

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
        tableViewPets.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewPets(newValue));
    }

    public void carregarTableViewPets() {
        tableColumnPetNomePet.setCellValueFactory(new PropertyValueFactory<>("nomePet"));

        listPets = PetDAO.listar();
        //listCidades = CidadeDAO.listar();
        //listPortes = PorteDAO.listar();
        //listRacas = RacaDAO.listar();
        //listSexos = SexoDAO.listar();
        
        //listPets = PetDAO.buscar();
        //listPets = PetDAO.buscar(carregarComboBoxSexo);
        //listPets = PetDAO.buscar(carregarComboBoxRaca);
        //listPets = PetDAO.buscar(carregarComboBoxCidade);
        //listPets = PetDAO.buscar(carregarComboBoxPorte);
        
        
        observableListPets = FXCollections.observableArrayList(listPets);
        tableViewPets.setItems(observableListPets);
        
    }
    
     public void selecionarItemTableViewPets(Pet pet) {
        if (pet != null) {
            labelPetTelefone.setText(pet.getTelefone());
            labelPetEmail.setText(pet.getEmail());
            labelPetPorte.setText(String.valueOf(pet.getPorte()));
            labelPetSexo.setText(String.valueOf(pet.getSexo()));
            labelPetCidade.setText(String.valueOf(pet.getCidade()));
            labelPetRaca.setText(String.valueOf(pet.getRaca()));
            labelPetNomePet.setText(pet.getNomePet());
            labelPetNomeDono.setText(pet.getNomeDono());     
        } else {
            labelPetTelefone.setText("");
            labelPetEmail.setText("");
            labelPetPorte.setText("");
            labelPetSexo.setText("");
            labelPetCidade.setText("");
            labelPetRaca.setText("");
            labelPetNomePet.setText("");
            labelPetNomeDono.setText("");
            
        }
     }
}

    