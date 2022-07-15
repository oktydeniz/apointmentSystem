package com.example.appointmentsystem.views.app


import androidx.app.Fragment
import androidx.fragment.app
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.appointmentsystem.R
import com.example.appointmentsystem.databinding.FragmentNewAppointmentBinding
import com.example.appointmentsystem.models.Doctor
import com.example.appointmentsystem.util.SharedPreferenceManager
import com.example.appointmentsystem.viewModel.NewAppointmentViewModel


class NewAppointmentFragment : Fragment() {

    private var _binding: FragmentNewAppointmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var manager:SharedPreferenceManager
    private lateinit var department: String
    private lateinit var currentDoctor: String
    private var currentDoctorNumber = "0"
    private lateinit var currentHour: String
    private var selectedAllDoctors = ArrayList<Doctor>()
    private lateinit var vModel:NewAppointmentViewModel
    private val hours: MutableList<Int> = mutableListOf(8,9,10,11,13,14,15,16,17)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setAdapters()
        observeData()
        actions()
    }

    private fun setAdapters() {
        val items = resources.getStringArray(R.array.departments)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, items)
        binding.autoCompleteNewAppointmentDepartments.setAdapter(adapter)


        binding.autoCompleteNewAppointmentDepartments.setOnItemClickListener { adapterView, _, i, _ ->
            department = adapterView.getItemAtPosition(i).toString()
            vModel.getDoctors(department)
        }


        val hoursAdapter = ArrayAdapter(requireContext(), R.layout.item_list, hours)
        binding.autoCompleteNewAppointmentHours.setAdapter(hoursAdapter)
        binding.autoCompleteNewAppointmentHours.setOnItemClickListener { adapterView, _, i, _ ->
            currentHour = adapterView.getItemAtPosition(i).toString()
        }

        binding.autoCompleteNewAppointmentDoctors.setOnItemClickListener { adapterView, _, i, _ ->
            currentDoctor = adapterView.getItemAtPosition(i).toString()
            selectedAllDoctors.forEach {
                if(currentDoctor==it.fullName){
                    currentDoctorNumber = it.doctorNumber
                }
            }
        }
    }
    private fun actions(){
        vModel.getAll()
        binding.saveNewAppointment.setOnClickListener { v->
            val currentUser =  manager.getUserNumber()
            currentUser.let {id->
                if(id != "0"){
                    var message = binding.editTextTextPersonMessage.text.toString()
                    if(message.isEmpty()){
                        message = "  "
                    }
                    val userName  = manager.getUserName()
                    userName?.let { it ->
                        vModel.saveNewAppointment(department,currentDoctorNumber,currentHour,id!!,
                            it,currentDoctor,message)
                    }
                }
            }
            val action = NewAppointmentFragmentDirections.actionNewAppointmentFragmentToHomeFragment()
            Navigation.findNavController(v).navigate(action)

        }
    }

    private fun init() {
       vModel = ViewModelProvider(this).get(NewAppointmentViewModel::class.java)
        manager = SharedPreferenceManager(requireContext())
    }
    private fun observeData(){
        vModel.doctors.observe(viewLifecycleOwner) {
            it.let { doctors ->
                selectedAllDoctors.addAll(doctors)
                val names = ArrayList<String>()
                doctors.forEach { dr ->
                    dr.fullName?.let { it1 -> names.add(it1) }
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.item_list, names)
                binding.autoCompleteNewAppointmentDoctors.setAdapter(adapter)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}