package com.evds.dcim

// IMPORTS
// import spock.lang.specification
import groovy.sql.Sql
import java.sql.SQLException

// REFERENCE:
// URL: http://docs.groovy-lang.org/latest/html/gapi/groovy/sql/Sql.html

//TODO
/* 1. Re-factor-1 this class to ONLY provide you a SQL Connection object reference
 * 1.1  Next, Re-factor-2, use Abstract Factory pattern and implement SQL Connect Factory (with max Limit )
 *
 * 2. Re-factor to split off extract from DB and File output to a separate class
 * 2.1  Utilize this split-away class as a front-end PRODUCER to Kafka cluster
 *
 * 3. Re-factor to generate PROPERTY-OBJECT (JSON format) and update Metadata MongoDB store via Node.JS Service Endpoint
 * 3.1  See below reference to Metadata
 */


class ConnectMySQL {

    static void main(String...args) {

       // Class Variables
        String  field_0 = ''
        String  field_1 = ''
        String  field_2 = ''
        String  outRecordToFile = ''
        String  endOfLineMarker = '\n'  // we use this to terminate lines to output File

        Integer recordCount   = 0
        Integer Z9fillerCount = 0

       // Output file Text file with | delimiter
       File outFile = new File('/Users/sanjivsingh/Documents/EVAYA/Apps/projects/dataOut/seedList_1-backup2.csv')
       // Test
       println '\n' + "The file ${outFile.absolutePath} has ${outFile.length() } bytes"

           // Connection to DB
           def sql =  DbUtil.createConnection()

           // def sql = Sql.newInstance(db.dbURL, db.dbUserName, db.dbPassword, db.dbDriver)

           sql.eachRow('SELECT Prospect_ID, Followers, Skills FROM sl_2 LIMIT 500' ) { row ->

                   /* TEST Code: helps you understand how eachRow method works
                   for (int i = 0; i <= 2; i ++) { println row[i] } // prints ONE row's i-th column
                   println row[0] + '|' + row[1] + '|' + row[2]  // prints ONE row's i-th column
                   */
                   field_0 = row[0]; field_1 = row[1]; field_2 = row[2]


                   // Replace space or nulls AND maintain counts; helps with measuring Data Quality
                   // println 'Lengths are: ' + field_0.length() + '|' + field_1.length() + '|' + field_2.length()
                   if (field_0.length() == 0 || field_0 == null) {
                         field_0 = 'Z9Z9Z9'
                         Z9fillerCount++
                   }

                   if (field_1.length() == 0 || field_1 == null) {
                          field_1 = 'Z9Z9Z9'
                          Z9fillerCount++
                   }

                   if (field_2.length() == 0 || field_2 == null) {
                          field_2 = 'Z9Z9Z9'
                          Z9fillerCount++
                   }



                   // STRING together output record AND write to file
                   outRecordToFile =  field_0 + '|' + field_1 + '|' + field_2

                   if (outFile << ( outRecordToFile +  endOfLineMarker ) ) { recordCount++ } // increment record Count}


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


           } // sql

           // close connection to DB
           sql.close()
              println 'MSG: ----> Z9fillerCount = ' + Z9fillerCount
              println 'MSG: ----> Record Count output to file   = ' + recordCount
              println 'MSG: ----> SQL Connection to DB Closed '
              println 'MSG: ----> end of program'


    } // main

} // class
