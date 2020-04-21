/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Booking;
import model.Court;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLVerDisponibilidadController implements Initializable {

    @FXML
    private TextField textField;
    @FXML
    private GridPane gridPane;
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private List<TimeSlot> timeSlots = new ArrayList<>();
    private ObjectProperty<TimeSlot> timeSlotSelected;
    private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(90);
    private final LocalTime lastSlotStart = LocalTime.of(21, 0);
    @FXML
    private Label label;
    @FXML
    private Label slotSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    /*
    *   Inicializamos algunas variable y le metemos un lissener al TextField para que sea dinámica la interación, llamará a un metodo que pintará
    *   el grid con las reservas del usuario introducido.
     */
    public void initialize(URL url, ResourceBundle rb) {
        label.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        timeSlotSelected = new SimpleObjectProperty<>();
        setTimeSlotsGrid(LocalDate.now());

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (ClubDBAccess.getSingletonClubDBAccess().getUserBookings(textField.getText()) != null) {
                setTimeSlotsGrid(LocalDate.now());
            } else {
                for (TimeSlot timeSlot : timeSlots) {
                    ObservableList<Node> childern = gridPane.getChildren();
                    childern.remove(timeSlot.getView());
                }
            }
        });
    }

    /*
    *   Método que Pinta el Grid con las reservas del usuario introducido en el TextField, muy similar a la que vimos en la Pract 5 con el proyecto
    *   GridTimeSlot.
     */
    private void setTimeSlotsGrid(LocalDate date) {
        timeSlotSelected.setValue(null);
        ObservableList<Node> childern = gridPane.getChildren();
        for (TimeSlot timeSlot : timeSlots) {
            childern.remove(timeSlot.getView());
        }
        int slotIndex = 1;
        timeSlots = new ArrayList<>();
        int aux = 1;
        String[] palabras = new String[]{"Court 1", "Court 2", "Court 3", "Court 4"};
        for (int i = 0; i < 4; i++) {
            for (LocalDateTime startTime = date.atTime(firstSlotStart);
                    !startTime.isAfter(date.atTime(lastSlotStart));
                    startTime = startTime.plus(slotLength)) {
                TimeSlot timeSlot = new TimeSlot(startTime, slotLength, palabras[i]);
                timeSlots.add(timeSlot);
                registerPressHandlers(timeSlot);
                gridPane.add(timeSlot.getView(), aux, slotIndex);
                slotIndex++;
            }
            slotIndex = 1;
            aux++;
        }

        /*Aqui es donde nosotros pintamos las celdas de las reservas del usuario*/
        ArrayList<Booking> reservasDia = ClubDBAccess.getSingletonClubDBAccess().getUserBookings(textField.getText());
        for (Booking reserva : reservasDia) {
            for (TimeSlot timeSlot : timeSlots) {
                if ((timeSlot.getTime().equals(reserva.getFromTime())) && (timeSlot.getNameNode().equals(reserva.getCourt().getName())) && (reserva.getMadeForDay().equals(LocalDate.now()))) {
                    if (timeSlot.getView().getStyleClass().contains("time-slot")) {
                        if (reserva.getMember().getLogin().equals(textField.getText())) {
                            timeSlot.getView().getStyleClass().remove("time-slot");
                            timeSlot.getView().getStyleClass().add("time-slot-libre");
                        }
                    }
                    timeSlot.setSelected(true);
                }
            }
        }

    }

    private void registerPressHandlers(TimeSlot timeSlot) {
        timeSlot.getView().setOnMousePressed((MouseEvent event) -> {
            //-------------------------------------------------------------------------
            //solamente puede estar seleccionado un slot dentro de la lista de slot
            //sin el bucle exterior se podria seleccionar un SlotTime por cada columna
            timeSlots.forEach(slot -> slot.setSelected(slot == timeSlot));
            //----------------------------------------------------------------
            //actualizamos el label Dia-Hora-Pista, esto es ad hoc,  para mi diseño
            timeSlotSelected.setValue(timeSlot);
        });
    }

    /*Método para volver atras al Login*/
    @FXML
    private void volver(ActionEvent event) throws IOException {
        Parent ventanaP = FXMLLoader.load(getClass().getResource("/clubdepadel_entrega/FXMLLogin.fxml"));
        Scene ventanaS = new Scene(ventanaP);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(ventanaS);
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        stage.setTitle("Login");
        stage.show();
    }

    /*Clase ligeramente modificada del TimeSlot, extraida del Proyecto GridTimeSlot visto en la Pract 5*/
    public class TimeSlot {

        private final LocalDateTime start;
        private final Duration duration;
        protected final Pane view;
        private String name;
        private Court pista;

        private final BooleanProperty selected = new SimpleBooleanProperty();

        public final BooleanProperty selectedProperty() {
            return selected;
        }

        public final boolean isSelected() {
            return selectedProperty().get();
        }

        public final void setSelected(boolean selected) {
            selectedProperty().set(selected);
        }

        public TimeSlot(LocalDateTime start, Duration duration, String name) {
            this.name = name;
            this.start = start;
            this.duration = duration;
            view = new Pane();
            view.getStyleClass().add("time-slot");
            // ---------------------------------------------------------------
            // de esta manera cambiamos la apariencia del TimeSlot cuando los seleccionamos
            selectedProperty().addListener((obs, wasSelected, isSelected)
                    -> view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));
            switch (name) {
                case "Court 1":
                    pista = ClubDBAccess.getSingletonClubDBAccess().getCourt("Court 1");
                    break;
                case "Court 2":
                    pista = ClubDBAccess.getSingletonClubDBAccess().getCourt("Court 2");
                    break;
                case "Court 3":
                    pista = ClubDBAccess.getSingletonClubDBAccess().getCourt("Court 3");
                    break;
                case "Court 4":
                    pista = ClubDBAccess.getSingletonClubDBAccess().getCourt("Court 4");
                    break;
                default:
                    break;
            }

        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalTime getTime() {
            return start.toLocalTime();
        }

        public LocalDate getDate() {
            return start.toLocalDate();
        }

        public DayOfWeek getDayOfWeek() {
            return start.getDayOfWeek();
        }

        public Duration getDuration() {
            return duration;
        }

        public Node getView() {
            return view;
        }

        public String getNameNode() {
            return name;
        }

        public Court getCourt() {
            return pista;
        }

    }

}
