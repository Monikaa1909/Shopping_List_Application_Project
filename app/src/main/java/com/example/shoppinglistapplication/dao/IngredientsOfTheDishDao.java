package com.example.shoppinglistapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.shoppinglistapplication.entity.IngredientsOfTheDish;
import com.example.shoppinglistapplication.helpfulModel.DishDetail;

import java.util.List;

@Dao
public interface IngredientsOfTheDishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(IngredientsOfTheDish ingredientsOfTheDish);

    @Query("SELECT * FROM IngredientsOfTheDish")
    LiveData<List<IngredientsOfTheDish>> getAllIngredientsOfTheDish();

    @Delete
    void delete(IngredientsOfTheDish ingredientsOfTheDish);

    @Query("DELETE FROM IngredientsOfTheDish")
    void deleteAll();

    @Query("select exists (select * from ingredientsofthedish where idProduct = :idProduct and idDish = :idDish)")
    Boolean ingredientsExists(int idProduct, int idDish);

    @Query("UPDATE ingredientsofthedish SET quantity = :quantity WHERE idProduct =:idProduct and idDish = :idDish")
    void updateIngredientQuantity(int idProduct, int idDish, float quantity);

    @Query("DELETE FROM ingredientsofthedish where idProduct = :idProduct and idDish = :idDish")
    void deleteIngredientOfTheDish(int idProduct, int idDish);

    @Query("select product.productName, product.idProduct, ingredientsofthedish.quantity, unitofmeasurement.unit, ingredientsofthedish.idDish from ingredientsofthedish, product, dish, unitofmeasurement where unitofmeasurement.idUnitOfMeasurement = product.idUnitOfMeasurement and ingredientsofthedish.idDish = dish.idDish and ingredientsofthedish.idProduct = product.idProduct and ingredientsofthedish.idDish = :idDish")
    List<DishDetail> getDetail(int idDish);
}

