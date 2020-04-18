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
import javafx.scene.control.DatePicker;
import DBAcess.ClubDBAccess;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateTimeStringConverter;
import model.Booking;
import model.Court;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLReservarPistaController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Booking> tableViewBooking;

    ClubDBAccess clubDBAccess;
    ObservableList<Booking> observableBookings;
    @FXML
    private TableColumn<Booking, Booking> horas;

    LocalDate x = LocalDate.of(2020, Month.MARCH, 19);
    LocalTime y = LocalTime.of(14, 0);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        observableBookings = FXCollections.observableList(clubDBAccess.getBookings());
        horas.setCellValueFactory(param -> new SimpleObjectProperty<Booking>(param.getValue()));
        horas.setCellFactory(v -> {
            return new TableCell<Booking, Booking>() {
                @Override
                protected void updateItem(Booking item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(""+item.getFromTime());
                    }
                }
            };
        });
        clubDBAccess.getBookings().add(new Booking(LocalDateTime.MIN, LocalDate.MAX, LocalTime.NOON, true, clubDBAccess.getCourts().get(0), clubDBAccess.getMembers().get(0)));
        tableViewBooking.setItems(observableBookings);
        datePicker.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });
    }

    @FXML

    private void inspeccionar(ActionEvent event) {
        clubDBAccess.getBookings().removeAll(observableBookings);
    }

    @FXML
    private void reservar(ActionEvent event) {
    }

}
