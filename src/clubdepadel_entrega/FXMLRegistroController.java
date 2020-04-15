/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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
    @FXML
    private ImageView imagen_Perfil;
    String caracteresConfNombre = "1234567890!@#$%^&*()_+={}[]|<>,.`~?\\/:;'- ";
    String caracteresConfApellido = "1234567890!@#$%^&*()_+={}[]|<>,.`~?\\/:;'-";
    String caracteresConfTelefono = "1234567890";
    String caracteresConfLogin = "._!@#$%^&*()+={}[]|<>,`~?\\/:;'- ";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constrains_TextField_1(nombre_Input, caracteresConfNombre);
        Constrains_TextField_1(apellido_Input, caracteresConfApellido);
        Constrains_TextField_2(telefono_Input, caracteresConfTelefono);
        Constrains_TextField_1(login_Input, caracteresConfLogin);
        Constrains_TextField_1(password_Input, caracteresConfLogin);
        Constrains_TextField_2(targeta_Input, caracteresConfTelefono);
        Constrains_TextField_2(svc_Input, caracteresConfTelefono);
        inicializar_Lisseners_Numero(telefono_Input, "Ha de contener 9 Digitos", "Teléfono valido", telefono_Msg, telefono_Image, 9);
        inicializar_Lisseners_Numero(targeta_Input, "Ha de contener 16 Digitos", "Targeta valida", tarjeta_Msg, tarjeta_Image, 16);
        inicializar_Lisseners_Numero(svc_Input, "Ha de contener 3 Digitos", "SVC valido", svc_Msg, svc_Image, 3);
        inicializar_Lisseners_Login(login_Input, login_Msg, login_Image);
        inicializar_Lisseners_Password(password_Input, password_Msg, password_Image,6);
    }

    @FXML
    private void seleccionarImagen(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/clubdepadel_entrega/FXMLSelectorImagenes.fxml"));
        Parent root = miCargador.load();

        FXMLSelectorImagenesController controlador = miCargador.<FXMLSelectorImagenesController>getController();
        controlador.initImagenPerfil(imagen_Perfil);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Selector de Imagen de Perfil");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }

    @FXML
    private void cancelarRegistro(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de confirmación");
        alert.setHeaderText("Cancelar Registro");
        alert.setContentText("¿Seguro que quieres cancelar el registo?");
        ButtonType buttonTypeOne = new ButtonType("SI", ButtonData.YES);
        ButtonType buttonTypeTwo = new ButtonType("NO", ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeTwo, buttonTypeOne);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonTypeOne) {
                Parent ventanaP = FXMLLoader.load(getClass().getResource("/clubdepadel_entrega/FXMLLogin.fxml"));
                Scene ventanaS = new Scene(ventanaP);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(ventanaS);
                stage.setMinHeight(768);
                stage.setMinWidth(1024);
                stage.setTitle("Login");
                stage.show();
            }
        }

    }

    @FXML
    private void registrarse(ActionEvent event) throws IOException {
        System.out.println(comprovacionRegistro());
        if (comprovacionRegistro() == true) {
            String aux = quitarEspacios(apellido_Input.getText());
            model.Member e = new Member(nombre_Input.getText(), aux,
                    telefono_Input.getText(), login_Input.getText(), password_Input.getText(),
                    targeta_Input.getText(), svc_Input.getText(), imagen_Perfil.getImage());
            ClubDBAccess.getSingletonClubDBAccess().getMembers().add(e);
            ClubDBAccess.getSingletonClubDBAccess().saveDB();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogo de confirmación");
            alert.setHeaderText("Usuario Creado Correctamente");
            alert.setContentText("Regresa al Inicio de sesión para ingresar");
            alert.showAndWait();
                Parent ventanaP = FXMLLoader.load(getClass().getResource("/clubdepadel_entrega/FXMLLogin.fxml"));
                Scene ventanaS = new Scene(ventanaP);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(ventanaS);
                stage.setMinHeight(768);
                stage.setMinWidth(1024);
                stage.setTitle("Login");
                stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogo de confirmación");
            alert.setHeaderText("Error de Registro");
            alert.setContentText("Revisa los campos, puede que tengas alguno en blanco o mal");
            alert.showAndWait();
        }
    }

    private void inicializar_Lisseners_Numero(TextField x, String msg, String msg2, Text y, ImageView z, int top) {
        x.textProperty().addListener((observable, oldValue, newValue) -> {
            if (((x.getText().length() < top) || (x.getText().length() > top)) && (x.getText().length() != 0)) {
                y.setFill(Paint.valueOf("#ff0000"));
                z.setImage(new javafx.scene.image.Image("/images/CrossBox.png"));
                y.setText(msg);
            } else if (x.getText().length() == top) {
                y.setFill(Paint.valueOf("#00a654"));
                y.setText(msg2);
                z.setImage(new javafx.scene.image.Image("/images/checkBox.png"));
            } else {
                z.setImage(null);
                y.setText("");
            }
        });

    }

    private void inicializar_Lisseners_Password(TextField x, Text y, ImageView z, int top) {
        x.textProperty().addListener((observable, oldValue, newValue) -> {
            if ((x.getText().length() >= top) && (x.getText().length() != 0)) {
                y.setFill(Paint.valueOf("#00a654"));
                y.setText("Contraseña Valida");
                z.setImage(new javafx.scene.image.Image("images/checkBox.png"));
            } else if ((x.getText().length() < top) && (x.getText().length() != 0)) {
                y.setFill(Paint.valueOf("#ff0000"));
                y.setText("Mas de 6 carácteres");
                z.setImage(new javafx.scene.image.Image("images/CrossBox.png"));
            } else {
                y.setText("");
                z.setImage(null);
            }
        });
    }

    private void inicializar_Lisseners_Login(TextField x, Text y, ImageView z) {
        x.textProperty().addListener((observable, oldValue, newValue) -> {
            if ((ClubDBAccess.getSingletonClubDBAccess().existsLogin(x.getText())) && (x.getText().length() > 0)) {
                y.setFill(Paint.valueOf("#ff0000"));
                y.setText("Nombre de usuario en uso");
                z.setImage(new javafx.scene.image.Image("/images/CrossBox.png"));
            } else if ((ClubDBAccess.getSingletonClubDBAccess().existsLogin(x.getText()) == false) && (x.getText().length() > 0)) {
                y.setFill(Paint.valueOf("#00a654"));
                y.setText("Nombre Disponible");
                z.setImage(new javafx.scene.image.Image("/images/checkBox.png"));
            } else {
                y.setText("");
                z.setImage(null);
            }
        });
    }

    private void Constrains_TextField_1(TextField x, String y) {
        x.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (y.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    private void Constrains_TextField_2(TextField x, String y) {
        x.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, (javafx.scene.input.KeyEvent keyEvent) -> {
            if (!y.contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    /*
    * Comprueba que los campos de los TextFields esten bien puestos y sin errores
     */
    private boolean comprovacionRegistro() {
        if (nombre_Input.getText().length() <= 0) {
            return false;
        }
        if (apellido_Input.getText().length() <= 0) {
            return false;
        }
        if ((telefono_Msg.getText().equals("Ha de contener 9 Digitos")) || (telefono_Msg.getText().equals(""))) {
            return false;
        }
        if ((login_Msg.getText().equals("Nombre de usuario en uso")) || (login_Msg.getText().equals(""))) {
            return false;
        }
        if ((password_Msg.getText().equals("Mas de 6 carácteres")) || (password_Msg.getText().equals(""))) {
            return false;
        }
        if (tarjeta_Msg.getText().equals("Ha de contener 16 Digitos")) {
            return false;
        }
        if (svc_Msg.getText().equals("Ha de contener 3 Digitos")) {
            return false;
        }
        return true;
    }

    /*
    * Quita los espacios del principio del String.
     */
    private String quitarEspacios(String x) {
        if (x.length() == 0) {
            return "";
        }
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == ' ') {
                x = x.substring(i + 1, x.length() - 1);
            } else {
                return x;
            }
        }
        return x;
    }

}
