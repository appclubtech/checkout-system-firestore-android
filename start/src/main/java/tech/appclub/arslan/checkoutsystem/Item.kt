package tech.appclub.arslan.checkoutsystem

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var image: String? = null,
    var name: String? = null,
    var price: String? = null
)