package com.example.employeesmanagermentapplication.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.Employees
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.R
import kotlinx.android.synthetic.main.activity_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var comeIntent = intent
        var tempId = intent.getIntExtra("TEMP_EM_ID", 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
        val employees = employeesService.getEmployeesById(tempId)

        employees.enqueue(object : Callback<Employees> {
            override fun onFailure(call: Call<Employees>?, t: Throwable?) {
                Log.d("FAILED", t?.message.toString())
            }

            override fun onResponse(call: Call<Employees>?, response: Response<Employees>?) {
                if (response?.body() != null) {
                    txt_id.setText(response.body().id.toString())
                    txt_name.setText(response.body().emName)
                    txt_age.setText(response.body().age.toString())
                    txt_salary.setText(response.body().salary.toString())
                }
            }

        })
    }
}
