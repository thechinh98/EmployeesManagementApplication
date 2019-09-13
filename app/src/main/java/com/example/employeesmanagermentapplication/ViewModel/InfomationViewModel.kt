package com.example.employeesmanagermentapplication.ViewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.Items.EmployeesReceive
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfomationViewModel(application: Application) : AndroidViewModel(application) {
    val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
    var employeesData = MutableLiveData<EmployeesReceive>()
    fun getEmployeesById(id :Int){
        employeesService.getEmployeesById(id).enqueue(object : Callback<EmployeesReceive> {
            override fun onFailure(call: Call<EmployeesReceive>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<EmployeesReceive>?, response: Response<EmployeesReceive>?) {
                if(response?.body() != null){
                    employeesData.value = response?.body()
                }
            }

        })
    }
    fun deleteEmployeesById(id:Int){
        employeesService.deleteEmById(id).enqueue(object : Callback<EmployeesReceive>{
            override fun onFailure(call: Call<EmployeesReceive>?, t: Throwable?) {
                Log.d("DELETED", "FAILED")
            }

            override fun onResponse(call: Call<EmployeesReceive>?, response: Response<EmployeesReceive>?) {
                Log.d("DELETED", "SUCCESS")
            }

        })
    }
}