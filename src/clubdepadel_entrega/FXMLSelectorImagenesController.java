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
        misdatos.add(new Perfiles("Hombre 1", new Image("/images/men.PNG")));
        datos = FXCollections.observableArrayList(misdatos);
        listView.setItems(datos);  
    }    

    @FXML
    private void confirmar(ActionEvent event) {
    }
    
    class ImageListCell extends ListCell<Perfiles>{  
        private ImageView view = new ImageView();
        @Override
        protected void updateItem(Perfiles item, boolean empty)
        {
            super.updateItem(item, empty);   
            if(item == null || empty){
                setText(null);
                setGraphic(null);
            }else {
                view.setImage(item.getImagen());
                setGraphic(view);
                setText(item.getNombre());
            }
        }
    }
    
}
