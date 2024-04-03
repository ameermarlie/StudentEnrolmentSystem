/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBConnection;
import domain.Course;
import domain.Enrolment;
import domain.Student;
import domain.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Imaad Abrahams 222585358
 */
public class ServerDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public ServerDAO(){
        try{
        this.con=DBConnection.derbyconnection();}
        catch(SQLException e){
        JOptionPane.showMessageDialog(null,"Warning! in UserDAO"+JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public Course addCourse(Course course){
        int ok;
        String insertSql="INSERT INTO COURSES VALUES(?, ?)";
        try{
            pstmt=this.con.prepareStatement(insertSql);
            pstmt.setString(1, course.getCourseCode());
            pstmt.setString(2, course.getTitle());
            
            ok=pstmt.executeUpdate();
            if(ok>0){
            return course;
            }
            else{
            return null;}
        }
        catch(SQLException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            try{
                if(pstmt!=null)
                   pstmt.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }
        
        }//end finally
        return null;  
    }//end addCourse Method
    
    public Student addStudent(Student student){
        int ok;
        String insertSql="INSERT INTO STUDENTS VALUES(?, ?)";
        try{
            pstmt=this.con.prepareStatement(insertSql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudentNumber());
            
            ok=pstmt.executeUpdate();
            if(ok>0){
            return student;
            }
            else{
            return null;}
        }
        catch(SQLException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            try{
                if(pstmt!=null)
                   pstmt.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }
        
        }//end finally
        return null;  
    }//end addStudent Method
    public ArrayList<Course> findCourseCode(String title){
        ArrayList<Course>courseList=new ArrayList<>();
        try{
        String getAllSql="SELECT*FROM COURSES WHERE title=?";
        pstmt=this.con.prepareStatement(getAllSql);
        pstmt.setString(1, title);
        ResultSet rs=pstmt.executeQuery();

        if(rs !=null){
        
            while(rs.next())
            {
                System.out.println("DB table record:"
                +rs.getString(1)+" "+rs.getString(2));
                courseList.add(new Course(rs.getString("course_code"),rs.getString("title")));
            }
            
            rs.close();
            
        }//end if
        }//end try
        catch(Exception exception){}
        finally{
            try{
                if(pstmt!=null)
                   pstmt.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }

            System.out.println("Course code parsed: "+courseList.get(0).getCourseCode());
        return courseList;
        }  
    }
    public Enrolment enrolStudent(Enrolment enrol){
        
        ArrayList<Course>courseList=findCourseCode(enrol.getCourseCode());
        String code=courseList.get(0).getCourseCode();
        int ok;
        
        String insertSql="INSERT INTO ENROLMENT VALUES(?, ?)";
        try{
            pstmt=this.con.prepareStatement(insertSql);
            pstmt.setString(1, enrol.getStudentNumber());
            pstmt.setString(2, code.toUpperCase());
            
            ok=pstmt.executeUpdate();
            if(ok>0){
            return enrol;
            }
            else{
            return null;}
        }
        catch(SQLException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            try{
                if(pstmt!=null)
                   pstmt.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }
        
        }//end finally
        return null;  
    }//end addStudent Method    
    public ArrayList<Enrolment> getStudents(String studNum){
    
        ArrayList<Enrolment>studentList=new ArrayList<>();
        try{
        String getAllSql="SELECT*FROM ENROLMENT WHERE student_number=?";
        pstmt=this.con.prepareStatement(getAllSql);
        pstmt.setString(1, studNum);
        ResultSet rs=pstmt.executeQuery();

        if(rs !=null){
        
            while(rs.next())
            {
                System.out.println("DB table record:"
                +rs.getString(1)+" "+rs.getString(2));
                studentList.add(new Enrolment(rs.getString("student_number"),rs.getString("course_code")));
            }
            
            rs.close();
            
        }//end if
        }//end try
        catch(Exception exception){}
        finally{
            try{
                if(pstmt!=null)
                   pstmt.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }
        
        return studentList;

        }
    }//end getCourses Method
    public ArrayList<Enrolment> getCourseStud(String courseCode){
        System.out.println("The requested course is: "+courseCode);
        ArrayList<Enrolment> courseList=new ArrayList<>();
        try{
        String getAllSql="SELECT*FROM ENROLMENT WHERE course_code=?";
        pstmt=this.con.prepareStatement(getAllSql);
        pstmt.setString(1, courseCode.toUpperCase());
        ResultSet rs=pstmt.executeQuery();

        if(rs !=null){
        
            while(rs.next())
            {
                System.out.println("DB table record:"
                +rs.getString(1)+" "+rs.getString(2));
                courseList.add(new Enrolment(rs.getString("student_number"),rs.getString("course_code")));
            }
            
            rs.close();
            
        }//end if
        }//end try
        catch(Exception exception){}
        finally{
            try{
                if(pstmt!=null)
                   pstmt.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }
        
        return courseList;

        }
    }//end getCourses Method
 
    public ArrayList<Course> getCourses(){
    
        ArrayList<Course>courseList=new ArrayList<>();
        try{
        String getAllSql="select*from COURSES";
        pstmt=this.con.prepareStatement(getAllSql);
        ResultSet rs=pstmt.executeQuery();

        if(rs !=null){
        
            while(rs.next())
            {
                System.out.println("DB table record:"
                +rs.getString(1)+" "+rs.getString(2));
                courseList.add(new Course(rs.getString("course_code"),rs.getString("title")));
            }
            
            rs.close();
            
        }//end if
        }//end try
        catch(Exception exception){}
        finally{
            try{
                if(pstmt!=null)
                   pstmt.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage(),"Warning",JOptionPane.ERROR_MESSAGE);
            }
        
        return courseList;

        }
    }//end getCourses Method
    public ArrayList<String> ComboList(){
        
        String listSql="SELECT*FROM COURSES";
        ArrayList<String> typeList=new ArrayList<>();
        
        try {
            pstmt=con.prepareCall(listSql);
            ResultSet rs=pstmt.executeQuery();
 
            if(rs!=null){
                while(rs.next()){
                    
                    String type=rs.getString("title");
                            
                    typeList.add(type);
                }//end while
                
            }//end if
            
        } catch (SQLException ex) {
            Logger.getLogger(ServerDAO.class.getName()).log(Level.SEVERE, null, ex);            
        }
//        finally{
//        try{
//            if(pstmt!=null){
//            pstmt.close();
//            }
//        }catch(SQLException e){
//        JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
//        }
//         return typeList;
//        }
        
        //end finally
    return typeList;
    } 
    
    
        public boolean Checker(Users user){
        
        String checkerSQL="SELECT*FROM USERS where username=? AND password=?";
        
        try{
        pstmt=con.prepareStatement(checkerSQL);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        rs=pstmt.executeQuery();
        
        while(rs.next()){
        
            if(rs.getString(1).equals(user.getUsername()) && rs.getString(2).equals(user.getPassword())){
                
                return true;
            }
            
        }
        return false;
        
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Error in User checker"+JOptionPane.ERROR_MESSAGE); 
           return false;
        }
        finally{
        
            try{
                pstmt.close();
                rs.close();
            }
            catch(SQLException e){
            JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
            }
        }    
    }
    
    public boolean adminCheck(Users user){
        String checkerSQL="SELECT*FROM USERS where username=? AND password=?";
        
        try{
        pstmt=con.prepareStatement(checkerSQL);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        rs=pstmt.executeQuery();
        
        while(rs.next()){
        
            if(rs.getBoolean("admin")==true){
            
                return true;
            }
            
        }
        return false;
        
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Error in adminCheck"+JOptionPane.ERROR_MESSAGE); 
           return false;
        }
        finally{
        
            try{
                pstmt.close();
                rs.close();
            }
            catch(SQLException e){
            JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
            }
        }   
    }
    
    
}
