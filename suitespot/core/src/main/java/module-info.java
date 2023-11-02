module suitespot.core {
  requires transitive com.google.gson;
  requires java.net.http;

  exports core.manager;
  exports core.fileUtil;
  exports core.model;

  opens core.model to com.google.gson;
}
