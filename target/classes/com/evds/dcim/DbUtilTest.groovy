package com.evds.dcim

/**
 * Created by sanjivsingh on 12/9/17.
 */
class DbUtilTest {

    static void main(String...args) {

       // Test Connection to DB
       def sql =  DbUtil.createConnection()

        sql.query('SELECT count(*) FROM sl_2') {
           resultSet -> if (resultSet.next() ) { // you MUST advance the implicit CURSOR marker to next; else Exception
               print 'Row count is: ' + resultSet.getString(1)
           }
        }

    } // main

} // class
