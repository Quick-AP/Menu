package com.ap.apmenu.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ap.apmenu.controller.MenuController;
import com.ap.apmenu.databinding.ActivityMenuBinding;
import com.ap.apmenu.design.MenuGridViewAdapter;
import com.ap.apmenu.model.FoodArrayListModel;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding menuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MenuController menuController = new MenuController();

        // Establish UI Bindings
        menuBinding = ActivityMenuBinding.inflate(getLayoutInflater());
        View menuView = menuBinding.getRoot();
        setContentView(menuView);


        // TODO: Load Food Array List from json
        MenuGridViewAdapter menuGridViewAdapter = new MenuGridViewAdapter(
                MenuActivity.this,
                new FoodArrayListModel().generateDummyData(),
                menuController
        );

        menuBinding.menuGridView.setAdapter(menuGridViewAdapter);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void updateTotalPrice(String newText) {
        menuBinding.menuTotalPrice.setText(newText);
    }


}