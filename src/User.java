import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Verification {
    private int flag = 0;


    Tickets tickets = new Tickets();
    static String S = "";

    FileWriter fileWriter;
    PrintWriter printWriter;
    private String name;
    private String password;
    private String[] trip = new String[10];
    Trips tri = new Trips();
    private static int i;
    private int countwrite = 0;
    public  static int correctuser;
    private static int j=0;
    static User[] users = new User[100];

    public int getFlag() {
        return flag;

    }

    public boolean validate(String email, String pass) {

        load();
        for (int v = 0; v < i; v++) {
            if (users[v].name.equals(email)) {
                if (users[v].password.equals(pass)) {

                    correctuser = v;
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 1)
            return true;
        else return false;

    }

    public void load() {
        tickets.load();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\M3MO\\IdeaProjects\\GoBus\\src\\UserFile.txt"));
        } catch (Exception re) {
            System.out.println(re.getMessage() + "File not Found");
            System.exit(0);
        }
        try {
            i = 0;
            while (scanner.hasNext()) {
                scanner.useDelimiter(",");
                users[i] = new User();
                users[i].name = scanner.next();
                users[i].password = scanner.next();
                users[i].trip = tri.converttoarray(scanner.nextLine());
                users[i].j = tri.words;
                for (int c = 0; c < users[i].j; c++) {
                    String[] a ;
                    a = users[i].trip[c].split("-");
                    users[i].tri.Description.add(a[0]);
                    users[i].tri.Type.add(a[1]);
                    users[i].tri.NumberOfStops.add(Integer.parseInt(a[2]));
                    users[i].tri.NumberOfTickets.add(Integer.parseInt(a[3]));

                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Error reading file ");
        }
    }

    public void addTrip(int indexTrip, int numberTickets) {
        // System.out.println("AvailableSeats: "+tickets.getAvailableSeats(indexTrip-1)+" NumberofTickets: "+numberTickets);
        tickets.load();
        if (flag == 1 && tickets.getAvailableSeats(indexTrip) >= numberTickets) {
            try {

                int value = tickets.getAvailableSeats(indexTrip) - numberTickets;
                //System.out.println("The value: "+value);
                tickets.removeAvailableseats(indexTrip);
                tickets.setAvailableSeats(indexTrip, value);
                // System.out.println("Availabe seats after alternation: "+tickets.getAvailableSeats(indexTrip-1));
                String s = tickets.getDescription(indexTrip) + "-" + tickets.getType(indexTrip) + "-" + tickets.getStop(indexTrip)+"-"+numberTickets;

                users[correctuser].tri.Description.add(tickets.getDescription(indexTrip));
                users[correctuser].tri.Type.add(tickets.getType(indexTrip));
                users[correctuser].tri.NumberOfStops.add(tickets.getStop(indexTrip));
                users[correctuser].tri.NumberOfTickets.add(numberTickets);
                users[correctuser].j++;
                // System.out.println(users[correctuser].trip.get(indexTrip-1));

            } catch (Exception e) {
                System.out.println(e.getMessage()+" Error adding trips to user");
            }
        }
    }

    public void showTrips() {
        int i = 1;
        for (int j = 0; j < tickets.getIndex(); j++) {
            //System.out.print(i+"."+tickets.Description.get(j)+" "+tickets.Type.get(j)+" "+tickets.Stop.get(j)+" "+tickets.Price.get(j)+" "+tickets.AvailableSeats.get(j)+" "+"\n");
            i++;
        }
    }

   // public String showUserTrips(int k) {
       // return users[correctuser].trip.get(k);
    //}
    public String getUserDescription(int k){return users[correctuser].tri.Description.get(k);}
    public String getUserType(int k){
        return users[correctuser].tri.Type.get(k);
    }
    public Integer getUserStops(int k){
        return users[correctuser].tri.NumberOfStops.get(k);
    }
    public Integer getUserTickets(int k){
        return users[correctuser].tri.NumberOfTickets.get(k);
    }

    public int getCorrectuserJ() {

        return users[correctuser].j;
    }

    public void save(){
        try {
            fileWriter = new FileWriter(new File("C:\\Users\\M3MO\\IdeaProjects\\GoBus\\src\\UserFile.txt"));
            printWriter = new PrintWriter(fileWriter);
            int c;
            countwrite=0;
            while (countwrite < i) {
                printWriter.printf("%s,%s,", users[countwrite].name, users[countwrite].password);


                c = 0;
                while (c < users[countwrite].tri.Description.size()) {

                    printWriter.print(users[countwrite].tri.Description.get(c) + "-");
                    printWriter.print(users[countwrite].tri.Type.get(c)+"-");
                    printWriter.print(users[countwrite].tri.NumberOfStops.get(c)+"-");
                    printWriter.print(users[countwrite].tri.NumberOfTickets.get(c)+",");
                    c++;
                }
                printWriter.print("\n");
                countwrite++;
            }
            printWriter.close();
        }catch (Exception e)
        {
            System.out.println("Error saving users");
        }
        tickets.save();
    }
    public static void   DeleteAfterTripDeletion(String s){
        int count=0;
        while(count<i)
        {
            for(int c=0;c<users[count].tri.Description.size();c++){

                String a= users[count].tri.Description.get(c)+"-"+users[count].tri.Type.get(c)+"-"+users[count].tri.NumberOfStops.get(c);

                if(a.equals(s)){

                    users[count].tri.Description.remove(c);
                    users[count].tri.Type.remove(c);
                    users[count].tri.NumberOfStops.remove(c);
                    users[count].j--;


                }
            }
            count++;
        }}
public ArrayList<String> getWords(){
        return users[correctuser].tri.Description;
}

    public void DeleteUserTrip(int k){
        for(int c=0;c<tickets.getIndex();c++)
        {
            if(users[correctuser].tri.Description.get(k).equals(tickets.getDescription(c))&&users[correctuser].tri.Type.get(k).equals(tickets.getType(c))&&users[correctuser].tri.NumberOfStops.get(k)==(tickets.getStop(c)))
            {
                tickets.returnSeats(c,users[correctuser].tri.NumberOfTickets.get(k));
            }



        }

        users[correctuser].tri.Description.remove(k);
        users[correctuser].tri.Type.remove(k);
        users[correctuser].tri.NumberOfStops.remove(k);
        users[correctuser].tri.NumberOfTickets.remove(k);
        users[correctuser].j--;
    }

}