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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author awset
 */
public class FXMLRegistroController implements Initializable {

    @FXML
    private TextField textoNombre;
    @FXML
    private TextField textoApellidos;
    @FXML
    private TextField textoTelefono;
    @FXML
    private TextField textoUsername;
    @FXML
    private TextField textoPassword;
    @FXML
    private TextField textoTarjeta;
    @FXML
    private ChoiceBox<String> elejImagen;
    @FXML
    private TextField textoSvc;
    @FXML
    private ImageView photo;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void botonAceptarPulsado(Event event)throws IOException{
        String nombre = textoNombre.getText();
        String apellidos = textoApellidos.getText();
        String telefono = textoTelefono.getText();
        String username = textoUsername.getText();
        String password = textoPassword.getText();
        String tarjeta = textoTarjeta.getText();
        
        String svc = textoSvc.getText();
        System.out.println(nombre+apellidos+telefono+username+password+tarjeta+svc);
        volverLogin(event);
       
    }
    
    @FXML
    public void botonCancelarPulsado(Event event) throws IOException{
        volverLogin(event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        elejImagen.getItems().addAll("Imagen 1","Imagen 2","Imagen 3","Imagen 4","Imagen 5","Imagen 6","Imagen 7","Imagen 8","Imagen 9","Imagen 10","Imagen 11");
        
    } 
    
    public void volverLogin(Event event) throws IOException{
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/clubdepadel_entrega/FXMLLogin.fxml"));
        Parent root = miCargador.load();

        FXMLLoginController controladoRegistro = miCargador.<FXMLLoginController>getController();
        controladoRegistro.initialize(null, null);

        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
}
