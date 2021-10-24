package com.example.appointmentsystem.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.appointmentsystem.databinding.AppoinmentItemBinding
import com.example.appointmentsystem.models.Appointment
import com.example.appointmentsystem.room.AppointmentDatabase
import com.example.appointmentsystem.util.SharedPreferenceManager
import com.example.appointmentsystem.views.app.HomeFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentAdapter(val list: List<Appointment>): RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    private lateinit var context:Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppointmentAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val view = AppoinmentItemBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.view.appointment = item

        holder.view.doctorNameView.setOnClickListener {
            val id = list[position].doctor_id
            val dao = AppointmentDatabase.invoke(context).dao()
            CoroutineScope(Dispatchers.Main).launch {
                val doctor = dao.doctorProfile(id)
                val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(doctor)
                Navigation.findNavController(it).navigate(action)
            }

        }
        val manager = SharedPreferenceManager.invoke(context)
        if(manager.isDoctor() == true){
            holder.view.doctorNameView.isClickable = false
            holder.view.textView3.text = " "
            holder.view.doctorNameView.text = item.patientName

        }
        holder.view.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class ViewHolder(var view:AppoinmentItemBinding):RecyclerView.ViewHolder(view.root)
}