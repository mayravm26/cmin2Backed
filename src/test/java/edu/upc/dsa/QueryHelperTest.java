package edu.upc.dsa;

import edu.upc.eetac.dsa.db.orm.model.*;

import edu.upc.dsa.orm.util.QueryHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class QueryHelperTest {


    @Test
    public void testQueryINSERT() {
/*        Assert.assertEquals("INSERT INTO Employee (ID, name, surname, address, birthDay, salary) VALUES (?, ?, ?, ?, ?, ?)",
                QueryHelper.createQueryINSERT(new Employee("Juan", "lopez", 333333)));*/
        Assert.assertEquals("INSERT INTO Employee (ID, name, surname, address, birthDay, genere, salary) VALUES (?, ?, ?, ?, ?, ?, ?)",
                QueryHelper.createQueryINSERT(new Employee("Juan", "lopez", "juan@upc.edu", 333333)));

    }

    @Test
    public void testQueryINSERT2() {
        Assert.assertEquals("INSERT INTO Deparment (ID, name, description) VALUES (?, ?, ?)",
                QueryHelper.createQueryINSERT(new Deparment("ENTEL", "ENGINYERIA TELEMÀTICA")));
    }

    @Test
    public void testQueryINSERT3() {

        Assert.assertEquals("INSERT INTO   (ID, name) VALUES (?, ?)",
                QueryHelper.createQueryINSERT(new Partida()));
    }


    @Test
    public void testQuerySELECT() {
        Assert.assertEquals("SELECT * FROM Employee WHERE ID = ?",
                QueryHelper.createQuerySELECT(new Employee("Juan", "lopez", "",333333)));
    }

    @Test
    public void testQuerySELECT2() {
        Assert.assertEquals("SELECT * FROM Deparment WHERE ID = ?",
                QueryHelper.createQuerySELECT(new Deparment("ENTEL", "ENGINYERIA TELEMÀTICA")));
    }

    @Test
    public void testQueryFindAll() {

        HashMap<String, String> hs = new HashMap<String, String>();
        hs.put("username", "toni");

        Assert.assertEquals("SELECT * FROM Employee WHERE 1=1 AND username=?",
                QueryHelper.createSelectFindAll(Employee.class, hs));
    }

    @Test
    public void testQueryFindAll2() {

        HashMap<String, String> hs = new HashMap();
        hs.put("categoria", "defensa");
        hs.put("color", "rojo");

        String theQuery = QueryHelper.createSelectFindAll(Employee.class, hs);
        System.out.println(theQuery);
        Assert.assertEquals("SELECT * FROM Employee WHERE 1=1 AND color=? AND categoria=?", theQuery);
    }

}
