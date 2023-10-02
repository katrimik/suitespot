package core.fileUtil;

public enum FileTypeEnum {
    CUSTOMER("customer"),
    BOOKING("booking"),
    ROOM("room"),
    ROOM_TYPE("room-type");
    
    private final String fileName;
    
    FileTypeEnum(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}