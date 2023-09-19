module suitespot.core {
    requires com.google.gson;    
    exports core;
    

    opens core.model to com.google.gson;
}
