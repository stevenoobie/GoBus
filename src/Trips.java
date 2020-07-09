import java.util.ArrayList;

public class Trips {
     int words=0;
    ArrayList<String>Description=new ArrayList<>();
    ArrayList<String >Type=new ArrayList<>();
    ArrayList<Integer>NumberOfTickets=new ArrayList<>();
    ArrayList<Integer>NumberOfStops=new ArrayList<>();
    ArrayList<String>Vehicle=new ArrayList<>();


    public String[] converttoarray(String a) {
        char[] B;
        int i = 0, z = 0, C = 0, S = 0; //S: start of index , C:End of index ,Z: index of the array of strings
        words = 0;
        String[] strings=new String[10];
        B = a.toCharArray();

        while (i < B.length) {
            if (B[i] == ',' && i == 0) {
                S = 1;
                C = 1;
            } else if (B[i] == ',' && i != 0) {

                strings[z]=new String(B).substring(S, C);
                words++;
                z++;
                S = i + 1;
                C = i + 1;

            } else
                C++;
            i++;
        }
        return strings;
    }

}