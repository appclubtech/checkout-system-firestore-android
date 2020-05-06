package tech.appclub.arslan.checkoutsystem.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tech.appclub.arslan.checkoutsystem.data.Cart
import tech.appclub.arslan.checkoutsystem.data.CartDAO

@Database(entities = [Cart::class], version = 2, exportSchema = false)
abstract class CartRoomDatabase : RoomDatabase() {

    abstract fun cartDAO(): CartDAO

    companion object {

        @Volatile
        private var INSTANCE: CartRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CartRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartRoomDatabase::class.java,
                    "cart_database"
                ).addCallback(CartDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

    }

    private class CartDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.cartDAO())
                }
            }
        }

        private suspend fun populateDatabase(cartDAO: CartDAO) {

            cartDAO.deleteAllItems()

            cartDAO.insert(Cart(name = "Fruit Basket", price = 100.0))
            cartDAO.insert(Cart(name = "Vegetable Basket", price = 90.0))
            cartDAO.insert(Cart(name = "Dairy Products", price = 60.0))
            cartDAO.insert(Cart(name = "Electronic Products", price = 200.0))
            cartDAO.insert(Cart(name = "Jellies", price = 40.0))
            cartDAO.insert(Cart(name = "First Aid Kit", price = 170.0))
            cartDAO.insert(Cart(name = "Video Game Collection", price = 2000.0))
            cartDAO.insert(Cart(name = "Washing Items", price = 290.0))


        }

    }


}