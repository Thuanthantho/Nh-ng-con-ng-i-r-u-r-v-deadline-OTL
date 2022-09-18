//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class Admin {
    public static int itemID;
    private static Scanner x;
    Scanner s;
    String filename;
    String fileitem = "resourse/products.txt";
    String fileorder = "resourse/order.txt";
    static ArrayList<String> nameList = new ArrayList<>(10);
    static ArrayList<String> numberList = new ArrayList<>(10);
    static ArrayList<String> priceList = new ArrayList<>(10);
    static ArrayList<String> CategoryList = new ArrayList<>(10);
    static String nameNumberString;
    static String name;
    static String number;
    static String Price;
    static String Category;
    static String type;
    static String username;
    static String adminPassword;

    public Admin() {
        s = new Scanner(System.in);
        filename = "resourse/adminAccount.txt";

        try {
            Path path = Paths.get(filename.toString());
            InputStream input = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("-----LOGIN as ADMIN-----");
            System.out.print("Enter username: ");
            String username = s.nextLine();
            System.out.print("Enter password: ");
            String password = s.nextLine();
            String _temp = null;
            boolean found = false;

            while ((_temp = reader.readLine()) != null) {
                String[] account = _temp.split(",");
                String _user = account[0];
                String _pass = account[1];
                if (_user.equals(username) && _pass.equals(password)) {
                    found = true;
                }
            }

            if (found) {
                System.out.println("Login as admin successfully!");
            } else {
                System.out.println("invalid username or password!");
                new Account();
            }

            reader.close();
            System.out.println("Press any key to show MENU...");
            String proc = s.nextLine();
            adminMenu();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }

    void adminMenu() {
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Update item price");
            System.out.println("-2: Add item");
            System.out.println("-3: Show all item info");
            System.out.println("-4: Show all Customer info");
            System.out.println("-5: Show all order info");
            System.out.println("-6: Back");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                updatePrice();
            } else if (choice.equals("2")) {
                addItem();
            } else if (choice.equals("3")) {
                readfile();
            } else if (choice.equals("4")) {
                showCusProfile();
            } else if (choice.equals("5")) {
                showAllOrderInfor();
            }
//            else if (choice.equals("6")) {
//                changeStatus();
//            }
            else if (choice.equals("6")) {
                new Account();
            } else {
                System.out.println("Invalid choice!");
                System.out.println("Press any key to continue...");
                String proc = s.nextLine();
                adminMenu();
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }

    void updatePrice() {
        String tempFile = "resourse/temp.txt";
        File oldFile = new File(fileitem);
        File newFile = new File(tempFile);
        String ID="";String name="";String price="";String category="";
        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(fileitem));
            x.useDelimiter("[,\n]");
            System.out.println("ID items to adjust: ");
            String editTerm=s.nextLine();
            System.out.println("Updated price is: ");
            String newPrice=s.nextLine();

            while(x.hasNext())
            {
                ID = x.next();
                name = x.next();
                price = x.next();
                category = x.next();
                if(ID.equals(editTerm)){
                    pw.print(ID+","+name+","+newPrice+","+category);

                }
                else {
                    pw.print(ID+","+name+","+price+","+category);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File (fileitem);
            newFile.renameTo(dump);

            System.out.println("Press any key to show MENU...");
            String proc = s.nextLine();
            adminMenu();
        }
        catch (Exception e){
            System.out.println("Error!");
        }

    }

    void addItem() {
        try {
            Path path = Paths.get(fileitem.toString());
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("-----ADD ITEM-----");
            itemID = (int) Math.round(Math.random() * 1000);
            System.out.print("Item Name: ");
            String Name = s.nextLine();
            System.out.print("Item price: ");
            String price = s.nextLine();
            System.out.print("Item category: ");
            String category = s.nextLine();

            writer.write(itemID + "," + Name + "," + price + "," + category);
            writer.newLine();
            System.out.println("Add item successfully!");
            System.out.println("New item: " + Name + " has ID: " + itemID);
            writer.close();
            output.close();

            System.out.println("Press any key to continue...");
            String proc = s.nextLine();
            adminMenu();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    void readfile() //index length error!
    {
        try {
            // Using file pointer creating the file.
            File file = new File("resourse/products.txt");

            if (!file.exists()) {

                // Create a new file if not exists.
                file.createNewFile();
            }

            // Opening file in reading and write mode.

            RandomAccessFile raf
                    = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Traversing the file
            // getFilePointer() give the current offset
            // value from start of the file.
            while (raf.getFilePointer() < raf.length()) {

                // reading line from the file.
                nameNumberString = raf.readLine();

                // splitting the string to get name and
                // number
                String[] lineSplit
                        = nameNumberString.split(",");

                // separating name and number.
                name = lineSplit[0];
                number = lineSplit[1];
                Price = lineSplit[2];
                Category = lineSplit[3];

                nameList.add(name);
                numberList.add(number);
                priceList.add(Price);
                CategoryList.add(Category);


                // Print the contact data
                System.out.println(
                        "ID: " + name + "\n"
                                + "Name: " + number + "\n"
                                + "Price: " + Price + "\n"
                                + "Category: " + Category + "\n");

            }
            System.out.println("Press any key to show MENU...");
            String proc = s.nextLine();
            adminMenu();
        } catch (IOException ioe) {
//            System.out.println(ioe);
        } catch (NumberFormatException nef) {
//            System.out.println(nef);
        }


    }

    void showCusProfile() {
        try {
            // Using file pointer creating the file.
            File file = new File("resourse/account.txt");

            if (!file.exists()) {

                // Create a new file if not exists.
                file.createNewFile();
            }

            // Opening file in reading and write mode.

            RandomAccessFile raf
                    = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Traversing the file
            // getFilePointer() give the current offset
            // value from start of the file.
            while (raf.getFilePointer() < raf.length()) {

                // reading line from the file.
                nameNumberString = raf.readLine();

                // splitting the string to get name and
                // number
                String[] split
                        = nameNumberString.split(",");

                // separating name and number.
                name = split[0];
                number = split[1];
                Price = split[2];
                Category = split[3];
                type = split[4];
                username = split[5];
                adminPassword = split[6];

                System.out.println(
                        "ID: " + name + "\n"
                                + "name: " + number + "\n"
                                + "address: " + Price + "\n"
                                + "phone: " + Category + "\n"
                                + "type: " + type + "\n"
                                + "username: " + username + "\n");

            }
            System.out.println("Press any key to show MENU...");
            String proc = s.nextLine();
            adminMenu();
        } catch (IOException ioe) {
//            System.out.println(ioe);
        } catch (NumberFormatException nef) {
//            System.out.println(nef);
        }


    }

    void showAllOrderInfor() {
        try {
            // Using file pointer creating the file.
            File file = new File("resourse/order.txt");

            if (!file.exists()) {

                // Create a new file if not exists.
                file.createNewFile();
            }

            // Opening file in reading and write mode.

            RandomAccessFile raf
                    = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Traversing the file
            // getFilePointer() give the current offset
            // value from start of the file.
            while (raf.getFilePointer() < raf.length()) {

                // reading line from the file.
                nameNumberString = raf.readLine();

                // splitting the string to get name and
                // number
                String[] split
                        = nameNumberString.split(",");

                // separating name and number.
                name = split[0];
                number = split[1];
                Price = split[2];
                Category = split[3];

                System.out.println(
                        "ID: " + name + "\n"
                                + "item name: " + number + "\n"
                                + "quantity: " + Price + "\n"
                                + "order status: " + Category + "\n");

            }
            System.out.println("Press any key to show MENU...");
            String proc = s.nextLine();
            adminMenu();
        } catch (IOException ioe) {
//            System.out.println(ioe);
        } catch (NumberFormatException nef) {
//            System.out.println(nef);
        }


    }

}

