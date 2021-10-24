package com.example.appointmentsystem.views.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.appointmentsystem.R
import com.example.appointmentsystem.databinding.FragmentSingInBinding
import com.example.appointmentsystem.fakeData.DoctorDataClass
import com.example.appointmentsystem.fakeData.UserDataClass
import com.example.appointmentsystem.util.InfoDialogs
import com.example.appointmentsystem.util.SharedPreferenceManager
import com.example.appointmentsystem.viewModel.SingInVIewModel
import com.example.appointmentsystem.views.app.AppActivity

class SingInFragment : Fragment() {

    private var _binding: FragmentSingInBinding? = null
    private val binding get() = _binding!!
    private lateinit var manager: SharedPreferenceManager
    private lateinit var vModel: SingInVIewModel
    private var isDoctor: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manager = SharedPreferenceManager(requireContext())
        vModel = ViewModelProvider(this).get(SingInVIewModel::class.java)

        val doctors = DoctorDataClass.addDoctors(requireContext())
        val users = UserDataClass.addUsers(requireContext())
        val isFirst = manager.isFirstTime()
        if (isFirst!!) {
            vModel.getData(doctors, users)
            manager.setFirstTime(false)

        }
        actions()
        binding.personType.isChecked = true
        isDoctor = false
    }

    private fun actions() {
        binding.singInBtn.setOnClickListener {
            val number = binding.editTextTextPersonNumber.text.toString()
            val password = binding.editTextTextPersonPassword.text.toString()
            if (number.isNotEmpty() && password.isNotEmpty()) {
                vModel.getCurrentUser(isDoctor, number, password)
                vModel.status.observe(viewLifecycleOwner, {
                    if (it) {
                       //TODO::Loading
                        val intent = Intent(requireContext(), AppActivity::class.java)
                        requireActivity().startActivity(intent)
                        requireActivity().finish()
                    }
                })
            }else{
                InfoDialogs.showInputError(requireContext())
            }
        }
        binding.iNotMemberText.setOnClickListener {
            val action = SingInFragmentDirections.actionSingInFragmentToFirstChoiceFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.radioGroup.setOnCheckedChangeListener { _, i ->
            kotlin.run {
                when (i) {
                    R.id.doctorType -> {
                        isDoctor = true
                    }
                    R.id.personType -> {
                        isDoctor = false
                    }
                }
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}