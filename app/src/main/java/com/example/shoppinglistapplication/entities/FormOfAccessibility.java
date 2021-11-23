package com.example.shoppinglistapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FormOfAccessibility {
    @PrimaryKey(autoGenerate = true)
    private int id_form_of_accessibility;

    @NonNull
    private Float form;

    public int getId_form_of_accessibility() { return id_form_of_accessibility; }

    public void setId_form_of_accessibility(int id_form_of_accessibility) { this.id_form_of_accessibility = id_form_of_accessibility; }

    @NonNull
    public Float getForm() { return form; }

    public void setForm(@NonNull Float form) { this.form = form; }

    public FormOfAccessibility(@NonNull Float form) {
        this.form = form;
    }

}
