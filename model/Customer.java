package model;

import java.util.Objects;

public class Customer implements Cloneable {
    private static long counter = 100;
    private final String customerId;
    private String name, email, mobile;
    private Address address;

    public Customer(String name, String email, String mobile) { this(name, email, mobile, null); }
    public Customer(String name, String email, String mobile, Address address) {
        this.customerId = "CUST" + (++counter);
        this.name = name; this.email = email; this.mobile = mobile; this.address = address;
    }
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public Address getAddress() { return address; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setAddress(Address address) { this.address = address; }

    public static class Address {
        private String line, city, pincode;
        public Address(String line, String city, String pincode) { this.line=line; this.city=city; this.pincode=pincode; }
        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }
        @Override public String toString() { return line + ", " + city + " - " + pincode; }
    }
    @Override public Customer clone() {
        try {
            Customer copy = (Customer) super.clone();
            if (address != null) copy.address = new Address(address.line, address.city, address.pincode);
            return copy;
        } catch (CloneNotSupportedException e) { throw new AssertionError(e); }
    }
    @Override public boolean equals(Object obj) { return obj instanceof Customer c && customerId.equals(c.customerId); }
    @Override public int hashCode() { return Objects.hash(customerId); }
    @Override public String toString() { return "Customer ID: " + customerId + ", Name: " + name + ", Email: " + email + ", Mobile: " + mobile + ", Address: " + address; }
}
