package sirsecurity.smoothbalance.repositories

import sirsecurity.smoothbalance.database.MoneyItemDao
import sirsecurity.smoothbalance.models.MoneyItemModel

class MoneyItemRepository(private val moneyItemDao : MoneyItemDao) {
    val allMoneyItems = moneyItemDao.getMoneyItems()

    suspend fun insert(recipe: MoneyItemModel)
            = moneyItemDao.insertMoneyItem(recipe)

    suspend fun update(id: Int, name: String, description: String, eventDate : String, state : String, price: Double)
            = moneyItemDao.updateMoneyItem(id, name, description, eventDate, state, price)

    suspend fun delete(moneyItem: MoneyItemModel)
            = moneyItemDao.deleteMoneyItem(moneyItem.id)
}