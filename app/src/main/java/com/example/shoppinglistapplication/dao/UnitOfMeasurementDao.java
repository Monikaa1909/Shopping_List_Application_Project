package com.example.shoppinglistapplication.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.shoppinglistapplication.entity.UnitOfMeasurement;
import java.util.List;

@Dao
public interface UnitOfMeasurementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UnitOfMeasurement unit_of_measurement);

    @Query("DELETE FROM UnitOfMeasurement")
    void deleteAll();

    @Query("SELECT * FROM UnitOfMeasurement")
    LiveData<List<UnitOfMeasurement>> getAllUnits();
}


