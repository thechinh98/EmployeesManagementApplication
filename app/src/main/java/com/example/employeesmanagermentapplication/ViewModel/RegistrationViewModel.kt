package com.example.employeesmanagermentapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.Items.EmployeesSend
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {
    val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
    fun saveEmployeesById(employees : EmployeesSend){
        employeesService.saveEmployees(employees).enqueue(object : retrofit2.Callback<EmployeesSend>{
            override fun onFailure(call: Call<EmployeesSend>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<EmployeesSend>?, response: Response<EmployeesSend>?) {

            }

        })
    }
}