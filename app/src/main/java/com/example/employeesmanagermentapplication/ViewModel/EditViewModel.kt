package com.example.employeesmanagermentapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.Items.EmployeesReceive
import com.example.employeesmanagermentapplication.Items.EmployeesSend
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditViewModel(application: Application) : AndroidViewModel(application) {
    val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
    var employeesDataReceive = MutableLiveData<EmployeesReceive>()

    fun getEmployeesById(id: Int) {
        employeesService.getEmployeesById(id).enqueue(object : Callback<EmployeesReceive> {
            override fun onFailure(call: Call<EmployeesReceive>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<EmployeesReceive>?, response: Response<EmployeesReceive>?) {
                if (response?.body() != null) {
                    employeesDataReceive.value = response?.body()
                }
            }

        })
    }

    fun updateEmployees(id: Int, employees: EmployeesSend) {
        employeesService.updateEmById(id, employees).enqueue(object : Callback<EmployeesSend> {
            override fun onFailure(call: Call<EmployeesSend>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<EmployeesSend>?, response: Response<EmployeesSend>?) {

            }
        })
    }

}