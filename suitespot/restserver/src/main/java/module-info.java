module suitespot.restserver {
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires suitespot.core;

    opens suitespot.restserver to spring.beans, spring.context, spring.web;
}
