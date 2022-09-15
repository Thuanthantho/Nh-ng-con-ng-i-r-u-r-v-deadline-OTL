import java.io.*;
import java.util.ArrayList;

public class IDGen {
    public static String IDaddcus;
    private static ArrayList<String> cusIDList = new ArrayList<>(2);
    private static String cus_ID;

    public static String IDGenerate() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("account.txt"));
            String line;
            String question = "";
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                if ( line.trim().length() == 0 ) {
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
        int a  = cusIDList.size();

        for (int i = 0; i <= a; i ++){
            if (i == a ){
                IDaddcus = "C00" + (a + 1);
                cusIDList.add(cus_ID);
            }
        }
        return IDaddcus;

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


