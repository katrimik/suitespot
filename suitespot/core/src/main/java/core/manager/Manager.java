package core.manager;

import core.fileUtil.FileTypeEnum;
import core.fileUtil.IJsonFileParser;
import core.fileUtil.JsonFileParser;
import core.model.Customer;

public class Manager {
    public static CustomerManager GetCustomerManager() {
        IJsonFileParser<Customer> f = new JsonFileParser<Customer>(Customer.class, FileTypeEnum.CUSTOMER);
        return new CustomerManager(f);
    }
}
