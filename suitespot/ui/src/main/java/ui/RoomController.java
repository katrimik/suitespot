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
    TextField roomType, roomNumber, roomPrice, searchRoomType, searchRoomNumber;
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
    }

    @FXML
    public void selectedRoomChanged(Room r) {
        if (r == null) {
            StatusCrudLbl.setText("Create new room");
            return;
        }
        ;

        StatusCrudLbl.setText("Edit room");

        String typeId = r.getTypeId();
        RoomType roomTypeData = roomTypeManager.getRoomType(typeId);

        roomType.setText(roomTypeData.getName());
        roomNumber.setText(String.valueOf(r.getRoomNumber()));
        roomPrice.setText(String.valueOf(roomTypeData.getPrice()));
    }

    @FXML
    public void selectedRoomTypeChanged(RoomType rt) {
        if (rt == null) {
            StatusCrudLbl.setText("Create new room type");
            return;
        }
        ;

        StatusCrudLbl.setText("Edit room");

        roomType.setText(rt.getName());
        roomNumber.setText("");
        roomPrice.setText(String.valueOf(rt.getPrice()));

    }

    private void resetFields() {
        roomType.setText("");
        roomNumber.setText("");
        roomPrice.setText("");
        searchRoomType.setText("");
        searchRoomNumber.setText("");
    }

    private void refreshRoomNumbers() {
        List<Room> roomNumbers = roomManager.listRooms();
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
        resetFields();
    }

    @FXML
    public void createRoomType() {
        resetFields();
    }

    @FXML
    public void deleteRoomNumber() {
        Room listViewRoomNumber = roomNumberList.getSelectionModel().getSelectedItem();
        if (listViewRoomNumber == null)
            return;

        roomManager.deleteRoom(listViewRoomNumber.getRoomId());

        refreshRoomNumbers();
        resetFields();
    }

    @FXML
    public void deleteRoomType() {
        RoomType listViewRoomType = roomTypeList.getSelectionModel().getSelectedItem();
        if (listViewRoomType == null)
            return;

        roomTypeManager.deleteRoomType(listViewRoomType.getRoomTypeId());

        refreshRoomNumbers();
        refreshRoomTypes();
        resetFields();
    }

    @FXML
    public void saveRoom() {

        RoomType roomTypeObject;

        if (roomTypeList.getSelectionModel().getSelectedItem() == null) {
            roomTypeObject = new RoomType(roomType.getText(), null, Integer.parseInt(roomPrice.getText()));
        } else {
            roomTypeObject = roomTypeList.getSelectionModel().getSelectedItem();
        }

        String roomTypeId = "";
        try {
            roomTypeId = roomTypeManager.saveRoomType(roomTypeObject);
        } catch (Exception e) {
            StatusErrorLbl.setText(e.getLocalizedMessage());
        }

        if (!roomTypeId.isEmpty() && !roomNumber.getText().trim().isEmpty() && !roomPrice.getText().trim().isEmpty()) {

            Room room;
            if (roomNumberList.getSelectionModel().getSelectedItem() == null) {
                room = new Room(Integer.parseInt(roomNumber.getText()), roomTypeId);
            } else {
                room = roomNumberList.getSelectionModel().getSelectedItem();
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
    public void searchRoomNumber() {
        roomNumberList.getItems().stream()
                .filter(room -> room.getRoomNumber() == Integer.parseInt(searchRoomNumber.getText()))
                .findAny();

        refreshRoomNumbers();
    }

    @FXML
    public void searchRoomType() {
        roomTypeList.getItems().stream()
                .filter(rt -> rt.getName() == searchRoomType.getText())
                .findAny();

        refreshRoomTypes();
    }

    @FXML
    public void previousPage() {
        System.out.println("hei");
    }

}
