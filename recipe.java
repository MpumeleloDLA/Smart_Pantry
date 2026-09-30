package com.example.smartpantry.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName="recipes")
public class recipe {


    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String reqIngredients;
    public String steps;

    //the use of constructors
    public recipe() {
    }

    public recipe(String name, String reqIngredients, String steps) {
        this.name = name;
        this.reqIngredients = reqIngredients.toLowerCase();
        this.steps = steps;


    }
//the use of getters and setters


    public int getID() {
        return id;

    }

    public void setID() {
        this.id = id;
    }


    public String getName() {
        return name;

    }

    public void setName() {
        this.name = name;
    }

    public String getReqIngredients() {
        return reqIngredients;
    }

    public void setIngredients() {
        this.reqIngredients = reqIngredients;
    }


    public String getSteps() {
        return steps;
    }

    public void setSteps() {
        this.steps = steps;
    }
}
