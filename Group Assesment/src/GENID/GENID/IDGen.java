package GENID;
import java.io.*;
import java.util.ArrayList;

public class IDGen {
    public static String IDaddcus;
    private static ArrayList<String> cusIDList = new ArrayList<>(2);
    private static String cus_ID;

    public static <string> String IDGenerate() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("account.txt"));
            String line;
            String question = "";
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;  // Skip blank lines
                } else {
                    String[] lineSplit = line.split(",");
                    cus_ID = lineSplit[0];
                    cusIDList.add(cus_ID);
                }//do something}
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//            for (String ID : IDList) {
//                if (proID.equals(ID)) {
//                    System.out.println("Product ID found in the ID list.");
//                }
//            }
        int a = cusIDList.size();


        String IDaddcus_final = null;
        for (int i = 0; i <= a; i++) {
            IDaddcus = "";
            int IDaddcus_num;
            if (i == a) {
                IDaddcus = String.valueOf(cusIDList.get(a - 1));
                IDaddcus_num = Integer.parseInt(IDaddcus.substring(1, 4)) + 1;
                IDaddcus_final = "C00" + Integer.toString(IDaddcus_num);
                cusIDList.add(IDaddcus_final);
            }
        }
        return IDaddcus_final;

    }

    public static ArrayList<String> getCusIDList() {
        return cusIDList;
    }

    public static void setCusIDList(ArrayList<String> cusIDList) {
        IDGen.cusIDList = cusIDList;
    }

    public static String getCus_ID() {
        return cus_ID;
    }

    public static void setCus_ID(String cus_ID) {
        IDGen.cus_ID = cus_ID;
    }

}


