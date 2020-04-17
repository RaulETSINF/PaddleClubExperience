/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class MainAppController implements Initializable {

    @FXML
    private ImageView imagen_perfil;
    @FXML
    private BorderPane borderPane;

    private model.Member usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initMainApp(model.Member x) {
        imagen_perfil.setImage(x.getImage());
        usuario = x;
    }

    @FXML
    private void verMiPerfil(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/clubdepadel_entrega/FXMLMiPerfil.fxml"));
        Parent root = miCargador.load();
        FXMLMiPerfilController controlador = miCargador.<FXMLMiPerfilController>getController();
        controlador.initPerfil(usuario);
        borderPane.setCenter(root);
    }

}
