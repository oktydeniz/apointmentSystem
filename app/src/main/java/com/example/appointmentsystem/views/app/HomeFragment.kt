package com.example.appointmentsystem.views.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.appointmentsystem.adapters.AppointmentAdapter
import com.example.appointmentsystem.databinding.FragmentHomeBinding
import com.example.appointmentsystem.util.SharedPreferenceManager
import com.example.appointmentsystem.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var vModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AppointmentAdapter
    private lateinit var manager: SharedPreferenceManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        manager = SharedPreferenceManager.invoke(requireContext())
        val id = manager.getUserNumber()
        vModel.getAppointments(id!!)

        observeData()
        actions()

    }

    private fun observeData() {
        vModel.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                binding.progressBar2.visibility = View.GONE
            } else {
                binding.progressBar2.visibility = View.GONE
            }
        })
        vModel.isSuccess.observe(viewLifecycleOwner, {
            if (it) {
                binding.isError.visibility = View.GONE
            } else {
                binding.isError.visibility = View.GONE
            }
        })
        vModel.mutableLiveData.observe(viewLifecycleOwner, {
            adapter = AppointmentAdapter(it)
            binding.recyclerView.adapter = adapter

        })
    }

    private fun actions() {
        binding.addNewAppointmentBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNewAppointmentFragment()
            Navigation.findNavController(it).navigate(action)
        }

        if (manager.isDoctor() == true) {
            binding.addNewAppointmentBtn.visibility = View.GONE
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}