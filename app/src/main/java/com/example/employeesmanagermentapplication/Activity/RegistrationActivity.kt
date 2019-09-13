package com.example.employeesmanagermentapplication.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.employeesmanagermentapplication.Items.EmployeesSend
import com.example.employeesmanagermentapplication.R
import com.example.employeesmanagermentapplication.ViewModel.RegistrationViewModel
import kotlinx.android.synthetic.main.activity_registration.*


class RegistrationActivity : AppCompatActivity() {
    lateinit var registrationViewModel: RegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initialize()

    }

    private fun initialize() {
        registrationViewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
        btn_save_edit.setOnClickListener {
            if (txt_age.text.toString() != "" && txt_salary.text.toString() != "" && txt_name.toString() != "") {
                var employeeSend = EmployeesSend()
                employeeSend.emName = txt_name.text.toString().trim()
                employeeSend.age = txt_age.text.toString().trim().toInt()
                employeeSend.salary = txt_salary.text.toString().trim().toInt()
                registrationViewModel.saveEmployeesById(employeeSend)

                var mainIntent = Intent(this,MainActivity::class.java)
                startActivity(mainIntent)
            } else {
                Toast.makeText(this, "Please input right infomation", Toast.LENGTH_SHORT).show()
            }

        }

    }
}

