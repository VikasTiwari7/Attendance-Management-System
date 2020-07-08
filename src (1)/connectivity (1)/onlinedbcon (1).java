/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectivity;

import java.sql.*;
public class onlinedbcon {
    static Connection con1;
    static public Connection getconnect(){
              try{
            Class.forName("com.mysql.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://scratchnest.com:3306/scratchn_Attendance_System","scratchn_root","root");


        }catch(Exception e){
            System.out.println(e);
        }return con1;




    }
}