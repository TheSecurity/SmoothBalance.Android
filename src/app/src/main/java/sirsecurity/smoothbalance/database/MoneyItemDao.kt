package sirsecurity.smoothbalance.database

import androidx.lifecycle.LiveData
import androidx.room.*
import sirsecurity.smoothbalance.models.MoneyItemModel

@Dao
interface MoneyItemDao
{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoneyItem(moneyItem: MoneyItemModel)

    @Query("SELECT * FROM moneyItems")
    fun getMoneyItems(): LiveData<List<MoneyItemModel>>

    @Query("UPDATE moneyItems SET name = :name, description = :description, eventDate = :eventDate, price = :price, state = :state WHERE id = :id")
    suspend fun updateMoneyItem(id: Int, name: String, description: String, eventDate : String, state : String, price: Double)

    @Query("DELETE FROM moneyItems WHERE id = :id")
    suspend fun deleteMoneyItem(id: Int)

}