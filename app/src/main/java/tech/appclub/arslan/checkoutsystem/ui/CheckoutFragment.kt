package tech.appclub.arslan.checkoutsystem.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import tech.appclub.arslan.checkoutsystem.MainActivity.Companion.LOG_TAG
import tech.appclub.arslan.checkoutsystem.R
import tech.appclub.arslan.checkoutsystem.data.Order
import tech.appclub.arslan.checkoutsystem.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private lateinit var orderCollection: CollectionReference
    private lateinit var binding: FragmentCheckoutBinding

    private val cartTotal: Double = 2950.0
    private val deliveryFee: Double = 10.0
    private val optionalCharges: Double = 5.0
    private var grandTotal = cartTotal + deliveryFee

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_checkout, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firestore = FirebaseFirestore.getInstance()
        orderCollection = firestore.collection("Orders")
        setupCheckboxListeners()
        binding.placeOrderBtn.setOnClickListener { checkoutCart() }
    }

    private fun checkoutCart() {

        val name = binding.nameValue.text.toString()
        if (validateEmpty(name, binding.nameInput)) return

        val contact = binding.contactValue.text.toString()
        if (validateEmpty(contact, binding.contactInput)) return

        val address = binding.addressValue.text.toString()
        if (validateEmpty(address, binding.addressInput)) return

        val optionals: ArrayList<String> = arrayListOf()
        if (binding.optionFastDelivery.isChecked) {
            optionals.add(getString(R.string.fast_delivery))
        }
        if (binding.optionMutiplePackaging.isChecked) {
            optionals.add(getString(R.string.multiple_packaging))
        }
        if (binding.optionRecyclableBags.isChecked) {
            optionals.add(getString(R.string.recyclable_bags))
        }

        // Set order
        val order = Order(
            cartTotal,
            deliveryFee,
            name,
            contact,
            address,
            optionals,
            grandTotal,
            Timestamp.now()
        )
        val data = hashMapOf(
            "name" to "Tokyo",
            "country" to "Japan"
        )


        orderCollection.document(Timestamp.now().toString()).set(data).addOnCompleteListener { task ->
            if (task.isComplete && task.isSuccessful) {
                Log.d(LOG_TAG, "Task is successful and completed")
                Toast.makeText(requireContext(), "Order Placed!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Log.d(LOG_TAG, "Task failed")
                Toast.makeText(
                    requireContext(),
                    task.exception?.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.addOnFailureListener { exception ->
            Log.d(LOG_TAG, exception.localizedMessage, exception)
        }

    }

    private fun validateEmpty(value: String, inputLayout: TextInputLayout): Boolean {
        return if (value.isEmpty() || value.isBlank()) {
            inputLayout.error = getString(R.string.err_empty_field)
            true
        } else {
            inputLayout.error = null
            false
        }
    }

    private fun setupCheckboxListeners() {
        val listener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                grandTotal += optionalCharges
                binding.grandTotal.text = String.format("$%.1f", grandTotal)
            } else {
                grandTotal -= optionalCharges
                binding.grandTotal.text = String.format("$%.1f", grandTotal)
            }
        }

        binding.optionFastDelivery.setOnCheckedChangeListener(listener)
        binding.optionMutiplePackaging.setOnCheckedChangeListener(listener)
        binding.optionRecyclableBags.setOnCheckedChangeListener(listener)
    }
}
