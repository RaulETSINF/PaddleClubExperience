/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubdepadel_entrega;

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
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private GridPane gridPane;
    private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(90);
    private final LocalTime lastSlotStart = LocalTime.of(21, 0);
    
    private List<List<TimeSlot>> timeSlots = new ArrayList<>();
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private ObjectProperty<TimeSlot> timeSlotSelected;
    
    /**
     * Initializes the controller class.
     */
    @Override
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
    }
    
    private void setTimeSlotsGrid(LocalDate date) {
        
    }

    @FXML
    private void inspeccionar(ActionEvent event) {
    }

    @FXML
    private void reservar(ActionEvent event) {
    }
    
    public class TimeSlot {

        private final LocalDateTime start;
        private final Duration duration;
        protected final Pane view;
        private Booking booking;
        private Court court;

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

        public TimeSlot(LocalDateTime start, Duration duration, Booking booking, Court court) {
            this.start = start;
            this.duration = duration;
            this.booking = booking;
            this.court = court;
            view = new Pane();
            view.getStyleClass().add("time-slot");
            selectedProperty().addListener((observable, oldValue, newValue) -> {
                view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, newValue);
            });
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
        
        public Booking getBooking(){
            return booking;
        }
        
        public Court getCourt(){
            return court;
        }

    }
    
}
