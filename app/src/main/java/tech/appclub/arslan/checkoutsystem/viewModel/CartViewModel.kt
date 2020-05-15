package tech.appclub.arslan.checkoutsystem.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import tech.appclub.arslan.checkoutsystem.data.Cart
import tech.appclub.arslan.checkoutsystem.db.CartRoomDatabase
import tech.appclub.arslan.checkoutsystem.repo.CartRepository

open class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository

    val allItems: LiveData<List<Cart>>

    init {
        val cartDAO = CartRoomDatabase.getDatabase(application, viewModelScope).cartDAO()
        repository = CartRepository(cartDAO)
        allItems = repository.allItems
    }

}