import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Math;

import static java.nio.file.StandardOpenOption.APPEND;

public class Customer {
    public static int ordID;
    Scanner s = new Scanner(System.in);
    String filename = "resourse/account.txt";
    String fileOrder = "resourse/order.txt";

    static ArrayList<String> nameList = new ArrayList<>(10);
    static ArrayList<String> numberList = new ArrayList<>(10);
    static ArrayList<String> priceList = new ArrayList<>(10);
    static ArrayList<String> CategoryList = new ArrayList<>(10);
    static String nameNumberString;
    static String name;
    static String number;
    static String Price;
    static String Category;
    static boolean retry;

    static String type;
    static String username;
    static String password;
    static String ordStatus;
    public String _user;

    public Customer() {

        try {
            Path path = Paths.get(filename.toString());
            InputStream input = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("-----LOGIN as CUSTOMER-----");
            System.out.print("Enter username: ");
            String username = s.nextLine();
            System.out.print("Enter password: ");
            String password = s.nextLine();
            String _temp = null;

            String _pass;
            boolean found = false;
            while ((_temp = reader.readLine()) != null) {
                String[] account = _temp.split(",");
                _user = account[5];
                _pass = account[6];
                if (_user.equals(username) && _pass.equals((password))) {
                    String a = account[5];
                    found = true;
                }
            }

            if (found == true) {
                System.out.println("Login successfully!");
            } else {
                System.out.println("invalid username or password!");
                new Account();
            }
            reader.close();
            System.out.println("Press any key to show menu...");
            String proc = s.nextLine();
            customerMenu();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    void customerMenu() {
        try {
            System.out.println("------------------------------------");
            System.out.println("-1: Show item info");
            System.out.println("-2: Search by category");
            System.out.println("-3: Create order");
            System.out.println("-4: Show profile");
            System.out.println("-5: Show order information");
            System.out.println("-6: Back");
            System.out.println("------------------------------------");
            System.out.println("Enter Choice");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                readfile();
            } else if (choice.equals("2")) {
                showCategory();
            } else if (choice.equals("3")) {
                createOrder();
            } else if (choice.equals("4")) {
                showProfile();
            } else if (choice.equals("5")) {
                showOrderInfo();
            } else if (choice.equals("6")) {
                new Account();
            } else {
                System.out.println("Invalid choice!");
                System.out.println("Press any key to continue...");
                String proc = s.nextLine();
                customerMenu();
            }
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
                                + "Title: " + number + "\n"
                                + "Price: " + Price + "\n"
                                + "Category: " + Category + "\n");

            }
            System.out.println("Press anykey to sort price...");
            String proc = s.nextLine();
            sortAsc();
        } catch (IOException ioe) {
//            System.out.println(ioe);
        } catch (NumberFormatException nef) {
//            System.out.println(nef);
        }


    }

    void showCategory() {
        String word = "*|||||||";
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter the number of category");
        System.out.println("1: Earbud || 2: Headphone || 3: Phone || 4: Laptop");
        String num = sc1.next();
        if (num.equals("1")) {
            word = "Earbud";
        }
        if (num.equals("2")) {
            word = "Headphone";
        }
        if (num.equals("3")) {
            word = "Phone";
        }
        if (num.equals("4")) {
            word = "Laptop";
        }
        boolean flag = false;
        int count = 0;
        System.out.println("Contents of the line");
        //Reading the contents of the file
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("resourse/products.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();

            if (line.indexOf(word) != -1) {
                flag = true;
                count = count + 1;
                String[] split = line.split(",");
                name = split[0];
                number = split[1];
                Price = split[2];
                Category = split[3];

                System.out.println(
                        "ID: " + name + "\n"
                                + "Title: " + number + "\n"
                                + "Price: " + Price + "\n"
                                + "Category: " + Category + "\n");

            }

        }
        System.out.println("Press any key to show MENU...");
        String proc = s.nextLine();
        customerMenu();

    }

    void showProfile() {
        String word = "*|||||||";
        Scanner sc1 = new Scanner(System.in);
        System.out.println("enter your username to view info");
        String num = sc1.next();

        word = num;


        boolean flag = false;
        int count = 0;
        System.out.println("Contents of the line");
        //Reading the contents of the file
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("resourse/account.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();

            if (line.indexOf(word) != -1) {
                flag = true;
                count = count + 1;
                String[] split = line.split(",");
                name = split[0];
                number = split[1];
                Price = split[2];
                Category = split[3];
                type = split[4];
                username = split[5];
                password = split[6];

                System.out.println(
                        "ID: " + name + "\n"
                                + "name: " + number + "\n"
                                + "address: " + Price + "\n"
                                + "phone: " + Category + "\n"
                                + "type: " + type + "\n"
                                + "username: " + username + "\n");
            }

        }
        System.out.println("Press any key to show MENU...");
        String proc = s.nextLine();
        customerMenu();
    }

    void createOrder() {

        try {
            while (true) {
                Path path = Paths.get(fileOrder.toString());
                OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
                System.out.println("-----ORDER-----");
                ordID = (int) Math.round(Math.random() * 1000);
                System.out.print("Enter item Name: ");
                String itemName = s.nextLine();
                System.out.print("Enter quantity: ");
                String quantity = s.nextLine();
                ordStatus = "paid";

                writer.write(ordID + "," + itemName + "," + quantity + "," + ordStatus);
                writer.newLine();
                System.out.println("Order successfully!");
                System.out.println("Bill's ID: " + ordID);
                writer.close();
                output.close();

                System.out.println("Press Y/y to purchase more!" + "\n" + "Otherwise, press any to CHECKOUT");
                String proc = s.nextLine();
                if (proc.equals("Y") || proc.equals("y")) {
                    continue;
                } else {
                    break;
                }
            }
            customerMenu();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    void showOrderInfo() {
        String word = "*|||||||";
        Scanner sc1 = new Scanner(System.in);
        System.out.println("enter ID of order to view info");
        String num = sc1.next();

        word = num;

        boolean flag = false;
        int count = 0;
        System.out.println("Contents of the line");
        //Reading the contents of the file
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("resourse/order.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();

            if (line.indexOf(word) != -1) {
                flag = true;
                count = count + 1;
                String[] split = line.split(",");
                name = split[1];
                number = split[2];
                System.out.println(
                        "item name: " + name + "\n"
                                + "Quantity: " + number + "\n");
            }
        }
        System.out.println("Press any key to show MENU...");
        String proc = s.nextLine();
        customerMenu();
    }

    void sortAsc() {
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

            }

        } catch (IOException ioe) {
//            System.out.println(ioe);
        } catch (NumberFormatException nef) {
//            System.out.println(nef);
        }
        Collections.sort(nameList);
        Collections.sort(numberList);
        Collections.sort(priceList);
        Collections.sort(CategoryList);
        System.out.println("after sort: ID" + nameList);
        System.out.println("after sort: itemName" + numberList);
        System.out.println("after sort: Price" + priceList);
        System.out.println("after sort: Category" + CategoryList);
        System.out.println("--------------------------------------");
        System.out.println("Press any key to show MENU...");
        Scanner s =new Scanner(System.in);
        String proc = s.nextLine();
        customerMenu();
    }

}
