package core.manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.fileUtil.IJsonFileParser;
import core.fileUtil.mock.JsonFileParserMock;
import core.model.RoomType;

public class RoomTypeManagerTest {

    private RoomTypeManager roomTypeManager;
    private RoomType newRoomType;

    @BeforeEach
    public void setup() {
        IJsonFileParser<RoomType> fileParser = new JsonFileParserMock<RoomType>();
        roomTypeManager = new RoomTypeManager(fileParser);
        newRoomType = new RoomType("Suite", null, 200);
    }

    @Test
    public void testDeleteRoomType() {
        String newId = roomTypeManager.saveRoomType(newRoomType);
        roomTypeManager.deleteRoomType(newId);

        List<RoomType> roomTypes = roomTypeManager.listRoomTypes();
        List<RoomType> deletedRoomTypesThatExist = roomTypes.stream().filter(c -> c.getRoomTypeId().equals(newId)).toList();

        assertTrue(roomTypes.size() == 0);
        assertTrue(deletedRoomTypesThatExist.size() == 0);
    }

    @Test
    public void testListRoomTypes() {
        List<RoomType> contains0RoomTypes = roomTypeManager.listRoomTypes();
        roomTypeManager.saveRoomType(newRoomType);
        List<RoomType> contains1RoomTypes = roomTypeManager.listRoomTypes();
        newRoomType.setRoomTypeId(null);
        roomTypeManager.saveRoomType(newRoomType);
        List<RoomType> contains2RoomTypes = roomTypeManager.listRoomTypes();

        assertTrue(contains0RoomTypes.size() == 0);
        assertTrue(contains1RoomTypes.size() == 1);
        assertTrue(contains2RoomTypes.size() == 2);
    }

    @Test
    public void testSaveRoomType() {
        String newRoomTypeId = roomTypeManager.saveRoomType(newRoomType);
        List<RoomType> containsRoomTypes = roomTypeManager.listRoomTypes();
        RoomType foundRoomType = containsRoomTypes.stream().filter(c -> c.getRoomTypeId().equals(newRoomTypeId)).findFirst().orElse(null);
        assertNotNull(foundRoomType);
    }

    @Test
    public void testReadRoomType() {
        String roomTypeId = roomTypeManager.saveRoomType(newRoomType);
        RoomType roomType = roomTypeManager.getRoomType(roomTypeId);
        
        // testing name and not id since id is set dynamically
        assertTrue(newRoomType.getName().equals(roomType.getName()));
    }

    @Test
    public void testUpdateRoomType() {
        String testName = "test update";

        String roomTypeId = roomTypeManager.saveRoomType(newRoomType);
        List<RoomType> beforeUpdateRoomTypes = roomTypeManager.listRoomTypes();
        newRoomType.setName(testName);
        roomTypeManager.saveRoomType(newRoomType);
        List<RoomType> afterUpdateRoomTypes = roomTypeManager.listRoomTypes();

        RoomType roomTypeWithNewName = roomTypeManager.getRoomType(roomTypeId);

        assertTrue(beforeUpdateRoomTypes.size() == 1);
        assertTrue(afterUpdateRoomTypes.size() == 1);
        assertTrue(roomTypeWithNewName.getName().equals(testName));
    }
}
