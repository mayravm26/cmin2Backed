package edu.upc.dsa;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ObjectHelperTest {


    @Test
    public void setterTest() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        /*Employee e = new Employee("id1","nom", "", 10000 );

        e.setName("Peopito");
        ObjectHelper.setter(e, "name", "Pepito");
        ObjectHelper.setter(e, "surname", "Grillo");

        Assert.assertEquals("Pepito", e.getName());
        Assert.assertEquals("Grillo", e.getSurname());
*/
    }


    @Test
    public void getterTest() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {
       /* Employee e = new Employee("Pepito","Grillo", "",10000 );


        String userName = (String) ObjectHelper.getter(e, "name");
        String userSurname = (String) ObjectHelper.getter(e, "surname");


        Assert.assertEquals("Pepito", userName);
        Assert.assertEquals("Grillo", userSurname);*/

    }

}
