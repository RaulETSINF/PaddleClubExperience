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
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private String caracteresConfTelefono = "1234567890";

    private model.Member miPerfil = null;

    private String login ;
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
    @FXML
    private Button cerrarSesion;
    @FXML
    private Label svcLabel;
    @FXML
    private TextField svcTextfield;
    @FXML
    private Text mensage_error1;
    @FXML
    private Text mensage_error2;
    @FXML
    private Label notarjetaLabel;

    public void initPerfil(String x, String y) {
        this.login = x;
        this.password = y;
        
        
        
        miPerfil = ClubDBAccess.getSingletonClubDBAccess().getMemberByCredentials(login, password);
        
        nombre = miPerfil.getName();
        apellido = miPerfil.getSurname();
        tarjeta = miPerfil.getCreditCard();
        imagen = miPerfil.getImage();

        //Alertas de tarjeta
        Constrains_TextField_2(anyadirT, caracteresConfTelefono);
        Constrains_TextField_2(svcTextfield, caracteresConfTelefono);
        inicializar_Lisseners_Numero(anyadirT, "La tarjeta ha de contener 16 Digitos.", "Nº de tarjeta valido.", mensage_error1, 16);
        inicializar_Lisseners_Numero(svcTextfield, "El SVC ha de contener 3 Digitos.", "SVC valido.", mensage_error2, 3);
        //Cambio del texto de los labels
        labelNombre.setText(nombre + ".");
        labelApellido.setText(apellido + ".");
        labelUsuario.setText(login + ":");
        imagenDePerfil.setImage(imagen);
        if (tarjeta.equals("")) {
            labelTarjeta.setText("No.");
            anyadirTarjeta.setVisible(true);
            anyadirT.setVisible(true);
            svcLabel.setVisible(true);
            svcTextfield.setVisible(true);
        } else {
            labelTarjeta.setText("Si.");}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        
    }
    
   

    @FXML
    private void setTarjeta(ActionEvent event) {
        miPerfil.setCreditCard(anyadirT.getText());
        anyadirTarjeta.setVisible(false);
        anyadirT.setVisible(false);
        miPerfil.setSvc(svcTextfield.getText());
        svcLabel.setVisible(false);
        svcTextfield.setVisible(false);
        notarjetaLabel.setVisible(false);
        mensage_error1.setVisible(false);
        mensage_error2.setVisible(false);
        labelTarjeta.setText("Si.");
        

    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        Parent ventanaP = FXMLLoader.load(getClass().getResource("/clubdepadel_entrega/FXMLLogin.fxml"));
        Scene ventanaS = new Scene(ventanaP);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(ventanaS);
        stage.setTitle("Login");
        stage.show();
    }

    private void Constrains_TextField_2(TextField x, String y) {
        x.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (!y.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }

        });
    }

    private void inicializar_Lisseners_Numero(TextField x, String msg, String msg2, Text y, int top) {
        x.textProperty().addListener((observable, oldValue, newValue) -> {
            if (((x.getText().length() < top) || (x.getText().length() > top)) && (x.getText().length() != 0)) {
                y.setFill(Paint.valueOf("#ff0000"));

                y.setText(msg);
            } else if (x.getText().length() == top) {
                y.setFill(Paint.valueOf("#00a654"));
                y.setText(msg2);

            } else {

                y.setText("");
            }
        });

    }

}
