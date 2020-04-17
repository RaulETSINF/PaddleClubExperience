/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    String caracteresConf = "._!@#$%^&*()+={}[]|<>,`~?\\/:;'- ";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void login(ActionEvent event) throws IOException {
        
        if (ClubDBAccess.getSingletonClubDBAccess().getMemberByCredentials(user_Input.getText(), password_Input.getText()) == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogo de confirmación");
            alert.setHeaderText("Error de Inicio de Sesión");
            alert.setContentText("Puede que hayas introducido los datos erroneamente o aun no estes registrado, revisalos o crea una cuenta");
            alert.showAndWait();

        } else {

           
            Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/clubdepadel_entrega/FXMLMiPerfil.fxml"));
            Parent root = miCargador.load();
            FXMLMiPerfilController controlador = miCargador.<FXMLMiPerfilController>getController();
            controlador.initPerfil(user_Input.getText(),password_Input.getText() );
            stage1.close();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Mi Perfil");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
        }

    }

    @FXML
    private void resgistrarse(ActionEvent event) throws IOException {
        Parent ventanaP = FXMLLoader.load(getClass().getResource("/clubdepadel_entrega/FXMLRegistro.fxml"));
        Scene ventanaS = new Scene(ventanaP);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(ventanaS);
        stage.setMinHeight(768);
        stage.setMinWidth(1150);
        stage.setTitle("Registro");
        stage.show();
    }

    @FXML
    private void verDisponibilidadPistas(ActionEvent event) {
    }

}
