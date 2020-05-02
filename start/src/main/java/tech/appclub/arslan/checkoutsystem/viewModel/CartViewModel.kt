package tech.appclub.arslan.checkoutsystem.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import tech.appclub.arslan.checkoutsystem.data.Item
import tech.appclub.arslan.checkoutsystem.db.CartRoomDatabase
import tech.appclub.arslan.checkoutsystem.repo.CartRepository

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository

    val allItems: LiveData<List<Item>>

    init {
        val cartDAO = CartRoomDatabase.getDatabase(application, viewModelScope).cartDAO()
        repository = CartRepository(cartDAO)
        allItems = repository.allItems
    }

}