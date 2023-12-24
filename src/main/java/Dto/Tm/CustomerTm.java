package Dto.Tm;

public class CustomerTm {
    private String custId;

    private String name;
    private String address;
    private String contact;

//    public String getContact2() {
//        return contact2;
//    }

//    public void setContact2(String contact2) {
//        this.contact2 = contact2;
//    }

    public CustomerTm(String custId, String name, String address, String contact, String contact2, String pets) {
        this.custId = custId;
        this.name = name;
        this.address = address;
        this.contact = contact;
//        this.contact2 = contact2;
//        this.pets = pets;
    }

//    private String contact2;
//    private String pets;

    @Override
    public String toString() {
        return "CustomerTm{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
//                ", pets='" + pets + '\'' +
                '}';
    }

    public CustomerTm() {
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

//    public String getPets() {
//        return pets;
//    }

//    public void setPets(String pets) {
//        this.pets = pets;
//    }

    public CustomerTm(String custId, String name, String address, String contact, String pets) {
        this.custId = custId;
        this.name = name;
        this.address = address;
        this.contact = contact;
//        this.pets = pets;
    }
}
