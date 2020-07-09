import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager implements Verification//load-validate-gui( buttons btghyar fel classes el tania
	{
	Tickets tickets=new Tickets();
	User user=new User();
	Driver driver=new Driver();
    FileWriter fileWriter ;
    PrintWriter printWriter;
    Scanner scanner=null;
    private String Email,Password,Name;
    int Age ;
     
    public void Load() { // load  el managers
    	user.load();
    	driver.load();
    	try {
    		scanner = new Scanner (new File ("C:\\Users\\M3MO\\IdeaProjects\\GoBus\\src\\ManagerFile.txt"));
    	
    	}
    	catch(Exception e){
System.out.println(e.getMessage()+"Error finding Manager's file");
    	}
    	try {
    		if(scanner.hasNext()) {
    			scanner.useDelimiter(",");
    			Email=scanner.next();
    			Password=scanner.next();
    			Name=scanner.next();
    			Age=Integer.parseInt(scanner.next());
    			
    		}
    	}
    	catch ( Exception er ) {
    		System.out.println(er.getMessage()+"Error loading Manager's file");
    	}
    }
    	public void DisplayManager()
    	{
    		System.out.println(Email);
    		System.out.println(Password);
    		System.out.println(Name);
    		System.out.println(Age);
    		
    	}
    
	@Override
	public  boolean validate(String email, String pass)
	{ Load();
		  if(Email.equals(email))
	        {
	            if(Password.equals(pass)) {
	               return true ;
	            }else
	                return false ;
	        }
	        else
	            return true ;
	    }
	}
	
	
	
