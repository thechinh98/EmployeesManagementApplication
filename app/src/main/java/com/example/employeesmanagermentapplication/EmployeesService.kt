package com.example.employeesmanagermentapplication

import com.example.employeesmanagermentapplication.Items.EmployeesReceive
import com.example.employeesmanagermentapplication.Items.EmployeesSend
import retrofit2.Call
import retrofit2.http.*

interface EmployeesService {
//    @Headers("Content-Type : application/json", "Accept : application/json")
    @GET("employees")
    fun getEmployees(): Call<ArrayList<EmployeesReceive>>

    @GET("employee/{id}")
    fun getEmployeesById(@Path("id") id: Int): Call<EmployeesReceive>

    @Headers("Content-Type: application/json")
    @POST("create")
    fun saveEmployees(@Body employees: EmployeesSend):Call<EmployeesSend>

    @DELETE("delete/{id}")
    fun deleteEmById(@Path("id") id:Int) :Call<EmployeesReceive>

    @PUT("update/{id}")
    fun updateEmById(@Path("id") id:Int,@Body employees: EmployeesSend) :Call<EmployeesSend>


}