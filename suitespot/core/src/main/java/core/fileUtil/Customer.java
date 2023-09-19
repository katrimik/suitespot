package core.fileUtil;



public class Customer {
    
    public Customer(String name, String age) {
        this.name = name;
        this.age = age;
    }
    public Customer(){}
    

    private String name; 
    private String age;


    public String getName() {
        return this.name;
    }


    public String getAge() {
        return this.age;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + name + "'" +
            ", age='" + age + "'" +
            "}";
    }
    
    
}
