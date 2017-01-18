package edu.niu.students.z1776873.crapsassignment2;


import java.util.Random;

/**
 * Created by Soukya and Harshitha on 10/20/2016.
 */
public class Die
{
    //data members
    private int face;
    private Random r;

    //default constructor sets the face value to 1
    public Die()
    {
        this.face = 1;
        r = new Random();
    }

    //randomly chooses a value for the die
    public int roll()
    {
        this.face = r.nextInt(6) + 1;
        return this.face;
    }

    //getter
    public int getFace()
    {

        return this.face;
    }

    //setting the value of the face
    public void setFace(int face)
    {
        if(face < 1)
        {
            this.face = 1;
        }
        else if(face > 6)
        {
            this.face = 6;
        }
        else
        {
            this.face = face;
        }
    }
}



