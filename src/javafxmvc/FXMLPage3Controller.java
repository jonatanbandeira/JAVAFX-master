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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
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

public class FXMLPage3Controller implements Initializable {
    @FXML
    private ComboBox comboBoxRaca;
    @FXML
    private ComboBox comboBoxSexo;
    @FXML
    private ComboBox comboBoxPorte;
    @FXML
    private ComboBox comboBoxCidade;
    @FXML
    private Button buttonBuscar;
    @FXML
    private List<Pet> listPets;
    @FXML
    private List<Sexo> listSexos;
    @FXML
    private List<Porte> listPortes;
    @FXML
    private List<Raca> listRacas;
    @FXML
    private List<Cidade> listCidades;
    
    @FXML
    private ObservableList<Pet> observableListPets;
    @FXML
    private ObservableList<Sexo> observableListSexos;
    @FXML
    private ObservableList<Porte> observableListPortes;
    @FXML
    private ObservableList<Raca> observableListRacas;
    @FXML
    private ObservableList<Cidade> observableListCidades;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final RacaDAO RacaDAO = new RacaDAO();
    private final SexoDAO SexoDAO = new SexoDAO();
    private final PorteDAO PorteDAO = new PorteDAO();
    private final CidadeDAO CidadeDAO = new CidadeDAO();
    private final PetDAO PetDAO = new PetDAO();
    
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
            pet.setPorte((Porte) comboBoxPorte.getSelectionModel().getSelectedItem());
            pet.setCidade((Cidade) comboBoxCidade.getSelectionModel().getSelectedItem());
            pet.setRaca((Raca) comboBoxRaca.getSelectionModel().getSelectedItem());
            pet.setSexo((Sexo) comboBoxSexo.getSelectionModel().getSelectedItem());

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

        if (comboBoxPorte.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Porte inválido!\n";
        }
        if (comboBoxCidade.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cidade inválida!\n";
        }
        if (comboBoxRaca.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Raça inválida!\n";
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
    @FXML
    public void handleButtonBuscar() throws IOException {
    Pet pet2 = new Pet();
    comboBoxRaca.getSelectionModel().getSelectedItem();
    comboBoxSexo.getSelectionModel().getSelectedItem();
    comboBoxCidade.getSelectionModel().getSelectedItem();
    comboBoxPorte.getSelectionModel().getSelectedItem();
    
            boolean buttonConfirmarClicked = showFXMLPage3_1(pet2);
            if (buttonConfirmarClicked) {
                PetDAO.buscar(pet2);
                carregarComboBoxRaca();
            }
        }
    
    
     public boolean showFXMLPage3_1(Pet pet) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLPage3_1Controller.class.getResource("FXMLPage3_1.fxml"));
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
        FXMLPage3_1Controller controller = loader.getController();
        controller.setDialogStage(dialogStage);
        

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    
     }
}
     
