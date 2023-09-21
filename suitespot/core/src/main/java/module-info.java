module suitespot.core {
    requires com.google.gson;
    exports core.manager;
    exports core.fileUtil;
    exports core.model;

    opens core.model to com.google.gson;


}
