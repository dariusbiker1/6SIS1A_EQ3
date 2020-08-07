package Control;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eleazar
 */

    
import java.util.*;
import java.sql.*;
public class Conexion{

    public static Connection getConnection(){
        String url,user,password;
        url="jdbc:mysql:3306/localhost/estetica";
        user="root";
        password="Soybiker1";
                
                Connection con = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(url,user,password);
                    System.out.println("Si se conecto");
            
        } catch (Exception e) {
                    System.out.println("No se conecto");
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
        }
        return con;
    }
    

}
 

