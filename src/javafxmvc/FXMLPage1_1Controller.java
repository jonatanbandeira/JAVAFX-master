package javafxmvc;


import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxmvc.model.dao.CidadeDAO;
import javafxmvc.model.dao.PetDAO;
import javafxmvc.model.dao.PorteDAO;
import javafxmvc.model.dao.RacaDAO;
import javafxmvc.model.dao.SexoDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Cidade;
import javafxmvc.model.domain.Pet;
import javafxmvc.model.domain.Porte;
import javafxmvc.model.domain.Raca;
import javafxmvc.model.domain.Sexo;

public class FXMLPage1_1Controller implements Initializable {

    @FXML
    private TextField textFieldPetNomePet;
    @FXML
    private TextField textFieldPetNomeDono;
    @FXML
    private TextField textFieldPetTelefone;
    @FXML
    private TextField textFieldPetEmail;
    @FXML
    private ComboBox<Raca> comboBoxRaca;
    @FXML
    private ComboBox<Sexo> comboBoxSexo;
    @FXML
    private ComboBox<Porte> comboBoxPorte;
    @FXML
    private ComboBox<Cidade> comboBoxCidade;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    
    
    private List<Sexo> listSexos;
    private List<Porte> listPortes;
    private List<Raca> listRacas;
    private List<Cidade> listCidades;
    
    private ObservableList<Sexo> observableListSexos;
    private ObservableList<Porte> observableListPortes;
    private ObservableList<Raca> observableListRacas;
    private ObservableList<Cidade> observableListCidades;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final RacaDAO RacaDAO = new RacaDAO();
    private final SexoDAO SexoDAO = new SexoDAO();
    private final PorteDAO PorteDAO = new PorteDAO();
    private final CidadeDAO CidadeDAO = new CidadeDAO();
    @FXML
    private Label labelPetNomePet;
    @FXML
    private Label labelPetNomeDono;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        RacaDAO.setConnection(connection);    
        carregarComboBoxRaca();
        SexoDAO.setConnection(connection);    
        carregarComboBoxSexo();
        PorteDAO.setConnection(connection);    
        carregarComboBoxPorte();
        CidadeDAO.setConnection(connection);    
        carregarComboBoxCidade();
    }
    
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

    public void carregarComboBoxRaca() {

        listRacas = RacaDAO.listar();
        observableListRacas = FXCollections.observableArrayList(listRacas);
        comboBoxRaca.setItems(observableListRacas);
    }

    public void carregarComboBoxSexo() {

        listSexos = SexoDAO.listar();
        observableListSexos = FXCollections.observableArrayList(listSexos);
        comboBoxSexo.setItems(observableListSexos);
    }
     
    public void carregarComboBoxPorte() {

        listPortes = PorteDAO.listar();
        observableListPortes = FXCollections.observableArrayList(listPortes);
        comboBoxPorte.setItems(observableListPortes);
    }
    
    
    public void carregarComboBoxCidade() {

        listCidades = CidadeDAO.listar();
        observableListCidades = FXCollections.observableArrayList(listCidades);
        comboBoxCidade.setItems(observableListCidades);
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        this.textFieldPetNomePet.setText(pet.getNomePet());
        this.textFieldPetNomeDono.setText(pet.getNomeDono());
        this.textFieldPetTelefone.setText(pet.getTelefone());
        this.textFieldPetEmail.setText(pet.getEmail());
        this.comboBoxPorte.getSelectionModel().getSelectedItem();
        this.comboBoxCidade.getSelectionModel().getSelectedItem();
        this.comboBoxRaca.getSelectionModel().getSelectedItem();
        this.comboBoxSexo.getSelectionModel().getSelectedItem();
        
        
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
    
    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            pet.setNomePet(textFieldPetNomePet.getText());
            pet.setNomeDono(textFieldPetNomeDono.getText());
            pet.setTelefone(textFieldPetTelefone.getText());
            pet.setEmail(textFieldPetEmail.getText());
            pet.setPorte(comboBoxPorte.getSelectionModel().getSelectedItem());
            pet.setCidade(comboBoxCidade.getSelectionModel().getSelectedItem());
            pet.setRaca(comboBoxRaca.getSelectionModel().getSelectedItem());
            pet.setSexo(comboBoxSexo.getSelectionModel().getSelectedItem());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
    
    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldPetNomePet.getText() == null || textFieldPetNomePet.getText().length() == 0) {
            errorMessage += "Nome Pet inválido!\n";
        }
        if (textFieldPetNomeDono.getText() == null || textFieldPetNomeDono.getText().length() == 0) {
            errorMessage += "Nome Dono inválido!\n";
        }
        if (textFieldPetTelefone.getText() == null || textFieldPetTelefone.getText().length() == 0) {
            errorMessage += "Telefone inválido!\n";
        }
        if (textFieldPetEmail.getText() == null || textFieldPetEmail.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
        }
        if (comboBoxPorte.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Porte inválido!\n";
        }
        if (comboBoxCidade.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cidade inválida!\n";
        }
        if (comboBoxRaca.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Rça inválida!\n";
        }
        if (comboBoxSexo.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Sexo inválido!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}

    
    
    
    
    
    
    
    

    
        
         
        
        
        
        
        
     
    
    

