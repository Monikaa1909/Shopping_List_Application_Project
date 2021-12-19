package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.ListOfPreferencesDish;
import com.example.shoppinglistapplication.helpfulModel.ListOfPreferencesDetail;

import java.util.List;

@Dao
public interface ListOfPreferencesDishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ListOfPreferencesDish listOfPreferencesDish);

    @Query("DELETE FROM ListOfPreferencesDish")
    void deleteAll();

    @Query("SELECT * FROM listofpreferencesdish")
    LiveData<List<ListOfPreferencesDish>> getAllListOfPreferencesDish();

    @Query("select exists (select * from listofpreferencesdish where idDish = :idDish and idListOfPreferences = :idListOfPreferences)")
    Boolean listOfPreferencesDishExists(int idDish, int idListOfPreferences);

    @Query("UPDATE listofpreferencesdish SET portions = :portions WHERE idDish = :idDish and idListOfPreferences = :idListOfPreferences")
    void updateListOfPreferencesDishPortions(int idDish, int idListOfPreferences, int portions);

    @Query("DELETE FROM listofpreferencesdish where idDish = :idDish and idListOfPreferences = :idListOfPreferences")
    void deleteListOfPreferencesDish(int idDish, int idListOfPreferences);

    @Query("select listofpreferencesdish.idListOfPreferences, listofpreferences.listofpreferencesName, dish.idDish, " +
            "dish.dishName, listofpreferencesdish.portions from listofpreferencesdish, listofpreferences, dish " +
            "where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences " +
            "and listofpreferencesdish.idDish = dish.idDish and listofpreferences.idListOfPreferences = :idListOfPreferences")
    List<ListOfPreferencesDetail> getListOfPreferencesDishDetail(int idListOfPreferences);
}
//select product.productName, sum(ingredientsofthedish.quantity)*sum(listofpreferencesdish.portions)  from listofpreferences, listofpreferencesdish, dish, ingredientsofthedish, product, unitofmeasurement
//        where listofpreferences.idListOfPreferences = listofpreferencesdish.idListOfPreferences and listofpreferencesdish.idDish = dish.idDish and dish.idDish = ingredientsofthedish.idDish and
//        ingredientsofthedish.idProduct = product.idProduct and product.idUnitOfMeasurement = unitofmeasurement.idUnitOfMeasurement and
//        listofpreferences.idlistofpreferences = 1 group by product.productName
//
//jablko  180
//gruszka 140
//pomarancza 225