package edu.niu.students.z1776873.crapsassignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Soukya and Harshitha on 10/20/2016.
 */

public class MainActivity extends AppCompatActivity {


    //declaring the variables for the widgets
    private Button rollButton;
    private Button restartButton;
    private TextView gameStatusTextView;
    private TextView diceSumTextView;
    private ImageView dice1Image;
    private ImageView dice2Image;
    private int dice1ImagePath;
    private int dice2ImagePath;
    private Die die1;
    private Die die2;
    int tmp = 0;
    int point = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //creating the objects of Die class
        die1 = new Die();
        die2 = new Die();


        //assigning the UI widgets to the declared variables
        gameStatusTextView = (TextView)findViewById(R.id.statusTextView);
        diceSumTextView = (TextView)findViewById(R.id.sumTextView);

        rollButton = (Button)findViewById(R.id.rollButton);
        restartButton = (Button)findViewById(R.id.restartButton);
        dice1Image = (ImageView)findViewById(R.id.die1);
        dice2Image = (ImageView)findViewById(R.id.die2);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if ( id == R.id.help)               //Condition for help option
        {
            Intent i = new Intent(this,ActivityHelp.class);
            startActivity(i);
            return true;
        }
        if(id == R.id.newGame)              //Condition for new game
        {
            Intent i = new Intent(this, Main2Activity.class);
            startActivity(i);
            return true;
        }
        if(id == R.id.HelpDicey)            //condition for new game help option
        {
            Intent i = new Intent(this, ActivityDiceyHelp.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //method for the roll button
    public void onRoll(View v)
    {
        //calling the roll method on the two objects
        die1.roll();
        die2.roll();
        dice1ImagePath = getResources().getIdentifier("die" + die1.getFace(),
                "drawable",getPackageName());
        dice2ImagePath = getResources().getIdentifier("die" + die2.getFace(),
                "drawable",getPackageName());

        //assigning the obtained path to the ImageViews
        dice1Image.setImageResource(dice1ImagePath);
        dice2Image.setImageResource(dice2ImagePath);

        //adding the two dice values
        int sum = die1.getFace() + die2.getFace();


        if(tmp == 0)
        {
            if(sum == 7 || sum == 11)
            {
                gameStatusTextView.setText("Congratulations! You won because you rolled a " + sum);
                rollButton.setVisibility(View.INVISIBLE);
                diceSumTextView.setText("Sum: "+sum);

            }
            if(sum == 2 || sum == 3 || sum == 12)
            {
                gameStatusTextView.setText("You are a loser because you rolled a " +sum);
                rollButton.setVisibility(View.INVISIBLE);
                diceSumTextView.setText("Sum: "+sum);
            }
            if(sum == 4 || sum == 5 || sum == 6 || sum == 8 || sum == 9 || sum == 10 )
            {
                gameStatusTextView.setText("You need to roll " + sum + " in order to win.");
                diceSumTextView.setText("Sum: "+sum);
                point = sum;
                tmp = 1;


            }}
        else
        {
            if(sum == 7){
                gameStatusTextView.setText("You are a loser because you rolled a " +sum);
                rollButton.setVisibility(View.INVISIBLE);
                diceSumTextView.setText("Sum: "+sum);

            }
            else if(sum == point){
                gameStatusTextView.setText("Congratulations! You won because you rolled a " + sum);
                rollButton.setVisibility(View.INVISIBLE);
                diceSumTextView.setText("Sum: "+sum);

            }
            else{
                gameStatusTextView.setText("You need to roll " + sum + " in order to win.");
                diceSumTextView.setText("Sum: "+sum);
                point = sum;
                tmp = 1;
            }
        }


    }


    //method for restart button
    public void onRestart(View v)
    {
        dice1Image.setImageResource(R.drawable.die0);
        dice2Image.setImageResource(R.drawable.die0);
        gameStatusTextView.setText("");
        diceSumTextView.setText("Sum: 0");
        rollButton.setVisibility(View.VISIBLE);
        tmp = 0;
        point = 0 ;
    }

}
