package tech.appclub.arslan.checkoutsystem.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDAO {

    @Query("SELECT * FROM cart_table ORDER BY name ASC")
    fun getAllItems(): LiveData<List<Cart>>

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllItems()

    @Insert
    suspend fun insert(cart: Cart)

}