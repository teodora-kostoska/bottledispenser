package com.example.bottledispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottleDispenser dispenser = null;
    ArrayList<String> bottles = null;
    Button money_button;
    Button get_money;
    Button buy_bottle;
    EditText text;
    SeekBar seekBar;
    TextView seekBarText;
    Spinner spinner;
    int money = 0;
    int choice = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create bottle dispenser
        dispenser = BottleDispenser.getInstance();
        // Get list of all the bottles in the dispenser
        bottles = dispenser.listBottles();
        money_button = findViewById(R.id.money_button);
        get_money = findViewById(R.id.money_return);
        buy_bottle = findViewById(R.id.buy_bottle);
        text = findViewById(R.id.editText3);
        seekBar = findViewById(R.id.seekBar);
        seekBarText = findViewById(R.id.seekBarText);
        //Check what's in seekbar
        money = setMoney();
        spinner = findViewById(R.id.spinner);
        //Load list of bottles into seekbar
        setSpinner();
    }
    //Add money to the dispenser, uses the value in money variable to add the amount that is in seekbar
    public void addMoney(View v){
        String get = dispenser.addMoney(money);
        text.setText(get);
    }
    //Return money when done with using dispenser
    public void returnMoney(View v){
        String get = dispenser.returnMoney();
        text.setText(get);
    }
    //Module to call bottledispenser class' buyBottle module, which will check whether there is enough money
    //and bottles to complete the buy
    public void buyBottle(View v){
        String get = dispenser.buyBottle(choice);
        text.setText(get);
        //Reset spinner with new values as if bottle is bought it is removed from bottle array list
        bottles = dispenser.listBottles();
        setSpinner();
    }
    //Module to listen to seekbar, when seek bar changes it changes the money amount
    public int setMoney(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                money = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String money2 = String.valueOf(money);
                seekBarText.setText(money2);
            }
        });
        return money;
    }
    //module to set the bottle list into the spinner and set a listener on the spinner, so that the correct bottle can be returned
    public void setSpinner() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bottles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }
}