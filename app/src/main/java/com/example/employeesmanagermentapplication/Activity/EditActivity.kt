package com.example.employeesmanagermentapplication.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.Items.EmployeesReceive
import com.example.employeesmanagermentapplication.Items.EmployeesSend
import com.example.employeesmanagermentapplication.R
import com.example.employeesmanagermentapplication.ViewModel.EditViewModel
import kotlinx.android.synthetic.main.activity_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditActivity : AppCompatActivity() {
    private lateinit var editViewModel: EditViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initialized()

        dataBinding()


    }

    private fun dataBinding() {
        editViewModel.employeesDataReceive.observe(this, Observer {employees ->
            showEmployeesInfo(employees)
        })
    }

    private fun showEmployeesInfo(employees: EmployeesReceive?) {
        txt_name.setText(employees?.emName)
        txt_age.setText(employees?.age.toString())
        txt_salary.setText(employees?.salary.toString())
    }

    private fun initialized() {
        pBarHorizontal.visibility = View.GONE

        var tempId = intent.getIntExtra("TEMP_EM_ID", 0)

        editViewModel = ViewModelProviders.of(this).get((EditViewModel::class.java))
        editViewModel.getEmployeesById(tempId)

        btn_back.setOnClickListener {
            finish()
        }

        btn_save_edit.setOnClickListener{
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            var tempEmployees = getInfo(tempId)
                            editViewModel.updateEmployees(tempId, tempEmployees)
                            finish()
                        })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })
                    builder.setTitle("Update this employee's infomation")
                }

                builder.create()
            }
            alertDialog?.show()
        }
    }

    private fun getInfo(id : Int): EmployeesSend {
        var tempEmployees = EmployeesSend()
        tempEmployees.emName = txt_name.text.toString()
        tempEmployees.age = txt_age.text.toString().toInt()
        tempEmployees.salary = txt_salary.text.toString().toInt()
        tempEmployees.id = id
        return tempEmployees

    }
}
