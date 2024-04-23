package com.example.tipappassignment4136;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//Kantemir Nasirov, IS413 GUI, PROF HOUDESHELL, 4/22/24

//This program is designed to split the bill for a customer upon requesting the Total Amount, the Perceived Service Quality upon which tip is calculated,
//as well as the number of people that are present

//I had run into some problems with sending data from 1 Activity to another
//Whenever I would try to store a number using regular inheritance techniques it would be lost in process
//and result in the 2nd activity seeing the value as 0.0 or null

//I used a solution from GeeksforGeeks
//https://www.geeksforgeeks.org/how-to-send-data-from-one-activity-to-second-activity-in-android/
//the solution uses the putExtra() method and getStringExtra() method to move data between activities

//Solution came out pretty verbose but I couldn't figure out another way of solving it without my app crashing
//I had commented out my attempts at solving the problem for my future use

public class MainActivity extends AppCompatActivity {   //class declaration

    final double exc = 1.22;   //tip amounts
    final double avg = 1.18;
    final double por = 1.14;
    public String serviceQuality;   //String for the spinner to compare like in the Ticket Activity we did in class
    public double tipPercent;   //double to store the actual tip percent

    //-----------------------------------------------------------------------------------------------------------------------------------------//

//    public void setPricePP(double pricePP){   //tried creating a setter and getter for inheritance

                                                        //didn't ended up needing it

//        //pricePP = total / nGuests;
//        this.pricePP = pricePP;
//    }
//    public double getPricePP() {
//        return pricePP;
//    }
//
//    public List<String> valuesList = new ArrayList<>();

    //-----------------------------------------------------------------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //onCreate
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText etTotal =findViewById(R.id.etTotal);   //total $$$
        EditText etNGuests = findViewById(R.id.etNGuests);    //number of guests

        Spinner group = findViewById(R.id.serviceTip); //Spinner declaration

        Button btCost = findViewById(R.id.btnCont);

        Resources res = getResources();                 //Resources for the spinner
        String[] tip = res.getStringArray(R.array.tipAmount);            //Grab the array
        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<>(this,R.layout.spinnerlist, tip);
        arrayAdapter.setDropDownViewResource(R.layout.spinnerlist);
        group.setAdapter(arrayAdapter);


        btCost.setOnClickListener(e ->   //Lambda expression on button click
        {
            serviceQuality = group.getSelectedItem().toString();     //singling out the group
            tipPercent = calculateTip(serviceQuality);     //storing the tip percent

            Intent intent = new Intent(MainActivity.this, PriceScreen.class);

            String value1 = String.valueOf(etTotal.getText());
            String value2 = String.valueOf(etNGuests.getText());
            String value3 = String.valueOf(tipPercent);

            intent.putExtra("TOTAL", value1);
            intent.putExtra("NGUESTS", value2);
            intent.putExtra("TIPPERCENT", value3);

            startActivity(intent);

            //-----------------------------------------------------------------------------------------------------------------------------------------//


            /*pricePP = total / nGuests;
            subTotal = Integer.parseInt(String.valueOf(etTotal.getText()));
            nGuests = Integer.parseInt(String.valueOf(etNGuests.getText()));
            serviceTip = group.getSelectedItem().toString();
            System.out.println(subTotal);
            System.out.println(nGuests);
            valuesList.add(String.valueOf(subTotal));
            valuesList.add(String.valueOf(nGuests));
            //subTotal = Integer.parseInt(String.valueOf(etTotal.getText()));
            /*PriceScreen priceScreen = new PriceScreen();
            priceScreen.setTotal(subTotal);
            if(priceScreen.getTotal() > 0.0){
                System.out.println(priceScreen.getTotal());
            }else{
                System.out.println("Wrong");
            }
            startActivity(new Intent(this,PriceScreen.class));*/
            /*Bundle bundle = new Bundle();
            bundle.putDouble("value", subTotal);*/
        });



    }


    //-----------------------------------------------------------------------------------------------------------------------------------------//





    //-----------------------------------------------------------------------------------------------------------------------------------------//


    public double calculateTip(String serviceTip){          //Compares the chosen value in the spinner to the ones in the strings.xml
        double tipAmnt = 0.0;
        Resources res = getResources();

        String[] tip = res.getStringArray(R.array.tipAmount);           //provided in Ticketing Activity in class
        Iterator<String> iterator = Arrays.asList(tip).iterator();

        while(iterator.hasNext()){
            if("Excellent (22% tip)".equals(serviceTip)){
                tipAmnt = exc;
                break;
            } else if ("Average (18% tip)".equals(serviceTip)) {
                tipAmnt = avg;
                break;
            } else if ("Poor (14% tip)".equals(serviceTip)) {
                tipAmnt = por;
                break;
            }
        }
        return tipAmnt;

    }
}