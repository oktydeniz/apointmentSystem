package com.example.appointmentsystem.views.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.appointmentsystem.databinding.FragmentFirstChoiseBinding
import com.example.appointmentsystem.util.SharedPreferenceManager

class FirstChoiceFragment : Fragment() {

    private var _binding: FragmentFirstChoiseBinding? = null
    private val binding get() = _binding!!
    private lateinit var manager: SharedPreferenceManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstChoiseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manager = SharedPreferenceManager.invoke(requireContext())
        actions()
    }

    private fun actions() {
        binding.doctorButton.setOnClickListener {
            manager.setIsDoctor(true)
            val action = FirstChoiceFragmentDirections.actionFirstChoiceFragmentToSingUpFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.patientButton.setOnClickListener {
            manager.setIsDoctor(false)
            val action = FirstChoiceFragmentDirections.actionFirstChoiceFragmentToSingUpFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}