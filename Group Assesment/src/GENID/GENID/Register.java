package GENID;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class Register {
    public static String cusID;
    static Scanner s = new Scanner(System.in);
    static String filename = "account.txt";

    static void register(){
        try{
            Path path = Paths.get(filename.toString());
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("-----REGISTER-----");
            cusID = GENID.IDGen.IDGenerate();
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

            writer.write(cusID+","+ Name +","+ Address+","+ Phone +","+ customertype +","+ username + "," + password);
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
