package tech.appclub.arslan.checkoutsystem.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tech.appclub.arslan.checkoutsystem.R
import tech.appclub.arslan.checkoutsystem.databinding.FragmentHomeBinding
import tech.appclub.arslan.checkoutsystem.viewModel.CartViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        cartViewModel.allItems.observe(requireActivity(), Observer {

        })

    }

}
