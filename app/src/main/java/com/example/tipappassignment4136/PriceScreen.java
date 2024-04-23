package com.example.tipappassignment4136;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;



public class PriceScreen extends AppCompatActivity{

    private double total;
    private double share;

    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();   //formatting style



    //-----------------------------------------------------------------------------------------------------------------------------------------//

//    public double total;

    //-----------------------------------------------------------------------------------------------------------------------------------------//

//    public void setTotal(double total){
//        //total = subTotal * calculateTip(serviceTip);
//        this.total = total;
//    }

    //-----------------------------------------------------------------------------------------------------------------------------------------//

//    public double getTotal() {
//        return total;
//    }

    //-----------------------------------------------------------------------------------------------------------------------------------------//

        @Override
    protected void onCreate(Bundle savedInstanceState) {  //onCreate
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_price_screen);

        TextView tvtotal = findViewById(R.id.tvTotal);   //Text View declarations
        TextView tvpp = findViewById(R.id.tvPerPerson);

        Button calculate = findViewById(R.id.btnCalc);

        calculate.setOnClickListener(e ->           //on click listener, when calculate button is pressed
        {
            String value1 = getIntent().getStringExtra("TOTAL");            //grabs the Extra string
            String value2 = getIntent().getStringExtra("NGUESTS");
            String value3 = getIntent().getStringExtra("TIPPERCENT");

            assert value1 != null;
            double subTotal = Double.parseDouble(value1);               //takes the string and transforms it into a double

            assert value3 != null;
            double tipPercent = Double.parseDouble(value3);

            total = subTotal * tipPercent;          //calculates total

            assert value2 != null;
            share = total / Double.parseDouble(value2);         //calculates individual share


            String resultText = "Total cost for the party today is: " + numberFormat.format(total);     //makes a resultText String to print out later

            String guestsText = "Your share today is: " + numberFormat.format(share);

            tvtotal.setText(resultText);        //prints out the result in TextView
            tvpp.setText(guestsText);       //prints out the result in TextView

            //String resultText = "Total cost for the party today is: " + total;
            //tvtotal.setText(resultText);
            //System.out.println(getTotal());
            /*String resultPP = "Your share today is: " + numberFormat.format(nGuests);
            //String resultPP = "Your share today is: " + pricePP;
            tvpp.setText(resultPP);
            */
        });




    }

}