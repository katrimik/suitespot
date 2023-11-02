module suitespot.core {
  requires transitive com.google.gson;
  requires java.net.http;

  exports core.manager;
  exports core.fileUtil;
  exports core.model;
  exports core.manager.interfaces;

  opens core.model to com.google.gson;
}
