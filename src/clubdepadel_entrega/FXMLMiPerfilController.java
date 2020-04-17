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
import javafx.scene.image.Image;
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
    private String login;
    private String password;
    private String nombre;
    private String apellido;
    private String tarjeta;
    private Image imagen;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        login = FXMLLoginController.login_;
        password = FXMLLoginController.password_;
        Member miPerfil = new Member();
        miPerfil = ClubDBAccess.getSingletonClubDBAccess().getMemberByCredentials(login, password);
        nombre = miPerfil.getName();
        apellido = miPerfil.getSurname();
        tarjeta = miPerfil.getCreditCard();
        imagen = miPerfil.getImage();
    }    

    @FXML
    private void getInfo(ActionEvent event) {
        System.out.println(login + " " + password );
    }
    
}
