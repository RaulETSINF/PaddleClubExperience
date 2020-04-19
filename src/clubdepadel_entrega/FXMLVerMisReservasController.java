/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Booking;
import model.Court;
import model.Member;
import DBAcess.ClubDBAccess;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLVerMisReservasController implements Initializable {

    @FXML
    private TableView<Booking> tableView;
    @FXML
    private TableColumn<Booking, LocalDate> diaColum;
    @FXML
    private TableColumn<Booking, LocalTime> inicioColum;
    @FXML
    private TableColumn<Booking, Court> PistaColum;
    @FXML
    private TableColumn<Booking, Boolean> PagadoColum;
    @FXML
    private TableColumn<Booking, LocalTime> bookingDateColum;

    ClubDBAccess clubDBAccess;
    ObservableList<Booking> observableBooking;
    Member aux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        PistaColum.setCellValueFactory(param -> new SimpleObjectProperty<Court>(param.getValue().getCourt()));
        PistaColum.setCellFactory(v -> {
            return new TableCell<Booking, Court>() {
                @Override
                protected void updateItem(Court item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        switch (item.getName()) {
                            case "Court 1":
                                setText("Pista 1");
                                break;
                            case "Court 2":
                                setText("Pista 2");
                                break;
                            case "Court 3":
                                setText("Pista 3");
                                break;
                            case "Court 4":
                                setText("Pista 4");
                                break;
                            default:
                                break;
                        }

                    }
                }
            };

        });
        diaColum.setCellValueFactory(param -> new SimpleObjectProperty<LocalDate>(param.getValue().getMadeForDay()));
        diaColum.setCellFactory(v -> {
            return new TableCell<Booking, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.toString());
                    }
                }
            };

        });
        inicioColum.setCellValueFactory(param -> new SimpleObjectProperty<LocalTime>(param.getValue().getFromTime()));
        inicioColum.setCellFactory(v -> {
            return new TableCell<Booking, LocalTime>() {
                @Override
                protected void updateItem(LocalTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.toString());
                    }
                }
            };

        });
        PagadoColum.setCellValueFactory(param -> new SimpleObjectProperty<Boolean>(param.getValue().getPaid()));
        PagadoColum.setCellFactory(v -> {
            return new TableCell<Booking, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        if (item.toString().equals("true")) {
                            setText("Pagado");
                        } else {
                            setText("No pagado");
                        }

                    }
                }
            };

        });
        bookingDateColum.setCellValueFactory(param -> new SimpleObjectProperty<LocalTime>(param.getValue().getFromTime()));
        bookingDateColum.setCellFactory(v -> {
            return new TableCell<Booking, LocalTime>() {
                @Override
                protected void updateItem(LocalTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.plusMinutes(90).toString());
                    }
                }
            };

        });
    }

    public void initVerMisReservas(Member x) {
        observableBooking = FXCollections.observableList(clubDBAccess.getUserBookings(x.getLogin()));
        tableView.setItems(observableBooking);
        aux = x;
    }

    @FXML
    private void Eliminar(ActionEvent event) {
        double num1 = (double) tableView.getSelectionModel().getSelectedItem().getMadeForDay().toEpochDay() * 24;
        double num2 = (double) LocalDate.now().toEpochDay() * 24;
        if (!tableView.getItems().isEmpty()) {
            if ((num1 - num2) > 24) {
                clubDBAccess.getBookings().remove(tableView.getSelectionModel().getSelectedItem());
                observableBooking.remove(tableView.getSelectionModel().getSelectedItem());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dialogo de confirmación");
                alert.setHeaderText("Error de Eliminación de Reserva");
                alert.setContentText("No se puede Eliminar una reserva a falta de 1 dia");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void Prueba(ActionEvent event) {
    }
}
