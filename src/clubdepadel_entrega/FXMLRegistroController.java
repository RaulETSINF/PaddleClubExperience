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
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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

    String caracteresConfNombre = "1234567890!@#$%^&*()_+={}[]|<>,.`~?\\/:;'- ";
    String caracteresConfApellido = "1234567890!@#$%^&*()_+={}[]|<>,.`~?\\/:;'-";
    String caracteresConfTelefono = "1234567890";
    @FXML
    private Text telefono_Mensaje;
    @FXML
    private ImageView telefonoImageView;
    @FXML
    private ImageView telefonoImageView6;
    @FXML
    private Text telefono_Mensaje6;
    @FXML
    private ImageView telefonoImageView5;
    @FXML
    private Text telefono_Mensaje5;
    @FXML
    private ImageView telefonoImageView1;
    @FXML
    private Text telefono_Mensaje1;
    @FXML
    private ImageView telefonoImageView2;
    @FXML
    private Text telefono_Mensaje2;
    @FXML
    private ImageView telefonoImageView3;
    @FXML
    private Text telefono_Mensaje3;
    @FXML
    private ImageView telefonoImageView4;
    @FXML
    private Text telefono_Mensaje4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombre_Input.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (caracteresConfNombre.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });

        apellido_Input.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (caracteresConfApellido.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });

        telefono_Input.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (!caracteresConfTelefono.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });

        telefono_Input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (((telefono_Input.getText().length() < 9) || (telefono_Input.getText().length() > 9)) && (telefono_Input.getText().length() != 0)) {
                telefonoImageView.setImage(null);
                telefono_Mensaje.setFill(Paint.valueOf("#ff0000"));
                telefonoImageView.setImage(new javafx.scene.image.Image("/images/CrossBox.png"));
                telefono_Mensaje.setText("El Teléfono ha de contener 9 Digitos");
            } else if(telefono_Input.getText().length() == 9) {
                telefonoImageView.setImage(new javafx.scene.image.Image("/images/checkBox.png"));
                 telefono_Mensaje.setText("");
            }else{
                telefonoImageView.setImage(null);
                telefono_Mensaje.setText("");
            }
        });

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
