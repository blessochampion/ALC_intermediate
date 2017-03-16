package com.lagosdevelopers.lagdevs.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blessochampion on 3/16/17.
 */

public class DevelopersFactory {
    private final static  String ERROR_MESSAGE = "No developers available";

    private static  List<Developer> developers = new ArrayList<>();

    public static List<Developer> getDevelopers(){
             return developers;
    }

    public static Developer getDeveloperAtPosition(int positon){
        if(developers.isEmpty())
            throw new RuntimeException(ERROR_MESSAGE);
        return developers.get(positon);
    }


    public static void setDevelopers(List<Developer> developers) {
        DevelopersFactory.developers = developers;
    }
}
