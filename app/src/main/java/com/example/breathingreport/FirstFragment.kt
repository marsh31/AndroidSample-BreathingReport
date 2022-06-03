package com.example.breathingreport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.breathingreport.databinding.FragmentFirstBinding
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

        activity?.let { database = AppDatabase.getInstance(it.applicationContext) }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.testBtn.setOnClickListener {
            sampleCodeUsingDB()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sampleCodeUsingDB() {
        Log.d(TAG, "test button clicked")
        val dao = database.reportDao()

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                val pre_data = dao.getAll()
                dao.insertAll(Report(pre_data.size, "2022-05-30", 20 + pre_data.size))

                dao.getAll().forEach {
                    Log.d(TAG, "> ${it.uid}, ${it.date}, ${it.bold}")
                }
            }
        }
    }

}