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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
                setText(item.getNombre());
            }
        }
    }

    private void inicializarDatos() {
        misdatos.add(new Perfiles("Hombre 1", new Image("/images/men.PNG")));
        misdatos.add(new Perfiles("Hombre 2", new Image("/images/men2.PNG")));
        misdatos.add(new Perfiles("Hombre 3", new Image("/images/men3.PNG")));
        misdatos.add(new Perfiles("Hombre 4", new Image("/images/men4.PNG")));
        misdatos.add(new Perfiles("Hombre 5", new Image("/images/men5.PNG")));
        misdatos.add(new Perfiles("Mujer 1", new Image("/images/woman.PNG")));
        misdatos.add(new Perfiles("Mujer 2", new Image("/images/woman2.PNG")));
        misdatos.add(new Perfiles("Mujer 3", new Image("/images/woman3.PNG")));
        misdatos.add(new Perfiles("Mujer 4", new Image("/images/woman4.PNG")));
        misdatos.add(new Perfiles("Mujer 5", new Image("/images/woman5.PNG")));
    }

}
