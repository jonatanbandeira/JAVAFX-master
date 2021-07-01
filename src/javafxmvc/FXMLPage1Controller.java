package javafxmvc;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxmvc.model.dao.PetDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Pet;

public class FXMLPage1Controller implements Initializable {

    @FXML
    private TableView<Pet> tableViewPets;
    @FXML
    private TableColumn<Pet, String> tableColumnPetNomePet;
    @FXML
    private TableColumn<Pet, String> tableColumnPetNomeDono;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
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
        tableColumnPetNomeDono.setCellValueFactory(new PropertyValueFactory<>("nomeDono"));

        listPets = PetDAO.listar();

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
        } else {
            labelPetTelefone.setText("");
            labelPetEmail.setText("");
            labelPetPorte.setText("");
            labelPetSexo.setText("");
            labelPetCidade.setText("");
            labelPetRaca.setText("");
        }
     }

    @FXML
    public void handleButtonInserir() throws IOException {
    Pet pet2 = new Pet();
    boolean buttonConfirmarClicked = showFXMLPage1_1(pet2);
        if (buttonConfirmarClicked) {
            PetDAO.inserir(pet2);
            carregarTableViewPets();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException {
    Pet pet2 = tableViewPets.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
    if (pet2 != null) {
            boolean buttonConfirmarClicked = showFXMLPage1_1(pet2);
            if (buttonConfirmarClicked) {
                PetDAO.alterar(pet2);
                carregarTableViewPets();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Pet pet2 = tableViewPets.getSelectionModel().getSelectedItem();
        if (pet2 != null) {
            PetDAO.remover(pet2);
            carregarTableViewPets();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

      public boolean showFXMLPage1_1(Pet pet) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLPage1_1Controller.class.getResource("FXMLPage1_1.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        //Especifica a modalidade para esta fase . Isso deve ser feito antes de fazer o estágio visível. A modalidade pode ser: Modality.NONE , Modality.WINDOW_MODAL , ou Modality.APPLICATION_MODAL 
        //dialogStage.initModality(Modality.WINDOW_MODAL);//WINDOW_MODAL (possibilita minimizar)
        
        //Especifica a janela do proprietário para esta página, ou null para um nível superior.
        //dialogStage.initOwner(null); //null deixa a Tela Principal livre para ser movida
        //dialogStage.initOwner(this.tableViewClientes.getScene().getWindow()); //deixa a tela de Preenchimento dos dados como prioritária
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLPage1_1Controller controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPet(pet);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }
}

  
    
    