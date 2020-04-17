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
import DBAcess.ClubDBAccess;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Member;
/**
 * FXML Controller class
 *
 * @author awset
 */
public class FXMLMiPerfilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Member miPerfil = new Member();
    private String login;
    private String password;
    private String nombre;
    private String apellido;
    private String tarjeta;
    private Image imagen;
    @FXML
    private Label labelUsuario;
    @FXML
    private ImageView imagenDePerfil;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelApellido;
    @FXML
    private Label labelTarjeta;
    private Button añadirTarjeta;
    @FXML
    private Button anyadirTarjeta;
    @FXML
    private TextField anyadirT;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        login = FXMLLoginController.login_;
        password = FXMLLoginController.password_;
        
        miPerfil = ClubDBAccess.getSingletonClubDBAccess().getMemberByCredentials(login, password);
        
        //Informacion del perfil
        nombre = miPerfil.getName();
        apellido = miPerfil.getSurname();
        tarjeta = miPerfil.getCreditCard();
        imagen = miPerfil.getImage();
        
        //Cambio del texto de los labels
        labelNombre.setText(nombre + ".");
        labelApellido.setText(apellido + ".");
        labelUsuario.setText(login + ":");
        imagenDePerfil.setImage(imagen);
        if(tarjeta.equals("")){
            labelTarjeta.setText("No.");
            anyadirTarjeta.setVisible(true);
            anyadirT.setVisible(true);
        }
        else{
            labelTarjeta.setText("Si.");
        }
    }    

    private void getInfo(ActionEvent event) {
        System.out.println(login + " " + password );
    }

    @FXML
    private void setTarjeta(ActionEvent event) {
           miPerfil.setCreditCard(anyadirT.getText());
           labelTarjeta.setText("Si.");
           anyadirTarjeta.setVisible(false);
           anyadirT.setVisible(false);
    }
    
}
