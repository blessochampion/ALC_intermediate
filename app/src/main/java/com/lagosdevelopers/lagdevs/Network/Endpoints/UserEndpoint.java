package com.lagosdevelopers.lagdevs.Network.Endpoints;


import com.lagosdevelopers.lagdevs.App;

public class UserEndpoint
{
    private static  final String BASE_URL = "https://api.github.com/search/users?q=";
    private static final String location = "lagos";
    private static final String language = "java";
    public static final String DEVELOPERS_KEY = "items";


    public static String getDeveloperListEndpoint() {
        return BASE_URL+ "location:" + location + "+language:"+language;
    }

    public static String getToken() {
        return App.TOKEN;
    }
}
