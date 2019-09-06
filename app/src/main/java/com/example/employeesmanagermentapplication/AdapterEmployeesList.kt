package com.example.employeesmanagermentapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeesmanagermentapplication.Activity.MainActivity
import kotlinx.android.synthetic.main.items_employees_list.view.*

class AdapterEmployeesList() :
    RecyclerView.Adapter<AdapterEmployeesList.MyViewHolder>() {
    var myEmployeesList = ArrayList<Employees>()
    var callBack : MainActivity.ItemClickHandler? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_employees_list, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int = myEmployeesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initView(myEmployeesList[position], callBack!!)
    }

    class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        fun initView(itemsEmployees : Employees, callBack : MainActivity.ItemClickHandler){
            view.txt_id.text = itemsEmployees.id.toString()
            view.txt_name.text = itemsEmployees.emName
            view.txt_age.text = itemsEmployees.age.toString()
            view.txt_salary.text = itemsEmployees.salary.toString()
            view.setOnClickListener{
                callBack?.setOnItemClickHandler(itemsEmployees.id!!)
            }
        }
    }
    fun updateList(myEmList : ArrayList<Employees>){
        myEmployeesList = myEmList
        notifyDataSetChanged()
    }

}