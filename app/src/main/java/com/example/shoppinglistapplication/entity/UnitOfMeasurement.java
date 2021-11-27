package com.example.shoppinglistapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UnitOfMeasurement {
    @PrimaryKey(autoGenerate = true)
    private int idUnitOfMeasurement;

    @NonNull
    private String unit;

    public int getIdUnitOfMeasurement() { return idUnitOfMeasurement; }
    public void setIdUnitOfMeasurement(int idUnitOfMeasurement) { this.idUnitOfMeasurement = idUnitOfMeasurement; }

    @NonNull
    public String getUnit() { return unit; }
    public void setUnit(@NonNull String unit) { this.unit = unit; }

    public UnitOfMeasurement(@NonNull String unit) {
        this.unit = unit;
    }
}
