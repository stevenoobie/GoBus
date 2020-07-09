import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver implements Verification{

    Tickets ticket=new Tickets();
    FileWriter fileWriter ;
    PrintWriter printWriter;
    private int countSave=0;
    private String Email,Password,Name;
    private int Age,j;
    private String [] trips;
    static Driver []drivers=new Driver[100];
    private static int i=0;
    private boolean x=false;
    private int validDriver,countValidate=0,flag=0,countDisplay=0;
    Trips trip=new Trips();

    public String getName() {
        return drivers[validDriver].Name;
    }

    public int getAge() {
        return drivers[validDriver].Age;
    }
    public String getNameDrivers(int k){
        return drivers[k].Name;
    }

    public boolean validate (String email, String pw){
        load();
        while(countValidate<i)
        {
            if(drivers[countValidate].Email.equals(email)&&drivers[countValidate].Password.equals(pw))
            {
                validDriver=countValidate;
                x = true ;
                break;
            }
            countValidate++;

        }
        return x ;

    }
    public void load(){
        ticket.load();
        Scanner scanner=null;
        try{
            scanner=new Scanner(new File("C:\\Users\\M3MO\\IdeaProjects\\GoBus\\src\\Driver.txt"));
        }
        catch (Exception re)
        {
            System.out.println(re.getMessage()+"Driver file not Found");
            System.exit(0);
        }

        try {
            i=0;
            while(scanner.hasNext())
            {
                scanner.useDelimiter(",");
                drivers[i]=new Driver();
                drivers[i].Email=scanner.next();
                drivers[i].Password=scanner.next();
                drivers[i].Name=scanner.next();
                drivers[i].Age=Integer.parseInt(scanner.next());
                drivers[i].trips=trip.converttoarray(scanner.nextLine());
                drivers[i].j=trip.words;
                for (int c = 0; c < drivers[i].j; c++) {
                    String[] a ;
                    a = drivers[i].trips[c].split("-");
                    drivers[i].trip.Description.add(a[0]);
                    drivers[i].trip.Type.add(a[1]);
                    drivers[i].trip.NumberOfStops.add(Integer.parseInt(a[2]));
                    drivers[i].trip.Vehicle.add(a[3]);

                }
                i++;
                //System.out.println();

            }}
        catch (Exception e)
        {
            System.out.println(e.getMessage()+" Error loading Driver's file ");
        }
    }



    int Flag=0;//flag for saving tickets when addtrip happens.
    public void AddTripsToDriver(int ChooseDriver,int ChosenTicket) {
        Flag = 1;
        System.out.println(ticket.getCheckDriver(ChosenTicket));
        if (ticket.getCheckDriver(ChosenTicket)==0) {
            ticket.setDriverToTrip(ChosenTicket, 1);

            drivers[ChooseDriver].trip.Description.add(ticket.getDescription(ChosenTicket));
            drivers[ChooseDriver].trip.Type.add(ticket.getType(ChosenTicket));
            drivers[ChooseDriver].trip.NumberOfStops.add(ticket.getStop(ChosenTicket));
            drivers[ChooseDriver].trip.Vehicle.add(ticket.getVehicle(ChosenTicket));
            drivers[ChooseDriver].j++;


        } else
            System.out.println("Trip assigned to other driver.");
    }

    public void save(){
        try{

            fileWriter=new FileWriter(new File("C:\\Users\\M3MO\\IdeaProjects\\GoBus\\src\\Driver.txt"));
            printWriter=new PrintWriter(fileWriter);
            countSave=0;

            while(countSave<i)
            {
                printWriter.printf("%s,%s,%s,%d,",drivers[countSave].Email,drivers[countSave].Password,drivers[countSave].Name,drivers[countSave].Age);
                for(int c=0;c<drivers[countSave].trip.Description.size();c++)
                {
                    printWriter.print(drivers[countSave].trip.Description.get(c) + "-");
                    printWriter.print(drivers[countSave].trip.Type.get(c)+"-");
                    printWriter.print(drivers[countSave].trip.NumberOfStops.get(c)+"-");
                    printWriter.print(drivers[countSave].trip.Vehicle.get(c)+",");

                }
                printWriter.printf("\n");
                countSave++;
            }
            printWriter.close();
            if(Flag==1) {
                ticket.save();
                Flag=0;
            }
        }catch(Exception e){
            System.out.println(e.getMessage()+" Error saving to Driver's file");
        }
    }
    public static void   DeleteAfterTripDeletion(String s){
        int count=0;
        while(count<i)
        {    for(int c=0;c<drivers[count].j;c++){
            String a= drivers[count].trip.Description.get(c)+"-"+drivers[count].trip.Type.get(c)+"-"+drivers[count].trip.NumberOfStops.get(c)+"-"+drivers[count].trip.Vehicle.get(c);    
		   if(a.equals(s)){
                    drivers[count].trip.Description.remove(c+1);
                    drivers[count].trip.Type.remove(c+1);
                    drivers[count].trip.NumberOfStops.remove(c+1);
                    drivers[count].trip.Vehicle.remove(c+1);
                    System.out.println("Ediny");
                    drivers[count].j--;

                }
            }
            count++;
        }
    }


    public String getDriverDescription(int k){return drivers[validDriver].trip.Description.get(k);}
    public String getDriverType(int k){
        return drivers[validDriver].trip.Type.get(k);
    }
    public Integer getDriverStops(int k){
        return drivers[validDriver].trip.NumberOfStops.get(k);
    }
    public String getDriverVehicle(int k){
        return drivers[validDriver].trip.Vehicle.get(k);
    }


    public int getValidDriverJ(){
        return drivers[validDriver].j;
    }
    public ArrayList<String> getWords(){

        return drivers[validDriver].trip.Description;
    }

    public static int getI() {
        return i;
    }
}
