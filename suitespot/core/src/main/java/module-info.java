module suitespot.core {
    requires com.google.gson;    
    exports core;
    exports core.fileUtil;

    opens core.fileUtil to com.google.gson;
}
