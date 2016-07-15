package com.example.bhargavnallajeru.quizgame;

/**
 * Created by bhargavnallajeru on 7/26/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class end extends Activity {

    Button tryagain;
    Button exit;
    TextView score;
    TextView cong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        tryagain = (Button) findViewById(R.id.button4);

        score = (TextView) findViewById(R.id.score);
        score.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("myscore", "0"));


        int x = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("topScore", "0"));
        int y = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("myscore", "0"));



        cong = (TextView) findViewById(R.id.cong);

        if (y>x){
            cong.setVisibility(View.VISIBLE);
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("topScore",""+y).commit();

        }

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(end.this, game.class);
                startActivity(intent);
            }


        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
