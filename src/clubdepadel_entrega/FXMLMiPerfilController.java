/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import java.net.URL;
import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raul P
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

    
    /*
    * A traves de una ventanita añadimos los datos bancarios si no los habiamos puesto.
    */
    @FXML
    private void agregarDatosBancarios(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/clubdepadel_entrega/FXMLDatosBancarios.fxml"));
        Parent root = miCargador.load();
        FXMLDatosBancariosController controlador = miCargador.<FXMLDatosBancariosController>getController();
        controlador.initDatosBancarios(ClubDBAccess.getSingletonClubDBAccess().getMemberByCredentials(login_Usuario.getText(), password_Usuario.getText()),targeta_Usuario, svc_Usuario);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Selector de Imagen de Perfil");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
    /*
    * Metodo que inicializa el Controles le pasamos el Member con el que iniciamos la sesion
    */
    public void initPerfil(model.Member x) {      
        nombre_Usuario.setText(x.getName()); 
        apellido_Usuario.setText(x.getSurname());
        telefono_Usuario.setText(x.getTelephone());
        login_Usuario.setText(x.getLogin());
        password_Usuario.setText(x.getPassword());
        targeta_Usuario.setText(x.getCreditCard());
        svc_Usuario.setText(x.getSvc());
    }

    
    /*
    * Cierra la sesión y vuelve al Login
    */
    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/clubdepadel_entrega/FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMinHeight(768);
        stage.setMinWidth(1150);
        stage.setTitle("Registro");
        stage.show();
    }

}
