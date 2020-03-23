package sirsecurity.smoothbalance.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sirsecurity.smoothbalance.database.MoneyItemDatabase
import sirsecurity.smoothbalance.models.MoneyItemModel
import sirsecurity.smoothbalance.repositories.MoneyItemRepository

class MoneyItemViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository : MoneyItemRepository
    val allMoneyItems: LiveData<List<MoneyItemModel>>

    init {
        val recipeDao = MoneyItemDatabase.getDatabase(application).recipeDao()
        repository = MoneyItemRepository(recipeDao)
        allMoneyItems = repository.allMoneyItems
    }

    fun insert(moneyItem: MoneyItemModel) = viewModelScope.launch {
        repository.insert(moneyItem)
    }

    fun update(moneyItem: MoneyItemModel) = viewModelScope.launch {
        repository.update(moneyItem.id, moneyItem.name, moneyItem.description, moneyItem.eventDate, moneyItem.state, moneyItem.price)
    }

    fun delete(moneyItem: MoneyItemModel) = viewModelScope.launch {
        repository.delete(moneyItem)
    }
}