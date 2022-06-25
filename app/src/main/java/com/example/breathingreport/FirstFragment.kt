package com.example.breathingreport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breathingreport.databinding.FragmentFirstBinding
import com.example.breathingreport.ui.first.FirstItemModel
import com.example.breathingreport.ui.first.FirstViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val TAG = "FirstFragment"
    private var _binding: FragmentFirstBinding? = null
    private var recyclerView: RecyclerView? = null

    companion object {
        lateinit var database: AppDatabase
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.recyclerView = view.findViewById(R.id.first_recycler_view)
        this.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = FirstViewAdapter(
                generateItemList(),
                object : FirstViewAdapter.ListListener {
                    override fun onClickItem(tappedView: View, itemModel: FirstItemModel) {
                        this@FirstFragment.onClickItem(tappedView, itemModel)
                    }
                }
            )
        }

        activity?.let { database = AppDatabase.getInstance(it.applicationContext) }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        this.recyclerView?.adapter = null
        this.recyclerView = null
    }


    private fun generateItemList(): List<FirstItemModel> {
        val itemList = mutableListOf<FirstItemModel>()
        for (i in 0..100) {
            val item: FirstItemModel = FirstItemModel().apply {
                text = "Item ${i}"
            }

            itemList.add(item)
        }

        return itemList
    }

    private fun onClickItem(tappedView: View, itemModel: FirstItemModel) {

    }
}