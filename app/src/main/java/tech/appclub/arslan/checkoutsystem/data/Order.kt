package tech.appclub.arslan.checkoutsystem.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@IgnoreExtraProperties
data class Order(
    var cartTotal: Double? = null,
    var deliveryFee: Double? = 10.0,
    var customerName: String? = null,
    var contact: String? = null,
    var address: String? = null,
    var optionals: List<String>? = emptyList(),
    var grandTotal: Double? = null,
    @ServerTimestamp
    var orderPlacedOn: Timestamp? = null
)