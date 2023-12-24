package Dto;

public class CustomerDto {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerContact;


    public CustomerDto(String customerId, String customerName, String customerAddress, String customerContact, String customerContact2) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
//        this.customerContact2 = customerContact2;
    }

    public CustomerDto(String customer, String contact) {
        this.customerName = customer;
        this.customerContact = contact;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerContact='" + customerContact + '\'' +
//                ", customerContact2='" + customerContact2 + '\'' +
                '}';
    }

//    public String getCustomerContact2() {
//        return customerContact2;
//    }

//    public void setCustomerContact2(String customerContact2) {
//        this.customerContact2 = customerContact2;
//    }

    public CustomerDto() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public CustomerDto(String customerId, String customerName, String customerAddress, String customerContact) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
    }

    public String getCustomerPet() {
        return null;
    }
}
