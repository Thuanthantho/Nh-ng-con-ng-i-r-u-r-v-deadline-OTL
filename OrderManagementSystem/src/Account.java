import java.util.*;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
public class Account {

    Scanner s = new Scanner(System.in);
    String filename = "C:\\Users\\Will\\Documents\\GitHub\\programmming1\\OrderManagementSystem\\account.txt";
    public Account() {
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Register");
            System.out.println("-2: Login");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                createAccount();
            }
            else if (choice.equals("2")) {
                login();
            }
            else {
                System.out.println("Invalid choice!");
                System.out.println("Press any key to continue...");
                String proc = s.nextLine();
                new Account();
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    void login(){
        try{
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
            while((_temp=reader.readLine()) !=null){
                String[] account = _temp.split(",");
                _user = account[5];
                _pass = account[6];
                if(_user.equals(username)&&_pass.equals((password))){
                    found = true;
                }
            }

            if(found==true){
                System.out.println("Login successfully!");
            }
            else{
                System.out.println("invalid username or password!");
            }
            reader.close();
            System.out.println("Press any key to continue...");
            String proc = s.nextLine();
            new Account();
        }catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    void createAccount(){
        try{
            Path path = Paths.get(filename.toString());
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("-----REGISTER-----");
            System.out.print("Enter ID: ");
            String ID = s.nextLine();
            System.out.print("Enter Name: ");
            String Name = s.nextLine();
            System.out.print("Enter Address: ");
            String Address = s.nextLine();
            System.out.print("Enter Phone: ");
            String Phone = s.nextLine();
            System.out.print("Enter customer type: ");
            String customertype = s.nextLine();
            System.out.print("Enter username: ");
            String username = s.nextLine();
            System.out.print("Enter password: ");
            String password = s.nextLine();

            writer.write(ID+","+ Name +","+ Address+","+ Phone +","+ customertype +","+ username + "," + password);
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
    public static void main(String[] args){
        System.out.println("*********************************");
        System.out.println("* COSC2081 GROUP ASSIGNMENT     *\n" +
                "* STORE ORDER MANAGEMENT SYSTEM *\n" +
                "* Instructor: Mr. Minh Vu       *\n" +
                "* Group: Group Name             *\n" +
                "* s3751366, Cao Huy Tri         *\n" +
                "* s3911365, Vo Ngoc Diem Tien   *\n" +
                "* s3911999, Nguyen Minh Thuan   *\n" +
                "* s3879343, Quach Nhat Huy      *");
        System.out.println("*********************************\n");

        new Account();
    }
}
