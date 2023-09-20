package core;

import core.manager.CustomerManager;

public class Suitespot {

    public CustomerManager getCustomerManager() {
        return new CustomerManager();
    }
    
    public static String test(){
        return "core-test-string";
    }
}
