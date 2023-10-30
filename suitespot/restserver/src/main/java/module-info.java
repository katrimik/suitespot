module suitespot.restserver {
    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires suitespot.core;

    opens suitespot.restserver to spring.beans, spring.context, spring.web;
}
