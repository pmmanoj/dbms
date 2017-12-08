package com.evds.dcim

// IMPORTS
import groovy.transform.Canonical
// import spock.lang.specification

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

import groovy.sql.Sql


// REFERENCE:
// URL: http://docs.groovy-lang.org/latest/html/gapi/groovy/sql/Sql.html

// @Cannoical
class ConnectMySQL {

    static void main(String...args) {

        println '*** Hello Marker Z777'

       // connect to DB
       try {
           def db = [ dbURL: 'jdbc:mysql://192.168.99.191:3306/LENDING',
                      dbUserName: 'datadba',
                      dbPassword: 'Evaya123$9',
//                      dbDriver:   'com.mysql.jdbc.Driver' // this is DEPRECATED
                      dbDriver:   'com.mysql.cj.jdbc.Driver'
                    ]

           def sql = Sql.newInstance(db.dbURL, db.dbUserName, db.dbPassword, db.dbDriver)

           def NOW_time = sql.execute '''SELECT now()'''
           println '*** Hello Marker Z888'

           sql.eachRow('SELECT Prospect_ID, Followers, Skills FROM sl_2 LIMIT 50' ) { row ->
               for (int i = 0; i <= 2; i ++) {
                   println row[i] // prints ONE row's i-th column

                   // TODO
                   // 1. Write the row into a file (opened via TRY / CATCH block )
                   // 2. Increment a ROW-WRITTEN-to-File Counter
                   // 3. On exit the FOR loop compare value of [2] to Select row count value obtained earlier
                   // 4. Upon match of values : make REST (PUT) Call to Node.JS front-end microService  (99.162)
                   // 5. Node.JS microService will write out a file-creation-audit-record as Metadata in MongoDB
                   // 6. Put a WAIT STATE (blocking)
                   // 7. After WAIT interval : make REST (GET) call to Node.JS same microService End point
                   // 8. Parse the JSON return object and match the result of GET call
                   // 9. Upon match; write final LOG message (to Local file - rotational LOG FILE )
                   // 9A.  -- write out SYSLOG message to external SYSLOG server: file name, record count
                   // 9B.  -- < any other >


               }
           } // sql

           // close connection to DB
           sql.close()

       } catch (ClassNotFoundException | SQLException ex) {
           println ('Error in Connection ' + ex)
           // handle any errors
           println('SQL Exception: ' + ex.getMessage() )
           println('SQL State:     ' + ex.getSQLState() )
           println('VendorError:   ' + ex.getErrorCode() )

       }

    } // main

} // class
