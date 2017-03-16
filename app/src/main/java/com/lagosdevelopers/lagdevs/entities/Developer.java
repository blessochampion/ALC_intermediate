package com.lagosdevelopers.lagdevs.entities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by blessochampion on 3/12/17.
 */

public class Developer {
    private final String KEY_PROFILE_PHOTO_URL = "avatar_url";
    private final String KEY_USERNAME = "login";
    private final String KEY_PROFILE_URL = "url";

    private final String TAG = Developer.class.getName();

    private String username;
    private String profilePhotoUrl;
    private String githubProfileUrl;

    public Developer(JSONObject developer){
        try {
            username = developer.getString(KEY_USERNAME);
            profilePhotoUrl = developer.getString(KEY_PROFILE_PHOTO_URL);
            githubProfileUrl = developer.getString(KEY_PROFILE_URL);

        }catch (JSONException je){
            Log.e(TAG, je.getMessage());
        }

    }

    public String getUsername() {
        return username;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public String getGithubProfileUrl() {
        return githubProfileUrl;
    }


}
