/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author awset
 */
public class FXMLRegistroController implements Initializable {

    @FXML
    private TextField nombre_Input;
    @FXML
    private TextField apellido_Input;
    @FXML
    private TextField telefono_Input;
    @FXML
    private TextField login_Input;
    @FXML
    private TextField password_Input;
    @FXML
    private TextField targeta_Input;
    @FXML
    private TextField svc_Input;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 

    @FXML
    private void seleccionarImagen(ActionEvent event) {
    }

    @FXML
    private void cancelarRegistro(ActionEvent event) {
    }

    @FXML
    private void registrarse(ActionEvent event) {
    }


    }
