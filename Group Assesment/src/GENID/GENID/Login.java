package GENID;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Login {

    static Scanner s = new Scanner(System.in);
    static String filename = "Group Assesment/src/GENID/account.txt";

    static void login(){
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
}
