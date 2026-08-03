package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
This one demonstrates a nested POJO -/users/1 returns an address sub-object, so
User needs its own nested Address class to hold it
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id;
    private String name;
    private String email;
    private Address address;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



   @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address
   {
       private String street;
       private String city;

       private String zipcode;

       public String getStreet() {
           return street;
       }

       public void setStreet(String street) {
           this.street = street;
       }

       public String getCity() {
           return city;
       }

       public void setCity(String city) {
           this.city = city;
       }

       public String getZipcode() {
           return zipcode;
       }

       public void setZipcode(String zipcode) {
           this.zipcode = zipcode;
       }



   }


}
