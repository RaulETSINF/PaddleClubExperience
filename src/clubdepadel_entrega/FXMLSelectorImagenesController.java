/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLSelectorImagenesController implements Initializable {

    private ObservableList<Perfiles> datos = null;
    
    private ArrayList<Perfiles> misdatos = new ArrayList<Perfiles>();
    @FXML
    private ListView<Perfiles> listView;

    ImageView x = new ImageView(new Image("/images/padel.png"));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(c -> new ImageListCell());
        misdatos.clear();
        inicializarDatos();
        datos = FXCollections.observableArrayList(misdatos);
        listView.setItems(datos);
    }

    @FXML
    private void confirmar(ActionEvent event) {
        if (listView.getSelectionModel().getSelectedItem() != null) {   
            Image aux = listView.getSelectionModel().getSelectedItem().getImagen();
            x.setImage(aux);
        }
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    public void initImagenPerfil(ImageView aux) {
        x.imageProperty().addListener((observable, oldValue, newValue) -> {
            aux.setImage(x.getImage());
        });
    }

    class ImageListCell extends ListCell<Perfiles> {

        private ImageView view = new ImageView();

        @Override
        protected void updateItem(Perfiles item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setText(null);
                setGraphic(null);
            } else {
                view.setImage(item.getImagen());
                setGraphic(view);
                setText("       " + item.getNombre());
                setStyle("-fx-text-fill: #000000;"+"-fx-font-size: 25;"+
                        "-fx-font-weight: bolder;" + "-fx-font-family: Arial;");
            }
        }
    }

    private void inicializarDatos() {
        misdatos.add(new Perfiles("Raqueta 1", new Image("/images/padel.png")));
        misdatos.add(new Perfiles("Raqueta 2", new Image("/images/padel2.png")));
        misdatos.add(new Perfiles("Raqueta 3", new Image("/images/padel3.png")));
        misdatos.add(new Perfiles("Raqueta 4", new Image("/images/padel4.png")));
        misdatos.add(new Perfiles("Raqueta 5", new Image("/images/padel5.png")));
        misdatos.add(new Perfiles("Raqueta 6", new Image("/images/padel6.png")));
        misdatos.add(new Perfiles("Raqueta 7", new Image("/images/padel7.png")));
        misdatos.add(new Perfiles("Raqueta 8", new Image("/images/padel8.png")));
        misdatos.add(new Perfiles("Raqueta 9", new Image("/images/padel9.png")));
        misdatos.add(new Perfiles("Raqueta 10", new Image("/images/padel10.png")));
    }

}
