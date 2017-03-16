package com.lagosdevelopers.lagdevs.presenters;

import com.lagosdevelopers.lagdevs.entities.Developer;
import com.lagosdevelopers.lagdevs.entities.DevelopersFactory;

/**
 * Created by blessochampion on 3/16/17.
 */

public class DeveloperDetailsPresenter
{
    public Developer getSelectedDeveloper(int position){
        return DevelopersFactory.getDeveloperAtPosition(position);
    }
}
