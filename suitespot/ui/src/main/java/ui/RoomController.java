package ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import core.manager.Manager;
import core.manager.RoomManager;
import core.manager.RoomTypeManager;
import core.model.Room;
import core.model.RoomType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class RoomController implements Initializable {
    @FXML
    ListView<Room> roomNumberList;
    @FXML
    ListView<RoomType> roomTypeList;
    @FXML
    TextField roomType, roomNumber, roomPrice;
    @FXML
    Button createRoom, createRoomType, deleteRoomNumber, deleteRoomType;
    @FXML
    Label StatusErrorLbl, StatusCrudLbl;

    private final RoomManager roomManager;
    private final RoomTypeManager roomTypeManager;

    public RoomController() {
        roomManager = Manager.GetRoomManager();
        roomTypeManager = Manager.GetRoomTypeManager();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        roomNumberList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Room>() {
            @Override
            public void changed(ObservableValue<? extends Room> observable, Room oldValue, Room newValue) {
                selectedRoomChanged(newValue);
            }
        });

        roomTypeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RoomType>() {
            @Override
            public void changed(ObservableValue<? extends RoomType> observable, RoomType oldValue, RoomType newValue) {
                selectedRoomTypeChanged(newValue);
            }
        });

        refreshRoomTypes();
        refreshRoomNumbers();
        createRoom.setDisable(true);
        deleteRoomNumber.setDisable(true);
    }

    public void selectedRoomChanged(Room r) {
        if (r == null) {
            StatusCrudLbl.setText("Create new room");
            return;
        }

        StatusCrudLbl.setText("Edit room");

        String typeId = r.getTypeId();
        System.out.println(typeId);
        RoomType roomTypeData = roomTypeManager.getRoomType(typeId);

        roomType.setText(roomTypeData.getName());
        roomNumber.setText(String.valueOf(r.getRoomNumber()));
        roomPrice.setText(String.valueOf(roomTypeData.getPrice()));
    }

    public void selectedRoomTypeChanged(RoomType rt) {
        createRoom.setDisable(rt == null);
        deleteRoomNumber.setDisable(rt == null);

        if (rt == null) {
            return;
        }

        StatusCrudLbl.setText("Edit room");

        roomType.setText(rt.getName());
        roomNumber.setText("");
        roomPrice.setText(String.valueOf(rt.getPrice()));
        refreshRoomNumbers();
    }

    private void resetFields() {
        roomType.setText("");
        roomNumber.setText("");
        roomPrice.setText("");

        roomType.setDisable(false);
        roomNumber.setDisable(false);
        roomPrice.setDisable(false);
    }

    private void refreshRoomNumbers() {
        RoomType roomType = roomTypeList.getSelectionModel().getSelectedItem();
        if (roomType == null) {
            roomNumberList.setItems(null);
            return;
        }

        List<Room> roomNumbers = roomManager.listRooms();
        roomNumbers = roomNumbers.stream().filter(r -> r.getTypeId().equals(roomType.getRoomTypeId())).toList();
        ObservableList<Room> roomObservable = FXCollections.observableArrayList(roomNumbers);
        roomNumberList.setItems(roomObservable);
    }

    private void refreshRoomTypes() {
        List<RoomType> roomTypes = roomTypeManager.listRoomTypes();
        ObservableList<RoomType> roomTypeObservable = FXCollections.observableArrayList(roomTypes);
        roomTypeList.setItems(roomTypeObservable);
    }

    @FXML
    public void createRoom() {
        roomNumber.setText("");
        roomType.setDisable(true);
        roomPrice.setDisable(true);
        roomNumber.setDisable(false);
        roomNumberList.getSelectionModel().select(null);
        StatusCrudLbl.setText("Create room");
    }

    @FXML
    public void createRoomType() {
        resetFields();
        roomType.setDisable(false);
        roomPrice.setDisable(false);
        roomNumber.setDisable(true);
        roomTypeList.getSelectionModel().select(null);
        roomNumberList.getSelectionModel().select(null);
        StatusCrudLbl.setText("Create room type");
    }

    @FXML
    public void deleteRoomNumber() {
        Room listViewRoomNumber = roomNumberList.getSelectionModel().getSelectedItem();
        if (listViewRoomNumber == null) {
            return;
        }

        roomManager.deleteRoom(listViewRoomNumber.getRoomId());

        refreshRoomNumbers();
        resetFields();
    }

    @FXML
    public void deleteRoomType() {
        RoomType listViewRoomType = roomTypeList.getSelectionModel().getSelectedItem();
        if (listViewRoomType == null) {
            return;
        }

        roomTypeManager.deleteRoomType(listViewRoomType.getRoomTypeId());

        refreshRoomNumbers();
        refreshRoomTypes();
        resetFields();
    }

    @FXML
    public void saveRoom() {

        try {
            if (Integer.parseInt(roomPrice.getText()) < 0) {
                throw new IllegalArgumentException("Price under 0");
            }
        } catch (Exception e) {
            StatusErrorLbl.setText("Price must be a positive number");
            return;
        }

        String roomTypeId = null;
        if (roomTypeList.getSelectionModel().getSelectedItem() != null) {
            roomTypeId = roomTypeList.getSelectionModel().getSelectedItem().getRoomTypeId();
        }

        RoomType roomTypeObject = new RoomType(roomType.getText(), roomTypeId, Integer.parseInt(roomPrice.getText()));

        try {
            roomTypeId = roomTypeManager.saveRoomType(roomTypeObject);
        } catch (Exception e) {
            StatusErrorLbl.setText(e.getLocalizedMessage());
        }

        if (!roomTypeId.isEmpty() && !roomNumber.getText().trim().isEmpty() && !roomPrice.getText().trim().isEmpty()) {
            Room room = new Room(Integer.parseInt(roomNumber.getText()), roomTypeId);
            if (roomNumberList.getSelectionModel().getSelectedItem() != null) {
                room.setRoomId(roomNumberList.getSelectionModel().getSelectedItem().getRoomId());
            }

            try {
                roomManager.saveRoom(room);
            } catch (Exception e) {
                StatusErrorLbl.setText(e.getLocalizedMessage());
            }
        }

        refreshRoomTypes();
        refreshRoomNumbers();
        resetFields();
    }

    @FXML
    public void goToMainPage() {
        App.SetView("App");
    }
}
