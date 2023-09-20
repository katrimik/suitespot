module suitespot.core {
    requires com.google.gson;    
    exports core.manager;
    exports core.model;
    exports core;

    opens core.model to com.google.gson;
}
