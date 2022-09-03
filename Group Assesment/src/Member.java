public class Member {
    private String ID;
    private String name;
    private String address;
    private int phone;
    private String customer_type;
    private String username;
    private String password;


    public Member(String ID, String name, String address, int phone, String customer_type, String username, String password) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.customer_type = customer_type;
        this.username = username;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
