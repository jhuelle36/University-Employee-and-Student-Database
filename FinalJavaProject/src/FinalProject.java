import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

import java.util.Comparator;
import java.util.Collections;


/*
 * Final Project 
 * Tran Pham
 * Jaden Huelle
 */

public class FinalProject {
    public static void main(String[] args) {
    	
    	//Declare Variables
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        String name, id, department, rank, status;
        double gpa;
        int credHours;
        int check;
        
        
        //Declare ArrayList
        List<Person> p = new ArrayList<>();

        //Introduction
        System.out.println("\t\t\tWelcome to my Personal Management Program\n\n");
        System.out.println("Choose one of the options:\n");
        
        do {
            printOptions();

            // loop for as long as invalid input is entered
            // break out of loop if a valid option is chosen
            while (true) {
            	
            	//Try will test to make sure integer input is given
                try {
                    option = scanner.nextInt();
                    scanner.nextLine(); // consumes the \n not taken in by nextInt
                    System.out.println("\n");
                    
                    //Leave loop if option is between 1 and 8
                    if (option >= 1 && option <= 8) {
                        break;
                    }
                    //Option is not between 1 and 8
                    else {
                    	System.out.println("Invalid entry- please try again\n");
                        
                    }
                } 
                //Catch handles output that is not int
                catch (InputMismatchException e) {
                    // clears invalid input from iscanner buffer
                	System.out.println("Invalid entry- please try again\n");
                    scanner.nextLine();
                }
                printOptions();
            }//End of while
            
            
            //Switch Handles menu options
            switch (option) {
            	
            
            
            	//Enter information for faculty
                case 1:
                	
                    System.out.println("Enter the faculty info: ");
                    
                    //Input for name
                    System.out.print("\tName of the faculty: ");
                    name = scanner.nextLine();
                    
                    //Input for ID, check if ID is valid
                    do {
                        System.out.print("\tID: ");
                        id = scanner.nextLine();
                    } while (!validateID(id, p));
                    
                    //Input for rank, check if rank is valid
                    do {
                    	//Input
                    	System.out.print("\tRank: ");
                    	rank = scanner.nextLine();
                    	//Invalid
                    	if(rank.compareToIgnoreCase("adjunct") != 0 && rank.compareToIgnoreCase("professor") != 0) {
                    		System.out.println("\t\t'" + rank + "' is invalid");
                    	}
                    }while (rank.compareToIgnoreCase("adjunct") != 0 && rank.compareToIgnoreCase("professor") != 0 );
                    
                    //Input for department, check if department is valid
                    do {
                    	//Input
                    	System.out.print("\tDepartment: ");
                    	department = scanner.nextLine();
                    	//Invalid
                    	if(department.compareToIgnoreCase("mathematics") != 0 && department.compareToIgnoreCase("engineering") != 0 && department.compareToIgnoreCase("english") != 0 ) {
                    		System.out.println("\t\t'" + department + "' is invalid");
                    	}
                    }while (  department.compareToIgnoreCase("mathematics") != 0 && department.compareToIgnoreCase("engineering") != 0 && department.compareToIgnoreCase("english") != 0  );
                    
                    //Create new Faculty object and add to ArrayList
                    Person newP = new Faculty(name, id, capitalize(department), capitalize(rank) );
                    p.add( newP );
                    
                    System.out.println("Faculty added!\n\n");
                    break;

                    
                    
                //Enter information for student
                case 2:
                	
                    System.out.println("Enter the student info: \n");
                    
                    //Input for name
                    System.out.print("\tName of Student: ");
                    name = scanner.nextLine();

                    //Input for Id, check if Id is valid
                    do {
                        System.out.print("\tID: ");
                        id = scanner.nextLine();
                    } while (!validateID(id, p));
                    
                    
                    int test = 1;
                    gpa = 1;
                    
                    //Input for GPA, check if GPA is valid
                    while(test == 1) {
                    	//Try checks if input is a double
                    	try {
                    		//Input
	                    	System.out.print("\tGPA: ");
	                    	gpa = scanner.nextDouble();
	                    	test = 0;
	                    }catch(Exception e) {
	                    	//Invalid
	                    	System.out.println("\t\t Enter a type double");
	                    	scanner.nextLine();
	                    }
            		}
                    
                    
                    test = 1;
                    credHours = 1;
                    
                    //Input for credit hours, check if valid
                    while(test == 1) {
                    	//Try checks if credit hours is an integer
                    	try {
                    		//Input
                    		System.out.print("\tCredit hours: ");
	                    	credHours = scanner.nextInt();
	                    	test = 0;
	                    }catch(Exception e) {
	                    	//Invalid
	                    	System.out.println("\t\t Enter a type integer");
	                    	scanner.nextLine();
	                    }
            		}
                     
                    //Creates a Student object and adds it to list
                    Person newStudent = new Student(name, id, gpa, credHours);
                    p.add( newStudent);

                    System.out.println("Student added!\n\n");
                    break;

                   
                    
                //Print out student invoice
                case 3:
                	
                	//Input for ID
                    System.out.print("\tEnter the student's id: ");
                    id = scanner.next();
                    
                    //printInfo prints info if student is found
                    check = printInfo(p, id);
                    
                    //If printInfo returns 0 student is not found
                    if(check == 0) System.out.println("\tNo student matched!\n\n");
                    
                    break;
                    
                    
                    
                //Print out faculty information
                case 4:
                	
                	//Input for faculty ID
                    System.out.print("\tEnter the Faculty's id: ");
                    id = scanner.next();
                    
                    //printInfo prints info if faculty is found
                    check = printInfo(p, id);
                    
                    //If printInfo returns 0 faculty is not found
                    if(check == 0) System.out.println("\tNo Faculty matched\n\n");
                    
                    break;
                    
                    
                    
                //Enter information for staff member
                case 5:
                	
                	//Input for staff name
                	System.out.print("\tName of the staff member: ");
                    name = scanner.nextLine();
                    
                    //Input for ID, check if it is valid
                    do {
                        System.out.print("\tID: ");
                        id = scanner.nextLine();
                    } while (!validateID(id, p));
                    
                    
                    //Input for department, check if department is valid
                    do {
                    	//Input
                    	System.out.print("\tDepartment: ");
                    	department = scanner.nextLine();
                    	//Invalid
                    	if(department.compareToIgnoreCase("mathematics") != 0 && department.compareToIgnoreCase("engineering") != 0 && department.compareToIgnoreCase("english") != 0 ) {
                    		System.out.println("\t\t'" + department + "' is invalid");
                    	}
                    }while (  department.compareToIgnoreCase("mathematics") != 0 && department.compareToIgnoreCase("engineering") != 0 && department.compareToIgnoreCase("english") != 0  );
                    
                    
                    //Input for status, check if status is valid
                    do {
                    	//Input
                    	System.out.print("\tStatus, Enter P for Part Time, or Enter F for Full Time:");
                        status = scanner.nextLine();
                        //Invalid
                    	if(status.compareToIgnoreCase("f") != 0 && status.compareToIgnoreCase("p") != 0) {
                    		System.out.println("\t\t'" + status + "' is invalid");
                    	}
                    }while (status.compareToIgnoreCase("f") != 0 && status.compareToIgnoreCase("p") != 0 );
                    
                    
                    //Create staff object and then add to list
                    Person newStaff = new Staff(name, id, capitalize(department), capitalize(status));
                    p.add(newStaff);
                 
                    System.out.println("\nStaff member added!\n\n");
                    
                    break;
                    
                    
                    
                //Print out staff information
                case 6:
                	
                	//Input for staff ID
                	System.out.print("\tEnter the Staff's id: ");
                    id = scanner.next();
                    
                    //printInfo prints info if faculty is found
                    check = printInfo(p, id);
                    
                    //If printInfo returns 0 staff does not exist
                    if(check == 0) System.out.println("\tNo Staff matched");
                    
                    break;
                    
                    
                    
                //Delete a person from list
                case 7:
                	
                	//Input for ID
                	System.out.print("\tEnter the Faculty's id: ");
                    id = scanner.next();
                    
                    //Loops through searching for ID
                    check = 0;
                    for ( Person e : p) {
                    	//If found remove and leave loop
                		if ( id.compareToIgnoreCase(e.getId()) == 0 ) {
                			p.remove(e);
                			check = 1;
                			break;
                		}
                    }
                    
                    //If check equals 0, person not found, otherwise they were deleted
                    if ( check == 0) System.out.println("Sorry no such person exists.\n\n");
                    else System.out.println("Person was deleted!\n\n");
                    
                    break;

            }//end of switch
            

        } while (option != 8); //End of main do while
        
        
        
        String option2;
        
        //Asks if user want to make a report and checks if valid input
        do {
        	//Input
        	System.out.print("\n\n\nWould you like to create a report? (Y/N): ");
        	option2 = scanner.next();
        	//Invalid
        	if(option2.compareToIgnoreCase("y") != 0 && option2.compareToIgnoreCase("n") != 0) System.out.println("Invalid Input- Please try again.");
        	
        }while(option2.compareToIgnoreCase("y") != 0 && option2.compareToIgnoreCase("n") != 0);
        
        
        //If they want report
        if (option2.compareToIgnoreCase("y") == 0) {
           
        	check = 0;
        	//Sort by name or GPA, check for valid input, loop until valid
            do {
	        	try {
	            	//Input
	            	System.out.print("Would you like to sort your students by descending gpa or name (1 for gpa, 2 for name): ");
	                option = scanner.nextInt();
	                scanner.nextLine(); // consumes the \n not taken in by nextInt
	                System.out.println("\n");
	                
	                //Invalid, not 1 or 2
	                if (option != 1 && option != 2) {
	                	System.out.println("Invalid entry- please try again\n");
	                	check = 1;
	                } 
	               
	            } 
	            //Invalid, not an integer
	            catch (InputMismatchException e) {
	            	System.out.println("Invalid entry- please try again\n");
	                scanner.nextLine();
	                check = 1;
	            }
            }while(check == 1);
            
         
            //Declare writer, set to null
            PrintWriter writer = null;
            
            //Try checks to make sure PrintWriter is created without exceptions
            try {
            	//Create file
            	writer = new PrintWriter("report.txt");
        	}catch( Exception e) {
        		System.out.println("File error");
        	}
            
            //Gets date
            long millis = System.currentTimeMillis();  
            java.sql.Date date = new java.sql.Date(millis);
            
            //Print date in file
            writer.println("\t\t\t" + date);
            writer.println("\t\t****************************\n\n");
            
            
            int num = 0;
            
            //Print faculty
            writer.println("\tFaculty Members");
            writer.println("\t----------------------");
            
            //Loop goes through list, finds faculty objects, and prints them in file
            for(Person e : p) {
    			if( e instanceof Faculty) {
    				writer.println("\t\t" + num + ". " + e.getName());
    				writer.println("\t\tID: " + e.getId());
    				writer.println("\t\t" + e.getRank() + ", " + e.getDepartment() );
    				num++;
    			}
    		}
            
            
            writer.println("\n\n");
            num = 0;
            
            //Print Staff
            writer.println("\tStaff Members");
            writer.println("\t----------------------");
            
            //Loop goes through list, finds staff objects, and prints them in file
            for(Person e : p) {
    			if( e instanceof Staff) {
    				writer.println("\t\t" + num + ". " + e.getName());
    				writer.println("\t\tID: " + e.getId());
    				writer.println("\t\t" + e.getDepartment() + ", " + e.getStatus() );
    				num++;
    			}
    		}
            
            
            writer.println("\n\n");
            
            
            //Creates new ArrayList of students to sort accordingly
            List<Student> studentList = new ArrayList<>();
            
            //Loop goes through list, finds student objects and adds them to list
            for (Person e : p) {
                if (e instanceof Student) {
                    studentList.add((Student) e);
                }
            }
            
            
            //Sort by GPA
            if( option == 1) {
            	studentList.sort(Comparator.comparingDouble(Student::getGpa).reversed());
            }
            
            //Sort by Name
            else {
            	Collections.sort(studentList, Comparator.comparing(Student::getName));

            }
            
            
            num = 0;
            
            //Print Students
            writer.println("\tStudents");
            writer.println("\t----------------------");
            
            //Loop goes through list, finds student objects, and prints them in file
            for(Person e : studentList) {
    			if( e instanceof Student) {
    				writer.println("\t\t" + num + ". " + e.getName());
    				writer.println("\t\tID: " + e.getId());
    				writer.println("\t\tGPA: " + e.getGpa() );
    				writer.println("\t\tCredit Hours: " + e.getCredHours() + "\n");
    				num++;
    			}
    		}
            
            //Close writer pointer, this is where buffer is cleared
            writer.close();
            
            
            System.out.println("Report created and saved to your hard drive!");
        }
        
        
        System.out.println("Goodbye!");
        
        //Close scanner
        scanner.close();
    }
    
    
    
    
    
    //Local method for printInfo, returns 1 if found and printed, 0 if not found
    private static int printInfo(List<Person> people, String id) {
    	
    	//Loop through, find ID, and print
    	for ( Person e : people) {
    		if ( id.compareToIgnoreCase(e.getId()) == 0 ) {
    			e.print();
    			return 1;
    		}
    	}
    	return 0;
    }
    
    
    //Local method for capitalizing(returns capitalized word): department, rank, and status
    private static String capitalize( String word ) {
    	
    	//Lower case entire word
    	word.toLowerCase();
    	
    	//Capitalize the first letter
        word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        
    	//Return word
    	return word;
    }

    
    //Local function that prints menu options
    private static void printOptions() {
    	
        System.out.println(" 1- Enter the information a faculty\n"
                + " 2- Enter the information of a student\n"
                + " 3- Print tuition invoice for a student\n"
                + " 4- Print faculty information\n"
                + " 5- Enter the information of a staff member\n"
                + " 6- Print the informatiion of a staff member\n"
                + " 7- Delete a person \n"
                + " 8 - Exit Program\\n");
        System.out.print("\tEnter your selection: ");
    }

    
    //Local function that checks if ID is in valid format, returns true if valid, false otherwise
    private static boolean validateID(String id, List<Person> people ) {
    	
        boolean valid = true;
        
        //Loop through to check for other matching IDS
        for ( Person e : people) {
    		if ( id.compareToIgnoreCase(e.getId()) == 0 ) {
    			System.out.println("Someone already has this ID number!");
    			valid = false;
    		}
    	}
        
        

        //Checks for the correct length
        if (id.length() == 6) {

            //Check the first 2 characters are letters
            for (int i = 0; i < 2; i++) {
                if (!Character.isLetter(id.charAt(i))) {
                    valid = false;
                    break;
                }
            }
            
            //Checks the rest are characters
            for (int i = 2; i < 6; i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    valid = false;
                    break;
                }
            }
        } 
        
        //If not right length, return false
        else {
            valid = false;
        }
        
        //If false print Invalid
        if (!valid) {
            System.out.println("\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit");
        }

        //Return if ID is valid or not
        return valid;
    }
}



//Abstract class Person that extends to Employee and Student
abstract class Person {
	
	//Private fields
    private String name;
    private String id;

    
    //Setters and Getters for name and ID
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //Person constructor assigns name and id
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }
    
    //Person constructor for no given information
    public Person() {
    	this.name = null;
    	this.id = null;
    }

    //Abstract method print()
    public abstract void print();
    
    //Getters required to print information that are overwritten by child classes: Rank, Department, Status, GPA, Credit Hours
    public String getRank() {
    	return null;
    }
    public String getDepartment() {
    	return null;
    }
    public String getStatus() {
    	return null;
    }
    public double getGpa() {
        return 0;
    }
    public int getCredHours() {
    	return 0;
    }
    
}



//Class Student extended from Person
class Student extends Person {
	
	//Private fields
    private double gpa;
    double adminFee = 52;
    double creditPrice = 236.45;
    double discount;
    private int credHours;

    //Getters and Setters for gpa and CredHours
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public int getCredHours() {
        return credHours;
    }
    public void setCredHours(int credHours) {
        this.credHours = credHours;
    }
    
    //Student constructor assigns values
    public Student(String name, String id, double gpa, int credHours) {
    	
    	//Super uses Person's constructor
        super(name, id);
        this.gpa = gpa;
        this.credHours = credHours;
    }
    
    //Student constructor for no given information
    public Student() {
    	
    	//Super calls Person's constructor
    	super();
    	this.gpa = -1;
    	this.credHours = -1;
    }

    //Method for calculating student invoice, returns the total payment
    public double tuitionInv() {
    	
    	//Formula
        double totalPayment = (this.credHours * creditPrice) + adminFee;

        //If student's gpa is greater than or equal to 3.85, 25% off total payment
        if (this.gpa >= 3.85) {
        	discount = totalPayment * 0.25;
            totalPayment *= 0.75;
        }
        //Otherwise no discount
        else discount = 0;
        
        //Return totalPayment
        return totalPayment;
    }

    //Overwrites abstract class print from Person class - prints fee invoice
    public void print() {
    	
    	System.out.println("Here is the tuition invoice for " + getName() + "\n\n");
    	System.out.println("\t---------------------------------------------------------------------------------");
    	System.out.println( "\n\n\t" + getName() + "\t\t" + getId() );
    	System.out.println( "\tCredit Hours: " + credHours + " ($236.45 / credit hour)");
    	System.out.println("\tFees: $" + adminFee);
    	System.out.printf("\tTotal payment (after discount): $%.2f\t\t($%.2f discount applied)\n", tuitionInv(), discount);
    	System.out.println("\t---------------------------------------------------------------------------------\n\n");

    }
}



//Abstract class Employee extended from Person
abstract class Employee extends Person {
	
	//Private fields
    private String department;

    //Getters and setters for department
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    
    //Employee constructor assigns values
    public Employee(String name, String id, String department) {
    	
    	//Super uses Person's constructor
        super(name, id);
        this.department = department;
    }
    
    //Employee constructor for no given information
    public Employee() {
    	
    	//Super uses Person's constructor
    	super();
    	this.department = null;
    }

    //Overwrites abstract class print from Person class - prints general info for employees
    public void print() {
        System.out.println("\t---------------------------------------------------------------------------");
        System.out.println("\t" + getName() + "\t\t" + getId());
        System.out.print("\t" + department + " Department, ");
        
    }

    
}



//Faculty class extends from Employee which extends from Person
class Faculty extends Employee {
	
	//Private fields
    private String rank;

    //Getters and setters for rank
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    
    //Constructor, assigns values
    public Faculty(String name, String id, String department, String rank) {
    	
    	//Super calls Employee constructor
        super(name, id, department);
        this.rank = rank;
    }
    
    //Faculty constructor for no given information
    public Faculty() {
    	
    	//Super calls Employee constructor
    	super();
    	this.rank = null;
    }
    
    //Overwrites abstract class print from Person class - Prints faculty info
    public void print() {
    	
    	//Calls Employees print()
        super.print();
        System.out.println(rank);
        System.out.println("\t---------------------------------------------------------------------------\n\n");

    }
}

//Staff class extends Employee which extends Person
class Staff extends Employee {
	
	//Private fields
    private String status;
    
    //Getters and setters for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    //Method that checks if input is 'f' or 'p' and returns the appropriate string for printing
    private String status( String stat ) {
    	if ( stat.compareToIgnoreCase("f") == 0) return "Full Time";
    	else return "Part Time";
    	
    }

    //Overwrites abstract class print from Person class - Prints staff info
    public void print() {
    	
    	//Super calls Employee print
        super.print();
        System.out.println(status(status));
        System.out.println("\t---------------------------------------------------------------------------\n\n");
    }

    //Staff constructor assigns values
    public Staff(String name, String id, String department, String status) {
    	
    	//Super calls Employee constructor
        super(name, id, department);
        this.status = status;
    }
    
    //Staff constructor assigns values
    public Staff() {
    	
    	//Super calls Employee constructor
    	super();
    	this.status = null;
    }
}



