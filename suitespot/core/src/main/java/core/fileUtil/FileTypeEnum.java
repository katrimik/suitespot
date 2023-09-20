package core.fileUtil;

public enum FileTypeEnum {
    CUSTOMER("customer"),
    BOOKING("booking"),
    ROOM("room");
    
    private final String fileName;
    
    FileTypeEnum(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}