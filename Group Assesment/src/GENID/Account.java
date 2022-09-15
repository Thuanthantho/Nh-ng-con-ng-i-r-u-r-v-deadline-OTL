import java.util.*;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
public class Account {

    Scanner s = new Scanner(System.in);
    public Account() {
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Register");
            System.out.println("-2: Login");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice =s.nextLine();
            if (choice.equals("1")) {
                Register.register();
            }
            else if (choice.equals("2")) {
                Login.login();
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
