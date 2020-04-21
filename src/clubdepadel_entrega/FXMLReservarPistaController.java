/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

import DBAcess.ClubDBAccess;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Booking;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author RaulP
 */
public class FXMLReservarPistaController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private GridPane gridPane;

    private Member persona;
    private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(90);
    private final LocalTime lastSlotStart = LocalTime.of(21, 0);

    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private List<TimeSlot> timeSlots = new ArrayList<>();
    private ObjectProperty<TimeSlot> timeSlotSelected;
    @FXML
    private Label slotSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    /*
    *   Inicaliamos varios componentes y le añadimos el Lissener al DatePicker, asi como la Label que muestra el dia.
    */
    public void initialize(URL url, ResourceBundle rb) {

        timeSlotSelected = new SimpleObjectProperty<>();

        datePicker.setValue(LocalDate.now());

        setTimeSlotsGrid(datePicker.getValue());

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            setTimeSlotsGrid(newValue);
        });

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
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ISO_DATE;
        timeSlotSelected.addListener((a, b, c) -> {
            if (c == null) {
                slotSelected.setText("");
            } else {

                slotSelected.setText(c.getDate().format(dayFormatter)
                        + "-"
                        + c.getStart().format(timeFormatter)
                        + "-" + timeSlotSelected.getValue().getNameNode());

            }
        });
    }

    /*
    * Metodo de inicio le pasamos el member con el que inicamos la sesion para tenerlo ahi y realizar las
    * reservas a su nombre.
     */
    public void initReservar(Member x) {
        persona = x;
    }

    /*
    * Este metodo es idéntico al de verDisponibilidad solo que en vez de mostrar las reservas del usuario, lo que hace es mostrar todas
    * las reservas hechas por todos los usuario.
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
        String[] palabras = new String[]{"Pista 1", "Pista 2", "Pista 3", "Pista 4"};
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

        ArrayList<Booking> reservasDia = ClubDBAccess.getSingletonClubDBAccess().getForDayBookings(datePicker.getValue());
        for (Booking reserva : reservasDia) {
            for (TimeSlot timeSlot : timeSlots) {
                if ((timeSlot.getTime().equals(reserva.getFromTime())) && (timeSlot.getcourtName().equals(reserva.getCourt().getName()))) {
                    if (timeSlot.getView().getStyleClass().contains("time-slot")) {
                        timeSlot.getView().getStyleClass().remove("time-slot");
                        timeSlot.getView().getStyleClass().add("time-slot-libre");
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

    /*
    * Método para reservas las pistas, cuando algun time slot esta selecionado, simplemente extraemos la información del time slot,
    * y hacemos una reserva, después de confirmar si la queremos realmente, si no tenemos targeta nos avisa y si esta ya reservada tambien,
    * lo consigue gracias a comparar los styles del Time-Slot.
     */
    @FXML
    private void reservar(ActionEvent event) {
        if (timeSlotSelected != null) {

            if (timeSlotSelected.getValue().getView().getStyleClass().contains("time-slot-libre")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dialogo de confirmación");
                alert.setHeaderText("Error de Registro");
                alert.setContentText("Ya se ha realizado una reserva para esta fecha");
                alert.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("SlotTime");
                alerta.setHeaderText("Confirma la selección");
                alerta.setContentText("Quieres reservar esta pista?");
                Optional<ButtonType> result = alerta.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (ClubDBAccess.getSingletonClubDBAccess().hasCreditCard(persona.getLogin())) {
                        ClubDBAccess.getSingletonClubDBAccess().getBookings().add(new Booking(LocalDateTime.now(), datePicker.getValue(), timeSlotSelected.getValue().getTime(), true, timeSlotSelected.getValue().getCourt(), ClubDBAccess.getSingletonClubDBAccess().getMemberByCredentials(persona.getLogin(), persona.getPassword())));
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Dialogo de confirmación");
                        alert.setHeaderText("Reserva Realizada con éxito");
                        alert.setContentText("No hay datos Bancarios, La reserva se anotará como No Pagada");
                        alert.showAndWait();
                        ClubDBAccess.getSingletonClubDBAccess().getBookings().add(new Booking(LocalDateTime.now(), datePicker.getValue(), timeSlotSelected.getValue().getTime(), false, timeSlotSelected.getValue().getCourt(), ClubDBAccess.getSingletonClubDBAccess().getMemberByCredentials(persona.getLogin(), persona.getPassword())));
                    }
                    ObservableList<String> styles = timeSlotSelected.getValue().getView().getStyleClass();
                    if (styles.contains("time-slot")) {
                        styles.remove("time-slot");
                        styles.add("time-slot-libre");
                    }
                }
            }
        }
    }

    /*
    *   Clase TimeSlot ligeramente modificada, esta clase es identica a la de verDisponibilidad de las Pistas,
    *   He puesto que guarde el Court y un nombre y con eso voy guardando los datos al crear los TimeSlots.
     */
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
                case "Pista 1":
                    pista = ClubDBAccess.getSingletonClubDBAccess().getCourt("Court 1");
                    break;
                case "Pista 2":
                    pista = ClubDBAccess.getSingletonClubDBAccess().getCourt("Court 2");
                    break;
                case "Pista 3":
                    pista = ClubDBAccess.getSingletonClubDBAccess().getCourt("Court 3");
                    break;
                case "Pista 4":
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

        public String getcourtName() {
            return pista.getName();
        }

    }

}
