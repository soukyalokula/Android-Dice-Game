package edu.niu.students.z1776873.crapsassignment2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Soukya and Harshitha on 10/20/2016.
 */


public class Main2Activity extends AppCompatActivity {

    //declaring the variables for the widgets
    private Button rollButton;
    private Button restartButton;
    private TextView gameStatusTextView;
    private TextView diceSumTextView;

    private ImageView dice1Image;
    private ImageView dice2Image;
    private int dice1ImagePath;
    private int dice2ImagePath;

    //die objects
    private Die die1;
    private Die die2;

    int rolls = 0;
    int totalSum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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


    //method for the roll button
    public void onRoll(View v)
    {
        //calling the roll method on the two objects
        die1.roll();
        die2.roll();

        //getting the image path from drawable folder
        dice1ImagePath = getResources().getIdentifier("die" + die1.getFace(),
                "drawable",getPackageName());
        dice2ImagePath = getResources().getIdentifier("die" + die2.getFace(),
                "drawable",getPackageName());

        //assigning the obtained path to the ImageViews
        dice1Image.setImageResource(dice1ImagePath);
        dice2Image.setImageResource(dice2ImagePath);

        rolls++;

        //adding the two dice values
        int sum = die1.getFace() + die2.getFace();
        totalSum = totalSum + sum;
        if(rolls < 3)
        {
            if(totalSum < 20)
            {
                gameStatusTextView.setText("Your sum is "+sum+". You can roll " +(3-rolls) + " times more.");
                diceSumTextView.setText("Total Sum: "+totalSum);

            }
            if(totalSum >=20)
            {
                gameStatusTextView.setText("You won the game!! You can start another one.");
                diceSumTextView.setText("Total Sum: "+totalSum);
                rollButton.setVisibility(View.INVISIBLE);       //making the button invisible
            }
        }
        else
        {
            if(totalSum >=20) {
                gameStatusTextView.setText("You won the game!! You can start another one.");
                diceSumTextView.setText("Total Sum: " + totalSum);
                rollButton.setVisibility(View.INVISIBLE);       //making the button invisible
            }
            else {
                rollButton.setVisibility(View.INVISIBLE);
                gameStatusTextView.setText("You lost!! The game is over. You can restart a new one.");
                diceSumTextView.setText("Total Sum: " + totalSum);      //making the button invisible
            }
        }


    }
    //method for the restart button
    public void onRestart(View v)
    {
        dice1Image.setImageResource(R.drawable.die0);
        dice2Image.setImageResource(R.drawable.die0);
        gameStatusTextView.setText("");

        diceSumTextView.setText("Total Sum: 0");
        rollButton.setVisibility(View.VISIBLE);         //making the button visible
        totalSum = 0;
        rolls = 0 ;
    }
}
