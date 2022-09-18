import java.util.*;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
public class Account {
    private static Scanner x;
    Scanner s = new Scanner(System.in);
    String filename = "resourse/account.txt";
    public static String cusID;
    public Account() {
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Register for Customer");
            System.out.println("-2: Login as Customer");
            System.out.println("-3: Login as Admin");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                createAccount();
            }
            else if (choice.equals("2")) {
                new Customer();
            }
            else if (choice.equals("3")) {
                new Admin();
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
    void createAccount(){
        try{
            Path path = Paths.get(filename.toString());
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("-----REGISTER-----");
            cusID = IDGen.IDGenerate();
            System.out.print("Enter Name: ");
            String Name = s.nextLine();
            System.out.print("Enter Address: ");
            String Address = s.nextLine();
            System.out.print("Enter Phone: ");
            String Phone = s.nextLine();
            String customerType = "regular";
            System.out.print("Enter username: ");
            String username = s.nextLine();
            System.out.print("Enter password: ");
            String password = s.nextLine();

            writer.write(cusID+","+ Name +","+ Address+","+ Phone +","+ customerType +","+ username + "," + password);
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





}
