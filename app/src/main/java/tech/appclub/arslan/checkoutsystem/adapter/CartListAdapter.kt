package tech.appclub.arslan.checkoutsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.appclub.arslan.checkoutsystem.data.Cart
import tech.appclub.arslan.checkoutsystem.databinding.CartItemListViewBinding

class CartListAdapter internal constructor() : RecyclerView.Adapter<CartListAdapter.ItemViewHolder>() {

    private var cartItems = emptyList<Cart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CartItemListViewBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    inner class ItemViewHolder internal constructor(private val binding: CartItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cart: Cart) {
            binding.cart = cart
            binding.executePendingBindings()
        }
    }

    internal fun setItems(items: List<Cart>) {
        this.cartItems = items
        notifyDataSetChanged()
    }
}