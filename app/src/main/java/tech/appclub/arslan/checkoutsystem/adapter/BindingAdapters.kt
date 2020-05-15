package tech.appclub.arslan.checkoutsystem.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

@BindingAdapter("app:setPrice")
fun setPrice(view: TextView, amount: Double) {
    val decimalFormat = DecimalFormat()
    view.text = String.format("$ %s", decimalFormat.format(amount))
}