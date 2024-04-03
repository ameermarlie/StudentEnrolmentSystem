/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ameer
 */
public class DBConnection {
    
    public static Connection derbyconnection() throws SQLException{
        
        String DBURL="jdbc:derby://localhost:1527/StudentEnrolmentDB";
        String username="administrator";
        String password="password";
        Connection con= DriverManager.getConnection(DBURL,username,password);
        return con;
    }    
    
}
