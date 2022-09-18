import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Order {
    private int ord_id;
    private User user;
    private Items item;
    private Date date;
    private static Map<Integer, String> orderInfo;
    // private ArrayList<> detail;
    final String outputFilePath = "resources/orders.txt";

    public Order() {
    }

    public Order(int ord_id, User user, Items item) {
        this.ord_id = ord_id;
        this.user = user;
        this.item = item;

        orderInfo = new HashMap<>();
        date = new Date();
    }

    public static String detail(User user, Items item) {
        return user.getName() + " " + item.getTitle();
    }

    public static int generateID() {
        return (int) (Math.random() * 100 + 1);
    }

    public void printOrder() {
        for (Integer name : orderInfo.keySet()) {
            String key = name.toString();
            String value = orderInfo.get(name);
            System.out.println(key + " " + value);
        }
    }

    public static void createNewOrder(User user, Items item) {
        int ord_id = generateID();
        String orderDetail = detail(user, item);
        for (Map.Entry<Integer, String> list : orderInfo.entrySet()) {
            int key = list.getKey();
            if (key == ord_id) {
                ord_id = generateID();
            }
        }
        orderInfo.put(ord_id, orderDetail);
        System.out.println("Your order id: " + ord_id);
    }

    public void searchOrder() {
        Scanner s = new Scanner(System.in);
        int value = 0;
        while (value != 1) {
            System.out.print("Id search: ");
            int ord_id = s.nextInt();
            for (Map.Entry<Integer, String> list : orderInfo.entrySet()) {
                if (list.getKey() == ord_id) {
                    System.out.println("Your order is: " + ord_id + " " + list.getValue());
                    value = 1;
                }
            }
        }
    }

    public void writeFile() {
        File file = new File(outputFilePath);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("resources/orders.txt"))) {
            for (Map.Entry<Integer, String> entry :
                    orderInfo.entrySet()) {

                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                        + entry.getValue());

                // new line
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // always close the writer
    }


    public int getId() {
        return ord_id;
    }

    public User getUser() {
        return user;
    }

    public Items getItem() {
        return item;
    }

    public Date getDate() {
        return date;
    }

    public Map<Integer, String> getOrderInfo() {
        return orderInfo;
    }

    @Override
    public String toString() {
        return "Order_2{" +
                "id=" + ord_id +
                ", user=" + user +
                ", item=" + item +
                ", date=" + date +
                ", orderInfo=" + orderInfo +
                '}';
    }


}

