import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class Items {
    public static String proID;
    private static String ID;
    private static String title;
    private static float price;
    private static String category;
    private static ArrayList<String> IDList = new ArrayList<>(2);
    private static ArrayList<Float> PriceList = new ArrayList<>(2);

    public Items(String ID, String title, float price, String category) {
        Items.ID = ID;
        Items.title = title;
        Items.price = price;
        Items.category = category;
    }

    public static void readfileitems() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("resources/items.txt"));
        String line;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0) {
                continue;  // Skip blank lines
            } else {
                String[] lineSplit = line.split(",");
                //Split line
                ID = lineSplit[0];
                title = lineSplit[1];
                price = Float.parseFloat(lineSplit[2]);
                category = lineSplit[3];

                //add variable into list
                IDList.add(ID);
                PriceList.add(price);
            }
        }
    }

    public static String proIDgenerate() throws IOException {
        readfileitems();
        int b  = IDList.size();

        String proID_final = null;
        for (int i = 0; i <= b; i ++){
            proID ="";
            int proID_num;
            if (i == b ){
                proID = String.valueOf(IDList.get(b - 1));
                proID_num = Integer.parseInt(proID.substring(1, 4)) + 1;
                proID_final = "I00" + Integer.toString(proID_num);
                IDList.add(proID_final);
            }
        }
        return proID_final;
    }

    public static void viewProduct() {
        Path path = Path.of("resources/items.txt");
        try {
            Files.lines(path)
                    .skip(1)
                    . map( line -> {
                        String[] fields = line.split(",");
                        return new Items(fields[0],fields[1],Float.parseFloat(fields[2]),fields[3]);
                    }).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Items.readfileitems();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void addItems() {
        Scanner s = new Scanner(System.in);
        try{
            Path path = Paths.get("resources/items.txt".toString());
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(path, APPEND));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("-----ADD ITEMS-----");
            String ID = proIDgenerate();
            System.out.print("Enter Tittle: ");
            String Tittle = s.nextLine();
            System.out.print("Enter Price: ");
            String Price = s.nextLine();
            System.out.print("Enter Category: ");
            String Category = s.nextLine();

            writer.write(ID+","+ Tittle +","+ Price +","+ Category);
            writer.newLine();
            System.out.println("Add items successfully!");
            writer.close();
            output.close();

            System.out.println("Press any key to continue...");
            String proc = s.nextLine();
            Admin.adminMenu();
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Items{" + '\n' +
                "ID=" + ID + ',' + '\n' +
                "title=" + title + ',' + '\n' +
                "price=" + price + ',' + '\n' +
                "category=" + category + ',' + '\n' +
                '}' + '\n';
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        Items.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Items.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        Items.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        Items.category = category;
    }

    public static ArrayList<String> getIDList() {
        return IDList;
    }

    public static void setIDList(ArrayList<String> IDList) {
        Items.IDList = IDList;
    }

}
