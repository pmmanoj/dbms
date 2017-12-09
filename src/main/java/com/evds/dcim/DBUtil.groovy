package com.evds.dcim

import groovy.sql.Sql

import java.sql.SQLException

/**
 * Created by sanjivsingh on 12/9/17.
 */
class DbUtil {

   // Connection Method
    static Sql createConnection() {

        try {
            def db = [
//                    dbURL     : 'jdbc:mysql://192.168.99.191:3306/LENDING',
                      dbURL     : 'jdbc:mysql://fnw-src-sqldb00.cx20lab1.local/LENDING',
                      dbUserName: 'datadba',
                      dbPassword: 'Evaya123$9',
//                      dbDriver:   'com.mysql.jdbc.Driver' // this is DEPRECATED
                      dbDriver  : 'com.mysql.cj.jdbc.Driver'
            ]

            def sql = Sql.newInstance(db.dbURL, db.dbUserName, db.dbPassword, db.dbDriver)

        } catch (ClassNotFoundException | SQLException ex) {
            println ('Error in Connection ' + ex)

            println('SQL Exception: ' + ex.getMessage() )
        } // catch
    }  // Sql method

} // DbUtil class
