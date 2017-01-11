package com.example.android.justjava; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.justjava.R;

import java.text.NumberFormat;

import static com.example.android.justjava.R.id.amountOfQuantity;
import static com.example.android.justjava.R.id.quantity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int no_of_coffee=0;
    public void increment(View View)
    {
        displayQuantity(++no_of_coffee);
    }
    public void decrement(View View)
    {
        if(no_of_coffee>0){
        displayQuantity(--no_of_coffee);}
        else
        {displayQuantity(no_of_coffee);}
    }
    public void submitOrder(View View )
    {
        EditText nameField= (EditText) findViewById(R.id.name);
                String name = nameField.getText().toString();
         CheckBox whipped_checkBox= (CheckBox)findViewById(R.id.whiped_checkBox);
         boolean has_whipped_cream=whipped_checkBox.isChecked();
          int price=calculatePrice(has_whipped_cream);
         String priceMessage=orderSummary(price, has_whipped_cream, name);
            String to[]={"shivansh919@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, "coffee order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
          TextView priceTextView = (TextView) findViewById(R.id.compliment);
          priceTextView.setText(priceMessage);

        }





    public int calculatePrice(boolean has_whipped_cream)
    {
        int baseprice=15;
        if(has_whipped_cream)
        {
            baseprice=baseprice+5;
        }
    return no_of_coffee*baseprice;
    }


    /**
     * This method displays the given price on the screen.
     */
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(amountOfQuantity);
        quantityTextView.setText("" + number);
    }

    private String orderSummary(int price, boolean has_whipped_cream, String name)
    {
        String priceMessage="Thank You";
        priceMessage+="\nName- "+name;
        priceMessage+="\nHas Whipped Cream- "+has_whipped_cream;
        priceMessage+="\nQuantity- "+no_of_coffee;
        priceMessage+="\nTotal- "+price+"$";
        return priceMessage;
    }


}