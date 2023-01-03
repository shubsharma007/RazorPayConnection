package com.example.razorpay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.razorpay.databinding.ActivityMainBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String sAmount = "10";
        int amount = Math.round(Float.parseFloat(sAmount) * 10);
        binding.btnPay2.setOnClickListener(v -> {

            Checkout checkout = new Checkout();

                
            checkout.setKeyID("");

            JSONObject object = new JSONObject();

            try {
                object.put("name", "Android Developer");
                object.put("Description", "Text Payment");
                object.put("theme.color", "#0093DD");
                object.put("currency", "INR");
                object.put("amount", amount);
                // object.put("prefill.contact","9981667567");
                //  object.put("prefill.email","shub.sharma@gmail.com");

                checkout.open(MainActivity.this, object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void onPaymentSuccess(String s) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Payment ID");

        builder.setMessage(s);

        builder.show();

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }
}