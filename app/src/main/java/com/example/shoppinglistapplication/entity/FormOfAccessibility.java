package com.example.shoppinglistapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FormOfAccessibility {
    @PrimaryKey(autoGenerate = true)
    private int idFormOfAccessibility;

    @NonNull
    private Float form;

    public int getIdFormOfAccessibility() { return idFormOfAccessibility; }

    public void setIdFormOfAccessibility(int idFormOfAccessibility) { this.idFormOfAccessibility = idFormOfAccessibility; }

    @NonNull
    public Float getForm() { return form; }

    public void setForm(@NonNull Float form) { this.form = form; }

    public FormOfAccessibility(@NonNull Float form) {
        this.form = form;
    }

}
