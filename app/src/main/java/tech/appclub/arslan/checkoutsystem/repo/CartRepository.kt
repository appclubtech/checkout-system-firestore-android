package tech.appclub.arslan.checkoutsystem.repo

import androidx.lifecycle.LiveData
import tech.appclub.arslan.checkoutsystem.data.Cart
import tech.appclub.arslan.checkoutsystem.data.CartDAO

class CartRepository(private val cartDao: CartDAO) {

    val allItems: LiveData<List<Cart>> = cartDao.getAllItems()

}