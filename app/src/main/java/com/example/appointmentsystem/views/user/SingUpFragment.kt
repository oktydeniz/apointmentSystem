package com.example.appointmentsystem.views.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.appointmentsystem.R
import com.example.appointmentsystem.databinding.FragmentSingUpBinding
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.models.User
import com.example.appointmentsystem.util.InfoDialogs
import com.example.appointmentsystem.util.SharedPreferenceManager
import com.example.appointmentsystem.viewModel.SingUpViewModel

class SingUpFragment : Fragment() {

    private var _binding: FragmentSingUpBinding? = null
    private val binding get() = _binding!!
    private var isDoctor = false
    private lateinit var manager: SharedPreferenceManager
    private lateinit var department: String
    private lateinit var gender: String
    private lateinit var vModel: SingUpViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        manager = SharedPreferenceManager.invoke(requireContext())
        manager.isDoctor()?.let {
            isDoctor = it
        }
        department = " "
        viewSettings()
        actions()
    }

    private fun viewSettings() {
        if (isDoctor) {
            binding.textInputSingUpDepartments.visibility = View.VISIBLE
            val items = resources.getStringArray(R.array.departments)
            val adapter = ArrayAdapter(requireContext(), R.layout.item_list, items)
            binding.autoCompleteDepartments.setAdapter(adapter)
        } else {
            binding.textInputSingUpDepartments.visibility = View.GONE
        }
        val gender = resources.getStringArray(R.array.genders)
        val adapterGender = ArrayAdapter(requireContext(), R.layout.item_list, gender)
        binding.autoCompleteGender.setAdapter(adapterGender)

    }

    private fun actions() {
        binding.singUP.setOnClickListener {
            val name = binding.editTextPersonFullName.text.toString()
            val email = binding.editTextPersonEmail.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val number = binding.editTextPersonCountryNumber.text.toString()
            val phone = binding.editTextPersonPhone.text.toString()
            if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && number.isNotEmpty()
                && phone.isNotEmpty() && gender.isNotEmpty() && department.isNotEmpty()){
                saveData(name, email, phone, gender, department, password, number)

            }else{
                InfoDialogs.showInputError(requireContext())
            }
        }
        binding.autoCompleteDepartments.setOnItemClickListener { adapterView, _, i, _ ->
            department = adapterView.getItemAtPosition(i).toString()
        }
        binding.autoCompleteGender.setOnItemClickListener { adapterView, _, i, _ ->
            gender = adapterView.getItemAtPosition(i).toString()
        }


    }

    private fun saveData(
        fullName: String,
        email: String,
        phone: String,
        gender: String,
        department: String,
        password: String,
        number: String
    ) {
        if (isDoctor) {
            val doctor = Doctor()
            doctor.doctorNumber = number
            doctor.password = password
            doctor.gender = gender
            doctor.department = department
            doctor.phone = phone
            doctor.fullName = fullName
            doctor.email = email
            manager.setUserName(doctor.fullName!!)
            vModel.insertDoctor(doctor)
        } else if (!isDoctor) {
            val user = User()
            user.userNumber = number
            user.password = password
            user.gender = gender
            user.phone = phone
            user.fullName = fullName
            user.email = email
            manager.setUserName(user.fullName!!)
            vModel.insertUser(user)
        }

        //TODO::Loading
        val controller = NavHostFragment.findNavController(this)
        controller.navigate(R.id.action_singUpFragment_to_singInFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}