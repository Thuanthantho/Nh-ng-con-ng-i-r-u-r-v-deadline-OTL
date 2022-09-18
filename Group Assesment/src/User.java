import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class User {
    protected static String ID;
    protected static String name;
    protected static String address;
    protected static String phone;
    protected static String username;
    protected static String password;
    static Scanner s = new Scanner(System.in);
    static String filename = "resources/account.txt";
    static String fileitem = "resources/items.txt";

    public User(String ID, String name, String address, String phone, String username, String password) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    //Register method
    static void register() {
        try{
            Path path = Paths.get("resources/account.txt".toString());
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("-----REGISTER-----");
            String ID = IDGen.IDGenerate();
            System.out.print("Enter Name: ");
            String Name = s.nextLine();
            System.out.print("Enter Address: ");
            String Address = s.nextLine();
            System.out.print("Enter Phone: ");
            String Phone = s.nextLine();
            System.out.print("Enter username: ");
            String username = s.nextLine();
            System.out.print("Enter password: ");
            String password = s.nextLine();
            String customertype = "Regular";

            writer.write(ID+","+ Name +","+ Address+","+ Phone + username + "," + password + ","+ customertype);
            writer.newLine();
            System.out.println("Register successfully!");
            writer.close();
            output.close();

            System.out.println("Press any key to continue...");
            String proc = s.nextLine();
            new Account();
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    //getter setter
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

class Admin extends User {

    public Admin(String ID, String name, String address, String username, String password, String phone) {
        super(ID, name, address, username, password, phone);
    }

    static void adminlogin() {
        try{
            Path path = Paths.get("resources/adminAccount.txt".toString());
            InputStream input = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("-----LOGIN as ADMIN-----");
            System.out.print("Enter username: ");
            String username = s.nextLine();
            System.out.print("Enter password: ");
            String password = s.nextLine();
            String _temp = null;
            String _user;
            String _pass;
            boolean found = false;
            while((_temp=reader.readLine()) !=null){
                String[] account = _temp.split(",");
                _user = account[4];
                _pass = account[5];
                if(_user.equals(username)&&_pass.equals((password))){
                    found = true;
                }
            }

            if(found==true){
                System.out.println("Login as admin successfully!");
            }
            else{
                System.out.println("invalid username or password!");
                new Account();
            }
            reader.close();
            System.out.println("Press any key to show MENU...");
            String proc = s.nextLine();
            adminMenu();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    static void adminMenu(){
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Update item price");
            System.out.println("-2: View the items");
            System.out.println("-3: Add the items");
            System.out.println("-4: View customer list");
            System.out.println("-5: Log out");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                updatePrice();
            }
            else if (choice.equals("2")) {
                Items.viewProduct();
            }
            else if (choice.equals("3")) {
                Items.addItems();
            }
            else if (choice.equals("4")) {
                viewCustomer();
            }
            else if (choice.equals("5")) {
                new Account();
            }
            else {
                System.out.println("Invalid choice!");
                System.out.println("Press any key to continue...");
                String proc = s.nextLine();
                adminMenu();
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    static void updatePrice(){

        String tempFile = "resources/tempt.txt";
        File oldFile = new File(fileitem);
        File newFile = new File(tempFile);
        String ID = ""; String title = ""; String price = ""; String category = "";
        try{

            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(fileitem));
            x.useDelimiter("[,\n]");

            System.out.print("Enter ID ITEMS: ");
            String editTerm = s.nextLine();
            System.out.print("Enter PRICE: ");
            String newPrice = s.nextLine();

            while(x.hasNext()){
                ID = x.next();
                title = x.next();
                price = x.next();
                category = x.next();
                if(ID.equals(editTerm)){
                    pw.println(ID + "," + title + "," + newPrice + "," + category);

                    System.out.println("The price of " + title + " is: " + newPrice);
                    System.out.println("Press any key to show MENU...");
                    String proc = s.nextLine();
                    adminMenu();

                }
                else{
                    pw.println(ID + "," + title + "," + price + "," + category);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(fileitem);
            newFile.renameTo(dump);
        }
        catch (Exception e){
            System.out.println("error!");
        }
        System.out.println("Press any key to continue...");
        String proc = s.nextLine();
        adminMenu();
    }

    public static void viewCustomer() {
        Path path = Path.of("resources/account.txt");
        try {
            Files.lines(path)
                    .skip(1)
                    . map( line -> {
                        String[] fields = line.split(",");
                        return new Customer(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6]);
                    }).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Press any key to continue...");
        String proc = s.nextLine();
        adminMenu();
    }
}

class Customer extends User {
    public String customer_type;

    public Customer(String ID, String name, String address,String phone, String username, String password, String customer_type) {
        super(ID, name, address, phone, username, password);
        this.customer_type = customer_type;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID:" + ID + ',' +
                "Name:" + name + ',' +
                "Address:" + address + ',' +
                "Username" + username + ',' +
                "Password" + password +  ',' +
                "Customer type=" + customer_type +
                '}';
    }

    static void cuslogin() {
        try {
            Path path = Paths.get(filename.toString());
            InputStream input = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("-----LOGIN-----");
            System.out.print("Enter username: ");
            String username = s.nextLine();
            System.out.print("Enter password: ");
            String password = s.nextLine();
            String _temp = null;
            String _user;
            String _pass;
            boolean found = false;
            while ((_temp = reader.readLine()) != null) {
                String[] account = _temp.split(",");
                _user = account[4];
                _pass = account[5];
                if (_user.equals(username) && _pass.equals((password))) {
                    found = true;
                }
            }
            if (found == true) {
                System.out.println("Login successfully!");
            } else {
                System.out.println("invalid username or password!");
            }
            reader.close();
            System.out.println("Press any key to show MENU...");
            String proc = s.nextLine();
            customerMenu();
        }
        catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    static void customerMenu(){
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Buy items");
            System.out.println("-2: View the items");
            System.out.println("-3: Back");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                //updatePrice();
            }
            else if (choice.equals("2")) {
                Items.viewProduct();
            }
            else if (choice.equals("3")) {
                new Account();
            }
            else {
                System.out.println("Invalid choice!");
                System.out.println("Press any key to continue...");
                String proc = s.nextLine();
                customerMenu();
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
}
