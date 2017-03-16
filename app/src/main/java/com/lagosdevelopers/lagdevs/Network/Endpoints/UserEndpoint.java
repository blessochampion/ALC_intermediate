package com.lagosdevelopers.lagdevs.Network.Endpoints;


public class UserEndpoint
{
    private static  final String BASE_URL = "https://api.github.com/search/users?q=";
    private static final String location = "lagos";
    private static final String language = "java";
    public static final String DEVELOPERS_KEY = "items";
    private static final String TOKEN = "ec4b3fca12f6654d9441583c0ef8823e9b7b04fb";

    public static String getDeveloperListEndpoint() {
        return BASE_URL+ "location:" + location + "+language:"+language;
    }

    public static String getToken() {
        return TOKEN;
    }
}
