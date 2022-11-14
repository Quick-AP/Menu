package com.ap.apmenu.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.ap.apmenu.controller.MenuController;
import com.ap.apmenu.databinding.ActivityConfirmationBinding;

import java.util.Locale;

public class ConfirmationActivity extends AppCompatActivity {

    private ActivityConfirmationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            MenuController menuController = extras.getParcelable("MENU_CONTROLLER");
            Toast.makeText(
                    getApplicationContext(),
                    String.format(Locale.ENGLISH, "%.2f", menuController.getTotalPrice()),
                    Toast.LENGTH_SHORT).show();
        }

    }
}