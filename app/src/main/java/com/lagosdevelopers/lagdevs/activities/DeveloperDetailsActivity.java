package com.lagosdevelopers.lagdevs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.text.util.LinkifyCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.lagosdevelopers.lagdevs.R;
import com.lagosdevelopers.lagdevs.entities.Developer;
import com.lagosdevelopers.lagdevs.presenters.DeveloperDetailsPresenter;
import com.lagosdevelopers.lagdevs.utils.ImageUtils;
import com.lagosdevelopers.lagdevs.utils.TextUtils;

public class DeveloperDetailsActivity extends AppCompatActivity {

    public static String KEY_DEVELOPER_POSITION = "POSITION";
    private final String TITLE = "Details";

    Developer developer;
    DeveloperDetailsPresenter presenter;

    ImageView profileImage;
    TextView username;
    TextView profileUrl;
    private ShareActionProvider shareActionProvider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_details);

        presenter = new DeveloperDetailsPresenter();

        int position  = getIntent().getIntExtra(KEY_DEVELOPER_POSITION, 0);
        developer = presenter.getSelectedDeveloper(position);

        getReferencesToWidgets();

        bindData();
    }

    private void getReferencesToWidgets(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(TITLE);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileImage = (ImageView) findViewById(R.id.profile_image);
        username = (TextView) findViewById(R.id.username);
        profileUrl = (TextView) findViewById(R.id.profile_url);
    }

    private void bindData(){
        profileUrl.setText(developer.getGithubProfileUrl());
        LinkifyCompat.addLinks(profileUrl, Linkify.WEB_URLS);

        username.setText(TextUtils.capitalizeFirsCharacterIn(developer.getUsername()));

        ImageUtils.loadImageInto(this, profileImage, R.drawable.avatar, developer.getProfilePhotoUrl());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_developer_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_item_share){
            onShareAction();
        }else if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void onShareAction() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,composeIntentMessage() );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,"Share using"));

    }

    private String composeIntentMessage(){
        return  "Checkout this awesome developer @" + developer.getUsername()
                + ", "+ developer.getGithubProfileUrl();
    }

}
