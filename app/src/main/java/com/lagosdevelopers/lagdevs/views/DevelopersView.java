package com.lagosdevelopers.lagdevs.views;

import com.lagosdevelopers.lagdevs.entities.Developer;

import java.util.List;

/**
 * Created by blessochampion on 3/16/17.
 */

public interface DevelopersView
{
    void showLoading();
    void stopLoading();
    void showDevelopers(List<Developer> developers);
}
