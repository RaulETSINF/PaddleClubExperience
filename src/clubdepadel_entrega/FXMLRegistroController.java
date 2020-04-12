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
    @FXML
    private ImageView nombre_Image;
    @FXML
    private Text nombre_Msg;
    @FXML
    private ImageView apellido_Image;
    @FXML
    private Text apellido_Msg;
    @FXML
    private ImageView telefono_Image;
    @FXML
    private Text telefono_Msg;
    @FXML
    private ImageView login_Image;
    @FXML
    private Text login_Msg;
    @FXML
    private ImageView password_Image;
    @FXML
    private Text password_Msg;
    @FXML
    private ImageView tarjeta_Image;
    @FXML
    private Text tarjeta_Msg;
    @FXML
    private ImageView svc_Image;
    @FXML
    private Text svc_Msg;


    String caracteresConfNombre = "1234567890!@#$%^&*()_+={}[]|<>,.`~?\\/:;'- ";
    String caracteresConfApellido = "1234567890!@#$%^&*()_+={}[]|<>,.`~?\\/:;'-";
    String caracteresConfTelefono = "1234567890";
    String caracteresConfLogin = "!@#$%^&*()+={}[]|<>,`~?\\/:;'- ";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constrains_TextField_1(nombre_Input, caracteresConfNombre);
        Constrains_TextField_1(apellido_Input, caracteresConfApellido);
        Constrains_TextField_2(telefono_Input, caracteresConfTelefono);
        Constrains_TextField_1(login_Input, caracteresConfLogin);
        Constrains_TextField_1(password_Input, caracteresConfLogin);
        Constrains_TextField_2(targeta_Input, caracteresConfTelefono);
        Constrains_TextField_2(svc_Input, caracteresConfTelefono);

        inicializar_Lisseners_Numero(telefono_Input, "El Teléfono ha de contener 9 Digitos", telefono_Msg, telefono_Image, 9);
        inicializar_Lisseners_Numero(targeta_Input, "La targeta ha de contener 16 Digitos", tarjeta_Msg, tarjeta_Image, 16);
        inicializar_Lisseners_Numero(svc_Input, "El SVC ha de contener 3 Digitos", svc_Msg, svc_Image, 3);

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
    
    private void inicializar_Lisseners_Numero(TextField x, String msg, Text y, ImageView z, int top){
        x.textProperty().addListener((observable, oldValue, newValue) -> {
            if (((x.getText().length() < top) || (x.getText().length() > top)) && (x.getText().length() != 0)) {
                z.setImage(null);
                y.setFill(Paint.valueOf("#ff0000"));
                z.setImage(new javafx.scene.image.Image("/images/CrossBox.png"));
                y.setText(msg);
            } else if(x.getText().length() == top) {
                z.setImage(new javafx.scene.image.Image("/images/checkBox.png"));
                y.setText("");
            }else{
                z.setImage(null);
                y.setText("");
            }
        });
    
    }
    private void Constrains_TextField_1(TextField x, String y){
        x.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (y.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }
    private void Constrains_TextField_2(TextField x, String y){
        x.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (!y.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }
}
