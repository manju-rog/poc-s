package com.example.demo;



public class InputPayload {
    private String customerId;
    private String customerName;
    private Address address;

    // Getters and setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public static class Address {
        private String address1;
        private String address2;

        // Getters and setters
        public String getAddress1() { return address1; }
        public void setAddress1(String address1) { this.address1 = address1; }

        public String getAddress2() { return address2; }
        public void setAddress2(String address2) { this.address2 = address2; }
    }
}
