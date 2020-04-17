/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLReservarPistaController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<?> tableViewBooking;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inspeccionar(ActionEvent event) {
    }

    @FXML
    private void reservar(ActionEvent event) {
    }
    
}
