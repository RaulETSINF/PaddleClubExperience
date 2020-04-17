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
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLDatosBancariosController implements Initializable {

    @FXML
    private TextField targeta_Input;
    @FXML
    private Text targeta_Text;
    @FXML
    private TextField svc_Input;
    @FXML
    private Text svc_Text;
    
    private Text auxTrg = new Text("");
    private Text auxSvc = new Text("");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        targeta_Input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (((targeta_Input.getText().length() != 16)) && (targeta_Input.getText().length() != 0)) {
                targeta_Text.setText("Ha de contener 16 Dígitos");
            } else if (targeta_Input.getText().length() == 16) {
                targeta_Text.setText("Targeta Valida");
            } else {
                targeta_Text.setText("");
            }
        });
        svc_Input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (((svc_Input.getText().length() != 3)) && (svc_Input.getText().length() != 0)) {
                svc_Text.setText("Ha de contener 16 Dígitos");
            } else if (svc_Input.getText().length() == 3) {
                svc_Text.setText("SVC Valido");
            } else {
                svc_Text.setText("");
            }
        });
    }

    public void initDatosBancarios(Member x, Text trg, Text svc) {
        targeta_Input.setText(x.getCreditCard());
        svc_Input.setText(x.getSvc());
        
        auxTrg.textProperty().addListener((observable, oldValue, newValue) -> {
            x.setCreditCard(auxTrg.getText());
            trg.setText(auxTrg.getText());
        });
        auxSvc.textProperty().addListener((observable, oldValue, newValue) -> {
            x.setSvc(auxSvc.getText());
            svc.setText(auxSvc.getText());
        });
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (((targeta_Text.getText().equals("Targeta Valida"))) && (svc_Text.getText().equals("SVC Valido"))) {
            auxTrg.setText(targeta_Input.getText());
            auxSvc.setText(svc_Input.getText());
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

}
