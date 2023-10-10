package ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import core.manager.IRoomManager;
import core.manager.Manager;
import core.manager.RoomManager;
import core.manager.RoomTypeManager;
import core.model.Room;
import core.model.RoomType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class RoomController implements Initializable {
    @FXML
    ListView<RoomType> roomTypeListView;
    @FXML
    ListView<Room> roomListView;
    @FXML
    TextField roomTypeNameTxt;
    @FXML
    Spinner<Integer> roomTypePriceTxt, roomNumberTxt;
    @FXML
    ComboBox<RoomType> roomTypeComboBox;
    @FXML
    Label roomTypeErrorLbl, roomErrorLbl;
    @FXML
    Button roomTypeDeleteBtn, roomDeleteBtn;

    private final IRoomManager roomManager;
    private final RoomTypeManager roomTypeManager;

    public RoomController() {
        roomManager = Manager.GetRoomManager();
        roomTypeManager = Manager.GetRoomTypeManager();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        roomTypeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedRoomTypeChanged(newValue));
        roomListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedRoomChanged(newValue));
        loadRoomTypes();
        loadRooms();

        roomTypePriceTxt.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000, 0));
        roomNumberTxt.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(101, 599, 101));

        roomTypeErrorLbl.setText("");
        roomErrorLbl.setText("");

        roomTypeDeleteBtn.setDisable(true);
        roomDeleteBtn.setDisable(true);
    }

    public void selectedRoomTypeChanged(RoomType roomType) {
        roomTypeNameTxt.setText(roomType == null ? null : roomType.getName());
        roomTypePriceTxt.getValueFactory().setValue(roomType == null ? 0 : roomType.getPrice());
        roomTypeDeleteBtn.setDisable(roomType == null);
    }

    public void selectedRoomChanged(Room room) {
        roomNumberTxt.getValueFactory().setValue(room == null ? 101 : room.getRoomNumber());

        RoomType roomType = null;
        if (room != null) {
            roomType = roomTypeManager.getRoomType(room.getTypeId());
        }

        roomTypeComboBox.getSelectionModel().select(roomType);
        roomDeleteBtn.setDisable(room == null);
    }

    private void loadRoomTypes() {
        List<RoomType> roomTypes = roomTypeManager.listRoomTypes();
        roomTypeListView.setItems(FXCollections.observableArrayList(roomTypes));
        roomTypeComboBox.setItems(FXCollections.observableArrayList(roomTypes));
    }

    private void loadRooms() {
        List<RoomType> roomTypes = roomTypeManager.listRoomTypes();

        List<Room> rooms = roomManager.listRooms();
        rooms = rooms.stream()
            .sorted((a, b) -> a.getRoomNumber() - b.getRoomNumber())
            .toList();

        rooms.forEach(r -> {
            RoomType roomType = roomTypes.stream().filter(rt -> rt.getId().equals(r.getTypeId())).findFirst().orElse(null);
            String roomTypeName = roomType == null ? "no room type" : roomType.getName();
            r.setCustomStringFormatter("%1 " + "(" + roomTypeName + ")");
        });

        roomListView.setItems(FXCollections.observableArrayList(rooms));
    }

    private RoomType getSelectedRoomType() {
        return roomTypeListView.getSelectionModel().getSelectedItem();
    }

    private Room getSelectedRoom() {
        return roomListView.getSelectionModel().getSelectedItem();
    }

    private void clearRoomType() {
        roomTypeListView.getSelectionModel().select(null);
        roomTypeNameTxt.setText(null);
        roomTypePriceTxt.getValueFactory().setValue(0);
        roomTypeErrorLbl.setText("");
    }

    private void clearRoom() {
        roomListView.getSelectionModel().select(null);
        roomNumberTxt.getValueFactory().setValue(101);;
        roomTypeComboBox.getSelectionModel().select(null);
        roomErrorLbl.setText("");
    }

    @FXML
    public void deleteRoomType() {
        roomTypeManager.deleteRoomType(getSelectedRoomType().getId());
        loadRoomTypes();
        loadRooms();
        clearRoomType();
        clearRoom();
    }

    @FXML
    public void createRoomType() {
        clearRoomType();
    }

    @FXML
    public void saveRoomType() {
        try {
            RoomType existingRoomType = getSelectedRoomType();
            String roomTypeId = existingRoomType == null ? null : existingRoomType.getId();
            RoomType roomType = new RoomType(roomTypeNameTxt.getText(), roomTypeId, roomTypePriceTxt.getValue());
            roomTypeManager.saveRoomType(roomType);
        } catch (Exception e) {
            roomTypeErrorLbl.setText(e.getLocalizedMessage());
            return;
        }

        loadRoomTypes();
        clearRoomType();
    }

    @FXML
    public void deleteRoom() {
        roomManager.deleteRoom(getSelectedRoom().getId());
        loadRooms();
        clearRoom();
    }

    @FXML
    public void createRoom() {
        clearRoom();
    }

    @FXML
    public void saveRoom() {
        try {
            Room existingRoom = getSelectedRoom();
            String roomId = existingRoom == null ? null : existingRoom.getId();

            RoomType roomType = roomTypeComboBox.getSelectionModel().getSelectedItem();
            if (roomType == null) {
                throw new IllegalArgumentException("You must select a room-type");
            }

            Room room = new Room(roomNumberTxt.getValue(), roomType.getId());
            room.setId(roomId);
            roomManager.saveRoom(room);
        } catch (Exception e) {
            roomErrorLbl.setText(e.getLocalizedMessage());
            return;
        }

        loadRooms();
        clearRoom();
    }

    @FXML
    public void goToMainPage() {
        App.SetView("App");
    }
}
