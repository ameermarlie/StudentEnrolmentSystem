/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CombinedGui;

/**
 *
 * @author Ameer Marlie 222124474
 */
import domain.Course;
import domain.Enrolment;
import domain.Student;
import domain.Users;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Gui3 extends JFrame implements ActionListener{
    private JFrame log, adminPage, studPage;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static Socket server;
    private static java.util.List list;
    private static Object object;
    
    
    
    JPanel LnorthP=new JPanel(),LsouthP=new JPanel(),LwestP=new JPanel(),LeastP=new JPanel(),
            LcenterP=new JPanel(),LnorthInner=new JPanel(),LsouthInner=new JPanel(),
            LwestInner=new JPanel(),LeastInner=new JPanel(),LcenterInner=new JPanel();
    JLabel LlblUn=new JLabel("Username: "),LlblPass=new JLabel("Password: "),LlblTitle=new JLabel("Login Page");  
    JTextField LtxtUn=new JTextField(),LtxtPass=new JTextField();       
    JButton LbtnLogin=new JButton("Login");       
    Font Lfont=new Font("Arial",Font.BOLD,10),Lfont2=new Font("Arial",Font.BOLD,25);
    ImageIcon Licon;
    
    //admin page
    JPanel northP=new JPanel(),southP=new JPanel(),westP=new JPanel(),eastP=new JPanel(),
            centerP=new JPanel(),northInner=new JPanel(),southInner=new JPanel(),
            westInner=new JPanel(),eastInner=new JPanel(),centerInner=new JPanel();
    JPanel innerWestN=new JPanel(),innerWestS=new JPanel(),innerWestC=new JPanel(),innerWestW=new JPanel(),innerWestE=new JPanel();
    JPanel innerEastN=new JPanel(),innerEastS=new JPanel(),innerEastC=new JPanel(),innerEastW=new JPanel(),innerEastE=new JPanel();
    JPanel innerSouthN=new JPanel(),innerSouthS=new JPanel();

    JLabel lblName=new JLabel("Name: "),lblStudNo=new JLabel("Student Number: "),lblTitle=new JLabel("Add Student");
    JLabel lblCourseCode=new JLabel("Course Code: "),lblCourseTitle=new JLabel("Course title: "),lblTitle2=new JLabel("Add Course");
    JLabel lblTitle3=new JLabel("Student Enrolment Data: "),lblTitle4=new JLabel("Course Enrolment Results: ");
    JTextField txtName=new JTextField(),txtStudNo=new JTextField(),txtCourseCode=new JTextField(),txtCourseTitle=new JTextField();
    JTextArea txtStudEnrol=new JTextArea(10,10),txtCourseEnrol=new JTextArea(10,10);
    JButton btnAddStudent=new JButton("Add Student"),btnAddCourse=new JButton("Add Course"),btnExit=new JButton("Exit");       
    JButton btnStud=new JButton("Search Student"),btnCourse=new JButton("Search Enrolment");
    JButton btnDummy=new JButton("Search Student"),btnDummy2=new JButton("Search Enrolment"),btnDummy3=new JButton("Search Enrolment");
    Font font=new Font("Arial",Font.BOLD,12),font2=new Font("Arial",Font.BOLD,25);
    
    //student page
    JPanel SnorthP=new JPanel(),SsouthP=new JPanel(),SwestP=new JPanel(),SeastP=new JPanel(),
            ScenterP=new JPanel(),SnorthInner=new JPanel(),SsouthInner=new JPanel();
    
    JPanel SnorthInnerN=new JPanel(),SnorthInnerC=new JPanel(),SnorthInnerS=new JPanel(),SnorthInnerW=new JPanel(),SnorthInnerE=new JPanel();
    JPanel SsouthInnerN=new JPanel(),SsouthInnerC=new JPanel(),SsouthInnerS=new JPanel();
    
    JLabel SlblStudNo=new JLabel("Student Number: "),SlblCourse=new JLabel("Select Course: "),SlblTitle=new JLabel("Student Enrolment");  
    JTextField StxtStudNo=new JTextField(),StxtCourse=new JTextField();       
    JButton SbtnEnrol=new JButton("Enrol For Course"),SSbtnExit=new JButton("Exit");       
    Font Sfont=new Font("Arial",Font.BOLD,10),Sfont2=new Font("Arial",Font.BOLD,25);
    ImageIcon Sicon;
    JComboBox cmbCourse;
    
    public Gui3(){
        try{
                server=new Socket("localhost",12345);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            try{
                out= new ObjectOutputStream(server.getOutputStream());
                out.flush();
                in= new ObjectInputStream(server.getInputStream());
            }
            catch(IOException e){
                System.out.println("Exception error in StudentRecordClient() method: "+e.getMessage());
            }
        
        loginGui();
    }
    
    public void loginGui(){
        
        LlblTitle.setFont(font2);
        log = new JFrame();
        log.setTitle("Login");
        log.setSize(800,800);
        log.setVisible(true);
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setLayout(new BorderLayout());
        log.getContentPane().setBackground(new Color(57, 122, 125));
        
        log.add(LnorthP,BorderLayout.NORTH);
        log.add(LsouthP,BorderLayout.SOUTH);
        log.add(LeastP,BorderLayout.EAST);
        log.add(LwestP,BorderLayout.WEST);
        log.add(LcenterP,BorderLayout.CENTER);
        
        LnorthP.setPreferredSize(new Dimension(100,200));//left is width, right is height
        LsouthP.setPreferredSize(new Dimension(100,200));
        LwestP.setPreferredSize(new Dimension(200,100));
        LeastP.setPreferredSize(new Dimension(200,100));
        LcenterP.setPreferredSize(new Dimension(400,400));
        
        LnorthP.setBackground(new Color(32, 75, 92));
        LsouthP.setBackground(new Color(32, 75, 92));
        LwestP.setBackground(new Color(32, 75, 92));
        LeastP.setBackground(new Color(32, 75, 92));
        LcenterP.setBackground(new Color(32, 75, 92));        
        
        LcenterP.setLayout(new BorderLayout());
        
        LcenterP.add(LnorthInner,BorderLayout.NORTH);
        LcenterP.add(LsouthInner,BorderLayout.SOUTH);
        LcenterP.add(LwestInner,BorderLayout.WEST);
        LcenterP.add(LeastInner,BorderLayout.EAST);
        LcenterP.add(LcenterInner,BorderLayout.CENTER);
        
        LnorthInner.setBackground(Color.white);
        LsouthInner.setBackground(Color.white);
        LwestInner.setBackground(Color.white);
        LeastInner.setBackground(Color.white);
        LcenterInner.setBackground(Color.white); 
        
        LnorthInner.setPreferredSize(new Dimension(100,100));//left is width, right is height
        LsouthInner.setPreferredSize(new Dimension(100,100));
//        westInner.setPreferredSize(new Dimension(200,100));
//        eastInner.setPreferredSize(new Dimension(200,100));
        LcenterInner.setPreferredSize(new Dimension(400,200));
        
        LnorthInner.add(LlblTitle);
        LsouthInner.add(LbtnLogin);
        
        LcenterInner.setLayout(new GridLayout(2,2));
        LcenterInner.add(LlblUn);
        LcenterInner.add(LtxtUn);
        LcenterInner.add(LlblPass);
        LcenterInner.add(LtxtPass);
        
        LbtnLogin.addActionListener(this);

        
    }

    public void adminGui(){
        
        adminPage = new JFrame();
        
        lblTitle.setFont(font2);
        lblTitle2.setFont(font2);
        lblTitle3.setFont(font);
        lblTitle4.setFont(font);
        
        btnExit.setBackground(Color.red);
        btnExit.setForeground(Color.white);
        btnExit.setSize(new Dimension(50,20));
        
        btnDummy.setVisible(false);
        btnDummy2.setVisible(false);
        btnDummy3.setVisible(false);
        
        adminPage.setTitle("Admin Ammendment");
        adminPage.setLocationRelativeTo(null);
        adminPage.setSize(1200,1000);
        adminPage.setVisible(true);
        adminPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPage.setLayout(new BorderLayout());
        adminPage.getContentPane().setBackground(new Color(57, 122, 125));
        
        adminPage.add(northP,BorderLayout.NORTH);
        adminPage.add(southP,BorderLayout.SOUTH);
        adminPage.add(eastP,BorderLayout.EAST);
        adminPage.add(westP,BorderLayout.WEST);
        adminPage.add(centerP,BorderLayout.CENTER);
        
        northP.setPreferredSize(new Dimension(100,100));//left is width, right is height
        southP.setPreferredSize(new Dimension(100,100));
        westP.setPreferredSize(new Dimension(100,100));
        eastP.setPreferredSize(new Dimension(100,100));
        centerP.setPreferredSize(new Dimension(600,600));
        
        northP.setBackground(new Color(32, 75, 92));
        southP.setBackground(new Color(32, 75, 92));
        westP.setBackground(new Color(32, 75, 92));
        eastP.setBackground(new Color(32, 75, 92));
        centerP.setBackground(new Color(32, 75, 92));        
        
        centerP.setLayout(new BorderLayout());
        
        centerP.add(northInner,BorderLayout.NORTH);
        centerP.add(southInner,BorderLayout.SOUTH);
        centerP.add(westInner,BorderLayout.WEST);
        centerP.add(eastInner,BorderLayout.EAST);
        centerP.add(centerInner,BorderLayout.CENTER);
        
        northInner.setBackground(Color.white);
        southInner.setBackground(Color.white);
        westInner.setBackground(Color.white);
        eastInner.setBackground(Color.white);
        centerInner.setBackground(Color.white); 
        
        innerWestN.setBackground(Color.white);
        innerWestS.setBackground(Color.white);
        innerWestC.setBackground(Color.white);
        innerWestE.setBackground(Color.white);
        innerWestW.setBackground(Color.white);
        
        innerEastN.setBackground(Color.white);
        innerEastS.setBackground(Color.white);
        innerEastC.setBackground(Color.white);
        innerEastE.setBackground(Color.white);
        innerEastW.setBackground(new Color(32, 75, 92));
        
        
        westInner.setPreferredSize(new Dimension(500,300));
        eastInner.setPreferredSize(new Dimension(500,300));

        southInner.setPreferredSize(new Dimension(800,500));
        innerSouthN.setPreferredSize(new Dimension(800,100));
        innerSouthS.setPreferredSize(new Dimension(800,400));
        
        innerSouthN.setLayout(new GridLayout(1,4));
        innerSouthN.add(btnDummy2);
        innerSouthN.add(lblTitle3);
        innerSouthN.add(btnDummy3);
        innerSouthN.add(lblTitle4);
        innerSouthN.add(btnDummy);
        
        innerSouthS.setLayout(new GridLayout(1,2));
        innerSouthS.add(txtStudEnrol);
        innerSouthS.add(txtCourseEnrol);
        
        
        southInner.setLayout(new BorderLayout());
        southInner.add(innerSouthN,BorderLayout.NORTH);
        southInner.add(innerSouthS,BorderLayout.SOUTH);
    
        westInner.setLayout(new BorderLayout());
        westInner.add(innerWestN,BorderLayout.NORTH);
        westInner.add(innerWestS,BorderLayout.SOUTH);
        westInner.add(innerWestC,BorderLayout.CENTER);
        westInner.add(innerWestW,BorderLayout.WEST);
        westInner.add(innerWestE,BorderLayout.EAST);
        
        eastInner.setLayout(new BorderLayout());
        eastInner.add(innerEastN,BorderLayout.NORTH);
        eastInner.add(innerEastS,BorderLayout.SOUTH);
        eastInner.add(innerEastC,BorderLayout.CENTER);
        eastInner.add(innerEastW,BorderLayout.WEST);
        eastInner.add(innerEastE,BorderLayout.WEST);
        
        innerWestN.setPreferredSize(new Dimension(500,100));
        innerWestS.setPreferredSize(new Dimension(500,100));
        innerWestC.setPreferredSize(new Dimension(300,200));
        innerWestW.setPreferredSize(new Dimension(100,200));
        innerWestE.setPreferredSize(new Dimension(100,200));
        
        
        innerEastN.setPreferredSize(new Dimension(500,100));
        innerEastS.setPreferredSize(new Dimension(500,100));
        innerEastC.setPreferredSize(new Dimension(300,200));
        innerEastW.setPreferredSize(new Dimension(100,200));
        innerEastE.setPreferredSize(new Dimension(100,200));

        innerWestN.add(lblTitle);
        
        innerWestC.setLayout(new GridLayout(3,3));

        innerWestC.add(lblStudNo);
        innerWestC.add(txtStudNo);
        innerWestC.add(lblName);
        innerWestC.add(txtName);        
        
        innerWestS.add(btnAddStudent);
        innerWestS.add(btnStud);
        
        innerEastN.add(lblTitle2);
        
        innerEastC.setLayout(new GridLayout(3,3));
        innerEastC.add(lblCourseCode);
        innerEastC.add(txtCourseCode);
        innerEastC.add(lblCourseTitle);
        innerEastC.add(txtCourseTitle);
        innerEastS.add(btnExit);
        innerEastS.add(btnAddCourse);
        innerEastS.add(btnCourse);

        
           
        btnAddCourse.addActionListener(this);
        btnAddStudent.addActionListener(this);
        btnStud.addActionListener(this);
        btnCourse.addActionListener(this);
        btnExit.addActionListener(this);

       
    }
    
    public void StudentGui(){
        
        studPage = new JFrame();
        
        ArrayList<String> list = getCourseList();
    
        String[] array=new String[list.size()];
        
        for(int i=0;i<array.length;i++){
            array[i]=list.get(i);        
        }
        
        cmbCourse=new JComboBox(array);
        
        lblTitle.setFont(font2);
        
        btnExit.setBackground(Color.red);
        btnExit.setForeground(Color.white);
        btnExit.setSize(new Dimension(50,20));
        
        studPage.setTitle("Student Enrolment");
        studPage.setSize(800,800);
        studPage.setVisible(true);
        studPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studPage.setLayout(new BorderLayout());
        studPage.getContentPane().setBackground(new Color(57, 122, 125));
        
        studPage.add(SnorthP,BorderLayout.NORTH);
        studPage.add(SsouthP,BorderLayout.SOUTH);
        studPage.add(SeastP,BorderLayout.EAST);
        studPage.add(SwestP,BorderLayout.WEST);
        studPage.add(ScenterP,BorderLayout.CENTER);
        
        SnorthP.setPreferredSize(new Dimension(100,100));//left is width, right is height
        SsouthP.setPreferredSize(new Dimension(100,100));
        SwestP.setPreferredSize(new Dimension(100,100));
        SeastP.setPreferredSize(new Dimension(100,100));
        ScenterP.setPreferredSize(new Dimension(600,600));
        
        SnorthP.setBackground(new Color(32, 75, 92));
        SsouthP.setBackground(new Color(32, 75, 92));
        SwestP.setBackground(new Color(32, 75, 92));
        SeastP.setBackground(new Color(32, 75, 92));
        ScenterP.setBackground(new Color(32, 75, 92));        
        
        ScenterP.setLayout(new BorderLayout());
        
        ScenterP.add(SnorthInner,BorderLayout.NORTH);
        ScenterP.add(SsouthInner,BorderLayout.SOUTH);
        
        SnorthInner.setBackground(Color.white);
        SsouthInner.setBackground(Color.white);
        
        
        SnorthInner.setPreferredSize(new Dimension(600,150));//left is width, right is height
        SsouthInner.setPreferredSize(new Dimension(600,450));
        
        SnorthInner.setLayout(new BorderLayout());
        SnorthInner.add(SnorthInnerN,BorderLayout.NORTH);
        SnorthInner.add(SnorthInnerC,BorderLayout.CENTER);
        SnorthInner.add(SnorthInnerS,BorderLayout.SOUTH);
        SnorthInner.add(SnorthInnerE,BorderLayout.EAST);
        SnorthInner.add(SnorthInnerW,BorderLayout.WEST);        

        SsouthInner.setLayout(new BorderLayout());
        SsouthInner.add(SsouthInnerN,BorderLayout.NORTH);
        SsouthInner.add(SsouthInnerC,BorderLayout.CENTER);
        SsouthInner.add(SsouthInnerS,BorderLayout.SOUTH);
      
        
        SnorthInnerN.setPreferredSize(new Dimension(600,50));
        SnorthInnerC.setPreferredSize(new Dimension(600,50));
        SnorthInnerS.setPreferredSize(new Dimension(600,50));
        SnorthInnerE.setPreferredSize(new Dimension(50,600));
        SnorthInnerW.setPreferredSize(new Dimension(50,600));
        
        SsouthInnerN.setPreferredSize(new Dimension(600,50));
        SsouthInnerS.setPreferredSize(new Dimension(600,400));
        
        SnorthInnerN.setBackground(Color.white);
        SnorthInnerC.setBackground(Color.white);
        SnorthInnerS.setBackground(Color.white);
        SnorthInnerE.setBackground(Color.white);
        SnorthInnerW.setBackground(Color.white);
        
        SsouthInnerN.setBackground(Color.white);
        SsouthInnerS.setBackground(Color.white);        
        
        SnorthInnerC.setLayout(new GridLayout(2,2));
        SnorthInnerC.add(SlblStudNo);
        SnorthInnerC.add(StxtStudNo);
        SnorthInnerC.add(SlblCourse);
        SnorthInnerC.add(cmbCourse);
        
        SnorthInnerN.add(SlblTitle);
        SnorthInnerS.add(SbtnEnrol);
        SnorthInnerS.add(btnExit);
        
        
        SbtnEnrol.addActionListener(this);
        btnExit.addActionListener(this);
    }
    
    public void resetAdminForm() {
        txtName.setText("");
        txtStudNo.setText("");
        txtCourseCode.setText("");
        txtCourseTitle.setText("");
    }
    
    public void resetStudentForm() {
        StxtStudNo.setText("");
        cmbCourse.setSelectedIndex(0);
        StxtStudNo.requestFocus();
    }    

    public void enrolStudent() {
        try{
            Enrolment enrol=new Enrolment(StxtStudNo.getText().toLowerCase(),cmbCourse.getSelectedItem().toString());
            System.out.println(enrol);
            object=enrol;
            out.writeObject(object);
            out.flush();
        }
        catch(IOException e){
            System.out.println("Exception error in addCourseRecord() method: "+e.getMessage());
        }
        resetStudentForm();
    }//end addStudentRecord()
    
// In this method, construct a Student object that is initialized with the values entered by the user on the gui.   
// Send the object to the server.
// Clear the textfields and place the cursor in the name textfield    
    public void addStudentRecord() {

        
        try{
            Student stud=new Student(txtName.getText().toLowerCase(),txtStudNo.getText().toLowerCase());
            object=stud;
            out.writeObject(object);
            out.flush();
        }
        catch(IOException e){
            System.out.println("Exception error in addStudentRecord() method: "+e.getMessage());
        }
        resetAdminForm();
    }//end addStudentRecord()
    public void addCourseRecord() {

        
        try{
            Course course=new Course(txtCourseCode.getText().toLowerCase(),txtCourseTitle.getText().toLowerCase());
            object=course;
            out.writeObject(object);
            out.flush();
        }
        catch(IOException e){
            System.out.println("Exception error in addCourseRecord() method: "+e.getMessage());
        }
        resetAdminForm();
    }//end addStudentRecord() 
    
    public void displayStudentRecords(ArrayList studentList) {
        
//        txtStudEnrol.setText("");
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Record not found.");
        } else {
            for (int i = 0; i < studentList.size(); i++) {
                Enrolment enrol = (Enrolment) studentList.get(i);
                {
                    txtStudEnrol.append(enrol.toString() + "\n");
                    
                }
            }
        }
    }
    public void displayCourseStud(ArrayList studentList) {
        
//        txtStudEnrol.setText("");
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Record not found.");
        } else {
            for (int i = 0; i < studentList.size(); i++) {
                Enrolment enrol = (Enrolment) studentList.get(i);
                {
                    txtCourseEnrol.append(enrol.toString() + "\n");
                    
                }
            }
        }
    }
           
     public void searchStudents(String search){
    
        try{
        out.writeObject("searchStudent");
        out.flush();
        out.writeObject(search.toLowerCase());
        out.flush();
        
        ArrayList<Enrolment> searchStudResults = (ArrayList<Enrolment>) in.readObject();
        displayStudentRecords(searchStudResults);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }   
    
     }
     public void searchCourseStud(String searchCourse){
    
        try{
        out.writeObject("searchCourse");
        out.flush();
        out.writeObject(searchCourse.toLowerCase());
        out.flush();
        
        ArrayList<Enrolment> searchCourseResults = (ArrayList<Enrolment>) in.readObject();
        displayCourseStud(searchCourseResults);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }   
    
     }
     
     public ArrayList<String> getCourseList(){
        
        try {
            out.writeObject("comboList");
            out.flush();
            
            ArrayList<String> comboList = (ArrayList<String>) in.readObject();
            
            return comboList;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;   
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==LbtnLogin){
            if(LtxtUn.getText().isEmpty()||LtxtPass.getText().isEmpty()){
                
                JOptionPane.showMessageDialog(null, "Please fill all text fields");
                LtxtUn.setText("");
                LtxtPass.setText("");
            }
            
            else {
                String un=LtxtUn.getText();
                String pw=LtxtPass.getText();
                boolean verify = false;
                boolean admin = false;

                //Users us=new Users(un,pw,true);

                try{
                    Users us=new Users(un,pw,true);
                    object=us;
                    out.writeObject(object);
                    out.flush();
                    verify = (boolean) in.readObject();
                    admin = (boolean) in.readObject();

                }
                catch(IOException ioe){
                    System.out.println("Exception error in addStudentRecord() method: "+ioe.getMessage());
                    ioe.printStackTrace();
                }catch(ClassNotFoundException cnfe){
                    System.out.println("Exception error in addStudentRecord() method: "+cnfe.getMessage());
                    cnfe.printStackTrace();
                }
                //boolean verify=dao.Checker(us);
                //boolean admin=dao.adminCheck(us);
                        if(verify==true){
                            JOptionPane.showMessageDialog(null, "Login Successful");
                            if(admin==true){
                                //closeConnection();
                                log.dispose();
                                adminGui();

                            }
                            else{
                                //closeConnection();
                                log.dispose();
                                StudentGui();
                            }

                        }
                        else{
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                        }
                 
            }
            
        }if(e.getSource()==btnAddStudent){            
            addStudentRecord();
        }
        else if(e.getSource()==btnAddCourse){
            addCourseRecord();
        }
        else if(e.getSource()==btnCourse){
            txtCourseEnrol.setText("");
            String search = txtCourseCode.getText();
            searchCourseStud(search.toLowerCase());
        }        
        else if(e.getSource()==btnStud){
            txtStudEnrol.setText("");
            String search = txtStudNo.getText();
            searchStudents(search.toLowerCase());
        }if(e.getSource()==SbtnEnrol){            
            enrolStudent();
        }
        else if(e.getSource()==btnExit){
            closeConnection();
            System.exit(0);
        }
      
    }
    
    public void closeConnection() {
        try{
        String exit= "exit";
        out.writeObject(exit);
        out.close();
        in.close();
        server.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//end closeConnection()
        
    public static void main(String[] args){
        Gui3 gui = new Gui3();
    }
    
}
