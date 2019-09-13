package com.example.employeesmanagermentapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.Items.EmployeesReceive
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) :AndroidViewModel(application) {
    val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
    var allEmployees = MutableLiveData<ArrayList<EmployeesReceive>>()


    fun getAll(){
        employeesService.getEmployees().enqueue(object : Callback<ArrayList<EmployeesReceive>>{
            override fun onFailure(call: Call<ArrayList<EmployeesReceive>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<ArrayList<EmployeesReceive>>?,
                response: Response<ArrayList<EmployeesReceive>>?
            ) {
                if(response?.body() != null){
                    allEmployees.value = response?.body()
                }
            }

        })
    }

}