package com.example.bhargavnallajeru.quizgame;

/**
 * Created by bhargavnallajeru on 7/26/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;


public class game extends Activity {

    String states[] = {"California","Washington","Florida","Idaho","Tennessee","Texas","Wyoming"};
    String capitals[] = {"Sacramento","Olympia","Tallahassee","Boise","Nashville","Austin","Cheyenne"};
    int statesids[] = {R.drawable.california,R.drawable.washington,R.drawable.florida,R.drawable.idaho,R.drawable.tennessee,R.drawable.texas,R.drawable.wyoming};

    int options[] = {-1,-1,-1,-1};
    int alreadydone[] = {-1,-1,-1,-1,-1,-1,-1};
    int score = 0;
    int correct = -1;
    ImageView stateimage;
    Button opt1;
    Button opt2;
    Button opt3;
    Button opt4;
    Button tryagain;
    Button exit;
    TextView topscore;
    TextView capitalquestion;
    TextView currentscore;
    int questioncounter = 1;
    boolean bonusquest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        stateimage = (ImageView) findViewById(R.id.stateimage);
        opt1 = (Button) findViewById(R.id.button);
        opt2 = (Button) findViewById(R.id.button2);
        opt3 = (Button) findViewById(R.id.button3);
        opt4 = (Button) findViewById(R.id.button4);

        currentscore = (TextView) findViewById(R.id.score);
        currentscore.setText("0");

        topscore = (TextView) findViewById(R.id.topscore);
        topscore.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("topScore", "0"));

        capitalquestion = (TextView) findViewById(R.id.capital);
        myIntialize();

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct == 0) {
                    score = score + 10;
                    currentscore.setText("" + score);
                    bonusQuesiton();
                } else {
                    gameover();
                }
            }


        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bonusquest == true){
                    score = score + 20;
                    currentscore.setText("" + score);
                    opt1.setVisibility(View.VISIBLE);
                    opt4.setVisibility(View.VISIBLE);
                    capitalquestion.setVisibility(View.GONE);
                    myIntialize();

                }
                else {
                    if (correct == 1) {
                        score = score + 10;
                        currentscore.setText("" + score);
                        bonusQuesiton();
                    } else {
                        gameover();
                    }
                }
            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bonusquest == true){
                    opt1.setVisibility(View.VISIBLE);
                    opt4.setVisibility(View.VISIBLE);
                    capitalquestion.setVisibility(View.GONE);
                    myIntialize();

                }
                else {
                    if (correct == 2) {
                        score = score + 10;
                        currentscore.setText("" + score);
                        bonusQuesiton();
                    } else {
                        gameover();
                    }
                }
            }
        });


        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correct == 3) {
                    score = score +10;
                    currentscore.setText(""+score);
                    bonusQuesiton();
                }
                else {
                    gameover();
                }
            }
        });

    }

    private void gameover() {

        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("myscore",""+score).commit();
        Intent intent = new Intent(game.this, end.class);
        startActivity(intent);
    }

    void bonusQuesiton(){
        bonusquest = true;
        opt1.setVisibility(View.GONE);
        opt4.setVisibility(View.GONE);
        capitalquestion.setVisibility(View.VISIBLE);

        Random r = new Random();
        int o = (r.nextInt((4)));

        opt2.setText(capitals[options[correct]]);
        opt3.setText(capitals[options[o]]);

    }

    void myIntialize(){


        if (questioncounter > 5){
            gameover();
        }
        else {
            Random r = new Random();
            bonusquest = false;
            correct = -1;
            options[0] = -1;
            options[1] = -1;
            options[2] = -1;
            options[3] = -1;

            for (int k = 0; k < 4; ) {
                int i = (r.nextInt((7)));

                if (i != options[0] && i != options[1] && i != options[2] && i != options[3]) {
                    options[k] = i;
                    k++;
                }
            }

            correct = (r.nextInt((4)));

            boolean getcorrect = false;
            while (!getcorrect) {


                if   ( alreadydone[0] == options[correct] || alreadydone[1] == options[correct] || alreadydone[2] == options[correct] || alreadydone[4] == options[correct] || alreadydone[3] == options[correct]|| alreadydone[4] == options[correct]|| alreadydone[5] == options[correct] ){
                    getcorrect = false;
                    correct = (r.nextInt((4)));
                }
                else{
                    alreadydone[questioncounter] = options[correct];
                    getcorrect = true;
                }
            }


            stateimage.setImageResource(statesids[options[correct]]);

            opt1.setText(states[options[0]]);
            opt2.setText(states[options[1]]);
            opt3.setText(states[options[2]]);
            opt4.setText(states[options[3]]);
            questioncounter++;
        }

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
