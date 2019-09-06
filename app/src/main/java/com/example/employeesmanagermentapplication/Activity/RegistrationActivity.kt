package com.example.employeesmanagermentapplication.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.Employees
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.R
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        var mainIntent = Intent(this, MainActivity::class.java)

        val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
        btn_save.setOnClickListener {
            if (txt_age.text.toString() != "" && txt_salary.text.toString() != "" && txt_name.toString() != "") {
                var tempName = txt_name.text.toString().trim()
                var tempAge = txt_age.text.toString().trim().toInt()
                var tempSalary = txt_salary.text.toString().trim().toInt()
                var string =
                    "{\"name\":\"" + tempName + "\",\"salary\":\"" + tempSalary + "\",\"age\":\"" + tempAge + "\"}"
                var jsonObject = JSONObject(string)
                var employees = employeesService.saveEmployeesById(JSONObject(Gson().toJson(Employees(tempName,tempSalary,tempAge))))
                employees.enqueue(object : retrofit2.Callback<Employees> {
                    override fun onFailure(call: Call<Employees>?, t: Throwable?) {
                        Log.d("FAILED", t?.message.toString())
                        Log.d("JSON", string)
                    }

                    override fun onResponse(call: Call<Employees>?, response: Response<Employees>?) {
                        startActivity(mainIntent)
                    }

                })
            } else {
                Toast.makeText(this, "Please input infomation", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getJsonByInfo(name: String, salary: Int, age: Int): String {
        var string = "{\"name\":\"" + name + "\"salary\":\"" + salary + "\"age\":\"" + age + "}"
        return string
    }
}

