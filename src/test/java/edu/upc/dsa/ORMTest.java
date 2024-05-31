package edu.upc.dsa;

//import edu.upc.eetac.dsa.db.orm.model.Employee;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import org.junit.Test;

public class ORMTest {


    @Test
    public void registerTest() {
    /*    String url = "127.0.0.1";
        String user = "root";
        String password = "1234";
*/
        Session session = FactorySession.openSession(); //url, user, password);
       // Employee employee = new Employee("idXXX", "Pepito", 23223);
       // session.save(employee); // INSERT INTO employee (idXXX, pepito, ...)

        ///
        // POST ==> hi ha un nou usuari a la taula POU
        //
    }

    @Test
    public void loginTest() {
        String url = "127.0.0.1";
        String user = "root";
        String password = "1234";

        Session session = FactorySession.openSession(url, user, password);
       // Employee e = (Employee)session.get(Employee.class, "idXXX");
        //Employee e = (Employee)session.get(Employee.class, "email", "toni@upc.edu");



    }
}
