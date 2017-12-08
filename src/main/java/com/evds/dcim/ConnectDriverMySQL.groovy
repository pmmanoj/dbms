package com.evds.dcim

/**
 * Created by sanjivsingh on 12/7/17.
 */

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

// we do NOT import com.mysql.cj.jdbc.*
//    else there will be problems


public class ConnectDriverMySQL {

   public static void main(String...args) {
     try {
        // newInstance() call is a work-around for some broken java implementations
         Class.forName('com.mysql.cj.jdbc.Driver').newInstance()
     } catch( Exception ex ) {
         println 'Error in obtaining REGISTERING MySQL Connector/J into DriverManager'
     }

   }  // main

} // class
