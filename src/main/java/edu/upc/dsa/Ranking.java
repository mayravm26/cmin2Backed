package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.List;

public class Ranking
{
    public List<User> getRanking()
    {
        List<User> rankingList = new ArrayList<>();

        rankingList.add(new User("laura","https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png",30));
        rankingList.add(new User("Luis","https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png",10));
        return rankingList
    }
}
