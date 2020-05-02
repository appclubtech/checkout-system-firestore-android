package tech.appclub.arslan.checkoutsystem.repo

import androidx.lifecycle.LiveData
import tech.appclub.arslan.checkoutsystem.data.CartDAO
import tech.appclub.arslan.checkoutsystem.data.Item

class CartRepository(private val cartDao: CartDAO) {

    val allItems: LiveData<List<Item>> = cartDao.getAllItems()

}