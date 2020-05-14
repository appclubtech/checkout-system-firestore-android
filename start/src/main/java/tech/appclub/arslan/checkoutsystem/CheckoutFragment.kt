package tech.appclub.arslan.checkoutsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tech.appclub.arslan.checkoutsystem.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding

    private val cartTotal: Double = 2950.0
    private val deliveryFee: Double = 10.0
    private val optionalCharges: Double = 5.0
    private var grandTotal = cartTotal + deliveryFee

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false)
        binding.checkout = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCheckboxListeners()
    }

    fun checkout() {

        val name = binding.nameValue.text.toString()
        if (validateEmpty(name, binding.nameInput)) return

        val contact = binding.contactValue.text.toString()
        if (validateEmpty(contact, binding.contactInput)) return

        val address = binding.addressValue.text.toString()
        if (validateEmpty(address, binding.addressInput)) return

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
