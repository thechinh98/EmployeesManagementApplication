package com.example.employeesmanagermentapplication

import android.database.Observable
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface EmployeesService {
    @GET("employees")
    fun getEmployees(): Call<ArrayList<Employees>>

    @GET("employee/{id}")
    fun getEmployeesById(@Path("id") id: Int): Call<Employees>

    @Headers("Content-Type: application/json")
    @POST("create")
    fun saveEmployeesById(@Body employees: JSONObject):Call<Employees>

    @DELETE("delete/{id}")
    fun deleteEmById(@Path("id") id:Int) :Call<Employees>

}