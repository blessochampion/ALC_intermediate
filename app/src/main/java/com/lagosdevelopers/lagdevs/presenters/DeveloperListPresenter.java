package com.lagosdevelopers.lagdevs.presenters;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lagosdevelopers.lagdevs.App;
import com.lagosdevelopers.lagdevs.Network.Endpoints.UserEndpoint;
import com.lagosdevelopers.lagdevs.entities.Developer;
import com.lagosdevelopers.lagdevs.entities.DevelopersFactory;
import com.lagosdevelopers.lagdevs.views.DevelopersView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blessochampion on 3/16/17.
 */

public class DeveloperListPresenter {
    private DevelopersView developersView;

    public DeveloperListPresenter(DevelopersView developersView) {
        this.developersView = developersView;
    }

    public void getListOfDevelopers() {

        List<Developer> developers = DevelopersFactory.getDevelopers();

        if (noDeveloperIsAvailable(developers)) {
            makeNetWorkCallsForDevelopers();

        } else {
            developersView.stopLoading();
            developersView.showDevelopers(developers);
        }

    }

    private void makeNetWorkCallsForDevelopers() {
        developersView.showLoading();
        new DevelopersAsyncTask().execute();
    }

    private boolean noDeveloperIsAvailable(List<Developer> developers) {
        return developers.isEmpty();
    }

    class DevelopersAsyncTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            developersView.showLoading();
        }


        @Override
        protected Void doInBackground(Void... params) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    UserEndpoint.getDeveloperListEndpoint(), null
                    , new Response.Listener<JSONObject>() {


                @Override
                public void onResponse(JSONObject jsonObject) {
                    developersView.stopLoading();
                    Log.e("d", jsonObject.toString());
                    try {

                        JSONArray developerArray = jsonObject.getJSONArray(UserEndpoint.DEVELOPERS_KEY);
                        List<Developer> developers = new ArrayList<>();

                        for (int i = 0; i < developerArray.length(); i++){
                            developers.add(new Developer(developerArray.getJSONObject(i)));
                        }

                        DevelopersFactory.setDevelopers(developers);
                        developersView.stopLoading();
                        developersView.showDevelopers(developers);

                    } catch (JSONException je) {
                    }

                }


            }, new Response.ErrorListener()

            {

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }


            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "token " + UserEndpoint.getToken());
                    return headers;

                }

            };

            int socketTimeout = 70000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(policy);
            App.getInstance().getRequestQueue().add(jsonObjectRequest);

            return null;

        }

    }

}
