/*package javafxmvc;

import java.net.URL;
import java.sql.Connection;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafxmvc.model.dao.PetDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
*/

//public class FXMLPage4Controller implements Initializable {

/*    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;
    
    private final ObservableList<String> observableListMeses = FXCollections.observableArrayList();

    //Atributos para manipulaÃ§Ã£o de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final PetDAO vendaDAO = new VendaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Map<String, Integer> dados = PetDAO.listarQuantidadeRacaCadastradas();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (Map.Entry<String, Integer> dadosItem : dados.entrySet()) {

                String raca = dadosItem.getKey().toString();
                Integer quantidade;

                quantidade = (Integer) dadosItem.getValue();

                series.getData().add(new XYChart.Data<>(raca, quantidade));
        }
        barChart.getData().add(series);
    }*/
//}
package javafxmvc;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafxmvc.model.dao.PetDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;

public class FXMLPage4Controller implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;
    
    private final ObservableList<String> observableListMeses = FXCollections.observableArrayList();

    //Atributos para manipulaÃ§Ã£o de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final PetDAO petDAO = new PetDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        petDAO.setConnection(connection); 
        Map<String, Integer> dados = petDAO.listarQuantidadeRacaCadastradas();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (Map.Entry<String, Integer> dadosItem : dados.entrySet()) {

                String raca = dadosItem.getKey().toString();
                Integer quantidade;

                quantidade = (Integer) dadosItem.getValue();

                series.getData().add(new XYChart.Data<>(raca, quantidade));
        }
        barChart.getData().add(series);
    }
}
