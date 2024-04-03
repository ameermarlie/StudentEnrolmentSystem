/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.ServerDAO;
import domain.Course;
import domain.Enrolment;
import domain.Student;
import domain.Users;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nur Johnston 22240168
 */
public class ServerSide {
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static Object receivedObject;
    

//In the constructor listen for incoming client connections    
public ServerSide(){
    int port=12345;
        try{
            serverSocket=new ServerSocket(port);
            System.out.println("Server is Listening on port"+port);
            clientSocket=serverSocket.accept();            
            System.out.println("Client connected: "+clientSocket.getInetAddress().getHostAddress());
            
            getStreams();
        }
        catch(IOException e){
            e.printStackTrace();
        }
}//end constructor

//------------------------------------------------------------------------------    
    
//create the io streams
public void getStreams(){
    try{
        out= new ObjectOutputStream(clientSocket.getOutputStream());
        out.flush();
        in= new ObjectInputStream(clientSocket.getInputStream());
    }
    catch(IOException e){
        e.printStackTrace();
    }
}//end getStreams()

//------------------------------------------------------------------------------    
    
//Declare arraylist and handle the communication between server and client    
    public void processClient() {
        ServerDAO dao=new ServerDAO();
        
        
        while(true){
            try{
                receivedObject=in.readObject();
                if(receivedObject instanceof Student)
                    {       
                        Student newStud=(Student)receivedObject;
                        dao.addStudent(newStud);
                        System.out.println("Added new student to the record: "+newStud);
                    }
                else if(receivedObject instanceof Course)
                    {       
                        Course newCourse=(Course)receivedObject;
                        dao.addCourse(newCourse);
                        System.out.println("Added new Course to the record: "+newCourse);
                    }
                else if(receivedObject instanceof Enrolment)
                    {       
                        Enrolment newEnrolment=(Enrolment)receivedObject;
                        dao.enrolStudent(newEnrolment);
                        System.out.println("Student Enrolment added to the record: "+newEnrolment);
                    } 
                
                else if(receivedObject instanceof Users){
                    Users us=(Users)receivedObject;
                    boolean verify=dao.Checker(us);
                    out.writeObject(verify);
                    out.flush();
                    boolean admin = dao.adminCheck(us);
                    out.writeObject(admin);
                    out.flush();
                    
                    System.out.println("User checked: "+us);
                    
                }
                else if(((String)receivedObject).equals("comboList"))
                    {       
                        ArrayList<String> list=dao.ComboList();
                        out.writeObject(list);
                        out.flush();
                    }

                else if(receivedObject instanceof String && ((String) receivedObject).equals("searchCourse")){
                    try{
                    String search = (String) in.readObject();
                    ArrayList<Enrolment> searchResults=dao.getCourseStud(search);
                        out.writeObject(searchResults);
                        out.flush();
                        System.out.println("Search results for Course sent to client: " + searchResults);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }                 
                else if(receivedObject instanceof String && ((String) receivedObject).equals("searchStudent")){
                    try{
                    String search = (String) in.readObject();
                    ArrayList<Enrolment> searchResults=dao.getStudents(search);
                        out.writeObject(searchResults);
                        out.flush();
                        System.out.println("Search results for Student sent to client: " + searchResults);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
               
                else if(receivedObject instanceof String && ((String)receivedObject).equals("exit")){
                    closeConnection();
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            }
        }
    }//end processClient

//------------------------------------------------------------------------------    

//close all connections to the server and exit the application   
    private static void closeConnection() {
    try{
        out.writeObject("Exit");
        out.flush();
        out.close();
        in.close();
        clientSocket.close(); 
        serverSocket.close();
        System.out.println("The server connection has been closed");
        System.exit(0);
        }
    catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }//end closeConnection()

//------------------------------------------------------------------------------    

//execute the program and call all necessary methods
    public static void main(String[] args) {
        ServerSide server=new ServerSide();        
        server.processClient();
        
    }//end main

}//end class
