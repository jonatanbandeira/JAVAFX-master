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
import javafxmvc.model.dao.CidadeDAO;
import javafxmvc.model.dao.PetDAO;
import javafxmvc.model.dao.SexoDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Cidade;
import javafxmvc.model.domain.Pet;
import javafxmvc.model.domain.Sexo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FXMLPage5Controller implements Initializable {
    
       @FXML
       private TableView<Pet> tableViewPet;
       @FXML
       private TableColumn<Pet, String> tableColumnPetCidade;
       @FXML
       private TableColumn<Pet, Integer> tableColumnPetQtdPets;
       @FXML
       private TableColumn<Pet, String> tableColumnPetSexo;
       @FXML
       private Button buttonImprimir;
       
       private List<Pet> listPet;
       private ObservableList<Pet> observableListPet;
       
       private List<Sexo> listSexo;
       private ObservableList<Sexo> observableListSexo;
       
       private List<Cidade> listCidade;
       private ObservableList<Cidade> observableListCidade;
       
       //Atributos para manipulação de Banco de Dados
       private final Database database = DatabaseFactory.getDatabase("postgresql");
       private final Connection connection = database.conectar();
       
       private final PetDAO petDAO = new PetDAO();
       private final SexoDAO sexoDAO = new SexoDAO();
       private final CidadeDAO cidadeDAO = new CidadeDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        petDAO.setConnection(connection);
        carregarTableViewPets();
    }
    
    public void carregarTableViewPets(){
        tableColumnPetCidade.setCellValueFactory(new PropertyValueFactory<>("cdCidade"));
      //  tableColumnPetQtdPets.setCellValueFactory(new PropertyValueFactory<>("quantidadeSexo"));
        tableColumnPetSexo.setCellValueFactory(new PropertyValueFactory<>("cdSexo"));
        
        listPet = petDAO.listar();
        observableListPet = FXCollections.observableArrayList(listPet);
        tableViewPet.setItems(observableListPet);
        
        listSexo = sexoDAO.listar();
        observableListSexo = FXCollections.observableArrayList(listSexo);
       // tableViewPet.setItems(observableListSexo);
        
        listCidade = cidadeDAO.listar();
        observableListCidade = FXCollections.observableArrayList(listCidade);
      //  tableViewPet.setItems(observableListCidade);
    }
    
    public void buttonImprimir() throws JRException{
        URL url = getClass().getResource("/javafxmvc/relatorios/JAVAFXMVCRelatorioPet.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);//null: caso não existam filtros
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        jasperViewer.setVisible(true);
    }
    
}