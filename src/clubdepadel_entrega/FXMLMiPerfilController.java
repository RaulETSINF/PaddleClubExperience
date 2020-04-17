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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author awset
 */
public class FXMLMiPerfilController implements Initializable {

    @FXML
    private Text nombre_Usuario;
    @FXML
    private Text apellido_Usuario;
    @FXML
    private Text telefono_Usuario;
    @FXML
    private Text login_Usuario;
    @FXML
    private Text password_Usuario;
    @FXML
    private Text targeta_Usuario;
    @FXML
    private Text svc_Usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void agregarDatosBancarios(ActionEvent event) {
    }
    
    public void initPerfil(model.Member x) {
        nombre_Usuario.setText(x.getName()); 
        apellido_Usuario.setText(x.getSurname());
        telefono_Usuario.setText(x.getTelephone());
        login_Usuario.setText(x.getLogin());
        password_Usuario.setText(x.getPassword());
        targeta_Usuario.setText(x.getCreditCard());
        svc_Usuario.setText(x.getSvc());
    }

}
