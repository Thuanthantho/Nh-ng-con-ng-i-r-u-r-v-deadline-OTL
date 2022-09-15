import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;



public class Admin {
    private static Scanner x;
    Scanner s = new Scanner(System.in);
    String filename = "C:\\Users\\Will\\Documents\\GitHub\\programmming1\\OrderManagementSystem\\adminAccount.txt";
    String fileitem = "C:\\Users\\Will\\Documents\\GitHub\\programmming1\\OrderManagementSystem\\products.txt";


    public Admin(){
        try{
            Path path = Paths.get(filename.toString());
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
                _user = account[0];
                _pass = account[1];
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
    void adminMenu(){
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Update item price");
            System.out.println("-2: Back");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                updatePrice();
            }
            else if (choice.equals("2")) {
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
    void updatePrice(){

        String tempFile = "C:\\Users\\Will\\Documents\\GitHub\\programmming1\\OrderManagementSystem\\tempt.txt";
        File oldFile = new File(fileitem);
        File newFile = new File(tempFile);
        String ID = ""; String title = ""; String price = ""; String category = "";
        try{

            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(fileitem));
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
    }

}
