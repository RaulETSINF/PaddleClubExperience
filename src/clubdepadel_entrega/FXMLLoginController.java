/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField user_Input;
    @FXML
    private PasswordField password_Input;

    String caracteresConf = "!@#$%^&*()_+={}[]|<>,.`~?\\/:;'-";
    ClubDBAccess miClub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        miClub = ClubDBAccess.getSingletonClubDBAccess();

        user_Input.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (caracteresConf.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });

        password_Input.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (caracteresConf.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    @FXML
    private void login(ActionEvent event) {
        if (miClub.getMemberByCredentials(user_Input.getText(), password_Input.getText()) == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogo de confirmación");
            alert.setHeaderText("Error de Inicio de Sesión");
            alert.setContentText("Puede que hayas introducido los datos erroneamente, revisalos o crea una cuenta");
            alert.showAndWait();

        } else {
            System.out.println("Bienvenido");
        }

    }

    @FXML
    private void registrarse(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/clubdepadel_entrega/FXMLRegistro.fxml"));
        Parent root = miCargador.load();

        FXMLRegistroController controladoRegistro = miCargador.<FXMLRegistroController>getController();
        controladoRegistro.initialize(null, null);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registro");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
