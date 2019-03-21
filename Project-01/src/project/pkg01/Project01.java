package project.pkg01;

import java.sql.*;
import java.util.Scanner;

public class Project01 {
    
    public static void main(String[] args) {
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
       
        Scanner objUser = new Scanner(System.in); //Create a scanner object called objUser
        
        boolean WrongChoice = false; //User Select wrong option
        boolean Continuation = true; //User wants to continue the program
        String decision = null; //User select whether close the program or not
        
        System.out.println("|||||||||||||||WELCOME TO STUDENT MANAGEMENT SYSTEM|||||||||||||||");

        try{
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagementsystem","root","");//open a connection
            stmt = conn.createStatement();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
         while(Continuation == true){
            
           if(WrongChoice == false){
                
                System.out.println("\nWhich operation do you want to do?\n\t1-Add new Student\n\t2-Get all Student informations\n\t3-Get one Student information\n");
                int UserChoice = objUser.nextInt(); //Get the input from the user 

                switch (UserChoice) {
                    case 1:
                        System.out.print("\nEnter Student ID        : ");
                        String id = objUser.next();
                        System.out.print("Enter Student name      : ");
                        String name = objUser.next();
                        System.out.print("Enter Student Age       : ");
                        String age = objUser.next();
                        System.out.print("Enter Student address   : ");
                        String address = objUser.next();
                        
                        try{
                            
                            String insert = "INSERT INTO student(ID, NAME, AGE, ADDRESS) VALUES ('" +id+ "','" +name+ "','" +age+ "','" +address+ "')";
                            stmt.executeUpdate(insert);
                            
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try{
                            
                            String all = "SELECT ID, NAME, AGE, ADDRESS FROM `student`";
                            rs = stmt.executeQuery(all);
                            
                            while(rs.next()){
                                //Retrieve by column name
                                id  = rs.getString("ID");
                                name = rs.getString("NAME");
                                age = rs.getString("AGE");
                                address = rs.getString("ADDRESS");

                                //Display values
                                System.out.println("\nStudent ID        : " + id);
                                System.out.println("Student name      : " + name);
                                System.out.println("Student age       : " + age);
                                System.out.println("Student address   : " + address);
                                System.out.println("\n-----------------------------------------------\n");
                            }
                            
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        System.out.print("\nEnter Student ID        : ");
                        id = objUser.next();
                        
                        try{
                            
                            String personal = "SELECT ID, NAME, AGE, ADDRESS FROM `student`";
                            rs = stmt.executeQuery(personal);

                            while(rs.next()){
                                if(id.equals(rs.getString("ID"))){
                                   
                                   name = rs.getString("NAME");
                                   age = rs.getString("AGE");
                                   address = rs.getString("ADDRESS");

                                       //Display values
                                   System.out.println("\n-----------------------------------------------");
                                   System.out.println("\nStudent ID        : " + id);
                                   System.out.println("Student name      : " + name);
                                   System.out.println("Student age       : " + age);
                                   System.out.println("Student address   : " + address);   
                                }
                            }                 
                            
                        }catch(Exception e){
                            e.printStackTrace();
                        }   
                        break;
                    default:
                        System.out.println("Sorry your choice of action is incorrect. Please try again!");
                        WrongChoice = true;
                        break;
                }
           }  
            
            System.out.println("\nDo You want to continue? y-yes / n-No");
            decision = objUser.next();
            
            if("y".equals(decision)){
                Continuation = true;
            }else if("n".equals(decision)){
                Continuation = false;
            }
       }  
    }
}
