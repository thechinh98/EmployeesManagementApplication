package com.example.employeesmanagermentapplication.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesmanagermentapplication.ApiInstance
import com.example.employeesmanagermentapplication.Employees
import com.example.employeesmanagermentapplication.EmployeesService
import com.example.employeesmanagermentapplication.R
import kotlinx.android.synthetic.main.activity_infomation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfomationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var mainIntent = Intent(this, MainActivity::class.java)
        var editIntent = Intent(this, EditActivity::class.java)
        var comeIntent = intent
        var tempEmployId = intent.getIntExtra("TEMP_EM_ID", 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infomation)
        val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
        val employees = employeesService.getEmployeesById(tempEmployId)

        employees.enqueue(object : Callback<Employees> {
            override fun onFailure(call: Call<Employees>?, t: Throwable?) {
                Log.d("FAILED", t?.message.toString())
            }

            override fun onResponse(call: Call<Employees>?, response: Response<Employees>?) {
                if (response?.body() != null) {
                    txt_id.text = response.body().id.toString()
                    txt_name.text = response.body().emName
                    txt_age.text = response.body().age.toString()
                    txt_salary.text = response.body().salary.toString()
                }
            }

        })
        btn_save.setOnClickListener {
            var tempId = txt_id.text.toString().toInt()
            editIntent.putExtra("TEMP_EM_ID", tempId)
            startActivity(editIntent)
        }
        btn_delete.setOnClickListener {
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            var deleteEmployees = employeesService.deleteEmById(txt_id.text.toString().toInt())
                            deleteEmployees.enqueue(object : Callback<Employees> {
                                override fun onFailure(call: Call<Employees>?, t: Throwable?) {
                                    Log.d("FAILED", t?.message.toString())
                                }

                                override fun onResponse(call: Call<Employees>?, response: Response<Employees>?) {
                                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                                    startActivity(mainIntent)
                                }

                            })
                        })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })
                    builder.setTitle("Delete this employee's infomation")
                }

                builder.create()
            }
            alertDialog?.show()

        }
    }

}
