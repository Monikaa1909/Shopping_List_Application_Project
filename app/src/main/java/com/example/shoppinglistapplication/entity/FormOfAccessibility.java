package com.example.shoppinglistapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FormOfAccessibility {
    @PrimaryKey(autoGenerate = true)
    private int idFormOfAccessibility;

    @NonNull
    private double form;

    public int getIdFormOfAccessibility() { return idFormOfAccessibility; }

    public void setIdFormOfAccessibility(int idFormOfAccessibility) { this.idFormOfAccessibility = idFormOfAccessibility; }

    @NonNull
    public double getForm() { return form; }

    public void setForm(@NonNull double form) { this.form = form; }

    public FormOfAccessibility(@NonNull double form) {
        this.form = form;
    }

}
