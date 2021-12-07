package com.example.shoppinglistapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shoppinglistapplication.database.DataRepository;
import com.example.shoppinglistapplication.entity.Product;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;

import java.util.List;

public class UnitOfMeasurementViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    private LiveData<List<UnitOfMeasurement>> units;

    public UnitOfMeasurementViewModel(@NonNull Application application) {
        super(application);

        dataRepository = DataRepository.getInstance(application);
        units = dataRepository.getAllUnits();
    }

    public void deleteUnitById(long id, DataRepository.Executor executor) {
        dataRepository.deleteUnitById(id);
    }

    public LiveData<List<UnitOfMeasurement>> getAllUnits() {
        return units;
    }

//    public List<Product> getProductsByCategoryName(String name) { return dataRepository.getProductsByCategoryName(name); }

    public Boolean unitExists(String name) { return dataRepository.unitExists(name); }
    public long unitIdByName(String name) { return dataRepository.unitIdByName(name); }

    public void insert(UnitOfMeasurement unitOfMeasurement, DataRepository.Executor executor) {
        dataRepository.insert(unitOfMeasurement, executor);
    }
}
