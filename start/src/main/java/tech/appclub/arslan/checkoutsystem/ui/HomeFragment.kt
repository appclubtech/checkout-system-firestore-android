package tech.appclub.arslan.checkoutsystem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import tech.appclub.arslan.checkoutsystem.MainActivity
import tech.appclub.arslan.checkoutsystem.R
import tech.appclub.arslan.checkoutsystem.adapter.CartListAdapter
import tech.appclub.arslan.checkoutsystem.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


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

        (requireActivity() as MainActivity).cartViewModel.allItems.observe(
            requireActivity(), Observer {
                val adapter = CartListAdapter()
                adapter.setItems(it)
                this.binding.itemRecyclerView.adapter = adapter
            })
    }

}
