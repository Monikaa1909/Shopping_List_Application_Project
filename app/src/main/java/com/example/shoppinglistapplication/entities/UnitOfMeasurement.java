package com.example.shoppinglistapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UnitOfMeasurement {
    @PrimaryKey(autoGenerate = true)
    private int id_unit_of_measurement;

    @NonNull
    private String unit;

    public int getId_unit_of_measurement() { return id_unit_of_measurement; }
    public void setId_unit_of_measurement(int id_unit_of_measurement) { this.id_unit_of_measurement = id_unit_of_measurement; }

    @NonNull
    public String getUnit() { return unit; }
    public void setUnit(@NonNull String unit) { this.unit = unit; }

    public UnitOfMeasurement(@NonNull String unit) {
        this.unit = unit;
    }
}
