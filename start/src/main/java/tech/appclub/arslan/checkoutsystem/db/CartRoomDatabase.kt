package tech.appclub.arslan.checkoutsystem.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tech.appclub.arslan.checkoutsystem.data.CartDAO
import tech.appclub.arslan.checkoutsystem.data.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
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

            var item = Item(
                image = "https://img.pngio.com/sodas-png-png-collections-at-" +
                        "sccprecat-sodas-png-734_428.png",
                name = "6 Sodas",
                price = 10.0
            )
            cartDAO.insert(item)

            item = Item(
                image = "https://www.pngitem.com/pimgs/m/41-410728_cakes-and" +
                        "-sweets-png-transparent-png.png",
                name = "Chocolate Cake",
                price = 22.0
            )
            cartDAO.insert(item)

            item = Item(
                image = "https://lh3.googleusercontent.com/proxy/_dw8jlWqVq5Gv5tbJZbC58" +
                        "_dnTikgzCzJK9lT9NgpRZBaqInX4q5nDzonprU-yU65sfHUrsMcHbMVAmNblpdl" +
                        "tAqiq2Zuz7XW4gcYSBi0-kEHw",
                name = "Fruit Basket",
                price = 63.0
            )
            cartDAO.insert(item)

            item = Item(
                image = "https://www.freepnglogos.com/uploads/vegetables-png/" +
                        "fruit-and-vegetables-basket-png-20.png",
                name = "Vegetable Basket",
                price = 40.0
            )
            cartDAO.insert(item)


        }

    }


}