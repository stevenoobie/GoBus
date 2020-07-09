import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.Scanner;
public class Tickets {
        User user;

       // Driver driver=new Driver();
       static    ArrayList<String> Description = new ArrayList<String>();
         static ArrayList<String> Type = new ArrayList<String>();
       static ArrayList<String>  Vehicle_Name= new ArrayList<String>();
       static ArrayList<Integer> Stop = new ArrayList<Integer>();
     static ArrayList<Double> Price = new ArrayList<Double>();
      static ArrayList<Integer> AvailableSeats = new ArrayList<Integer>();
     static ArrayList<Integer> CheckDriver=new ArrayList<Integer>();
    public void removeAvailableseats(int index){
        AvailableSeats.remove(index);
    }
    public void returnSeats(int index,int numOfTickets){
        int x= AvailableSeats.get(index)+numOfTickets;
        AvailableSeats.remove(index);
        AvailableSeats.add(index,x);
    }
    public String getVehicle(int index){return  Vehicle_Name.get(index);}
    public void setDriverToTrip(int index,int flag){CheckDriver.remove(index);CheckDriver.add(index,flag);}
    public int getCheckDriver(int index){ return CheckDriver.get(index); }
    public  String getDescription(int index) {return Description.get(index);}
    public  String getType(int index) {
        return Type.get(index);
    }
    public int getStop(int index) {
        return Stop.get(index);
    }
    public int getAvailableSeats(int index) {return AvailableSeats.get(index);}
    public void setAvailableSeats(int index,int value){AvailableSeats.add(index,value);}

    Scanner scanner =null;
    private static String S;  //S: is the string carrying the info. of the deleted trip to delete it from the user's and driver's filles.
    private static int flag=0;
    private static int flago=0;

    public int getFlagi() {
        return flagi;
    }

    public void setFlagi(int flagi) {
        Tickets.flagi = flagi;
    }

    private static int flagi=0;// a flag to know if a delete took place before saving to delete from user's and driver's too.//flago:is the flag sent to the user.//flagi:is the flag sent to the Driver.
    private int index;
    private int countWrite=0;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public String getS() {
        return S;
    }

    public void load() {
        try{
            scanner=new Scanner(new File(".\\Tickets.txt"));
        }catch(Exception e )
        {
            System.out.println(e.getMessage()+"Error finding Trips file");
            System.exit(0);
        }
        try {
            index=0;
            while (scanner.hasNext()) {
                scanner.useDelimiter(",");
                String s=scanner.nextLine();
                Description.add(index,scanner.next());
                Type.add(index,scanner.next());
                Stop.add(index,Integer.parseInt( scanner.next()));
                Price.add(index,Double.parseDouble(scanner.next()));
                AvailableSeats.add(index,Integer.parseInt(scanner.next()));
                Vehicle_Name.add(index,scanner.next());
                CheckDriver.add(index,Integer.parseInt(scanner.next()));
               // System.out.print("Index: "+index+" ");
                //System.out.println(Description.get(index)+" "+Type.get(index)+" "+Stop.get(index)+" "+Price.get(index)+" "+AvailableSeats.get(index)+" "+Vehicle_Name.get(index)+" "+CheckDriver.get(index));
                index++;

            }
        } catch(Exception er)
        {
            System.out.println(er.getMessage()+"Error loading Trips file");

        }

    }

    public void setFlago(int flag) {
        this.flago = flago;
    }

    public void save(){

        try {
            if(flag==1)
            {

              flag=0;
                }
            fileWriter = new FileWriter(new File(".\\Tickets.txt"));
            printWriter = new PrintWriter(fileWriter);
            countWrite=0;

            printWriter.printf("\n");
            while (countWrite < index) {
                if(countWrite==index-1)
                    printWriter.printf("%s,%s,%d,%f,%d,%s,%d,",Description.get(countWrite),Type.get(countWrite),Stop.get(countWrite),Price.get(countWrite),AvailableSeats.get(countWrite),Vehicle_Name.get(countWrite),CheckDriver.get(countWrite));
                else
                printWriter.printf("%s,%s,%d,%f,%d,%s,%d,\n",Description.get(countWrite),Type.get(countWrite),Stop.get(countWrite),Price.get(countWrite),AvailableSeats.get(countWrite),Vehicle_Name.get(countWrite),CheckDriver.get(countWrite));
                countWrite++;}
            printWriter.close();
        }catch (Exception e)
        {
            System.out.println("Error saving Tickets");
        }

    }

    public static Double getPrice(int index) {
        return Price.get(index);
    }

    String y ;

    public void AddTrip(String desc , String type , int stop , double price, String name) { //call lma el manager y3oz y add trip
        int seats=0;
        if(name=="Bus")
            seats=50;
        else if(name=="Minibus")
            seats=25;
        else if (name=="Limosine")
            seats=5;
        System.out.println(desc+" "+type+" "+stop+" "+price+" "+name+" "+seats);
        System.out.println("Index: "+index);
        Description.add(index,desc) ;
        Type.add(index,type);
        Stop.add(index,stop);
        Price.add(index,price);
        AvailableSeats.add(index,seats);
        Vehicle_Name.add(index,name);
        CheckDriver.add(index,0);
        index++;

    }

    public int getFlago() {

        return flago;
    }

    public void DeleteTrip(int i) { // for manager
            flag=1;
            S=Description.get(i)+" "+Type.get(i)+" "+Stop.get(i);
           // User.DeleteAfterTripDeletion(S);
            Driver.DeleteAfterTripDeletion(S);
        Description.remove(i);
        Type.remove(i);
        Stop.remove(i);
        Price.remove(i);
        AvailableSeats.remove(i);
        Vehicle_Name.remove(i);
        CheckDriver.remove(i);
        index--;


    }

    public boolean Book(int i) { // for user
        if ( AvailableSeats.get(i-1) > 0) {
            AvailableSeats.add(i-1, AvailableSeats.get(i-1)-1);
            y="Booked Succefully";
            return true;
        }
        else {
            y = " No Seats Available" ;
            return false;
        }

    }

    public int getIndex() {
        return index;
    }
}
