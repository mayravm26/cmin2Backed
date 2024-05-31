package edu.upc.dsa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBJDBC2 extends DBJDBC{


    public static void insert() throws SQLException{
        Connection connection = DBUtils.getConnection();

        // SQL INJECTION
        String theQuery = "INSERT INTO User (idUser, name, surname, username, password, coins, fuel, food) VALUES (0, ?,?,?,?,?,?,?)";
        // log.debug

        PreparedStatement statement1  =  connection.prepareStatement(theQuery);
        statement1.setString(1, "carla");
        statement1.setString(2, "ab");
        statement1.setString(3, "carla3");
        statement1.setString(4, "12345");
        statement1.setInt(5, 50);
        statement1.setInt(6, 45);
        statement1.setInt(7, 17);

        // a = b / 0  - null.method();

        /// NULLPOINTER ??
        statement1.execute();
        /// NULLPOINTER ??


        connection.close();

    }



    public static void main(String[] args) throws Exception {
        insert();
        findAll();

        // ORM (Object Relation Mapping) --> DAO (Data Access Object)

/*
        User u = new User("Toni");
        s.save(u); =====================> "INSERT INTO USER ...."
        u.setName("Juan");
        s.update(u); ====> "UPDATE xxx"

        s.save(new Object("Escudo")):; //"INSERT NTO Object
        s.save(new Mapa("Escudo")):;   // INSERT INTO Mapa
*/


    }

}
