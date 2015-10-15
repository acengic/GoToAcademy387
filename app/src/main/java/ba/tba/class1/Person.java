package ba.tba.class1;

import com.orm.SugarRecord;


public class Person extends SugarRecord<Person> {
    String name;
    String lastName;
    String address;
    String city;
    String country;
    String ZIP;


    public Person(){
    }

    public Person(String name, String lastName, String address, String city, String country, String ZIP){
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.ZIP = ZIP;

    }
}