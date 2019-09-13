package com.example.employeesmanagermentapplication.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.employeesmanagermentapplication.Items.EmployeesReceive
import com.example.employeesmanagermentapplication.R
import com.example.employeesmanagermentapplication.ViewModel.InfomationViewModel
import kotlinx.android.synthetic.main.activity_infomation.*
import retrofit2.Call

class InfomationActivity : AppCompatActivity() {

    lateinit var employees: Call<EmployeesReceive>
    private lateinit var infomationViewModel: InfomationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infomation)
        initialize()
        dataBinding()

    }

    private fun dataBinding() {
        infomationViewModel.employeesData.observe(this, Observer { employees ->
            showEmployeesInfo(employees)
        })
    }

    private fun showEmployeesInfo(employees: EmployeesReceive?) {
        txt_id.text = employees?.id.toString()
        txt_name.text = employees?.emName
        txt_age.text = employees?.age.toString()
        txt_salary.text = employees?.salary.toString()
    }

    private fun initialize() {
        infomationViewModel = ViewModelProviders.of(this).get(InfomationViewModel::class.java)

        var tempEmployId = intent.getIntExtra("TEMP_EM_ID", 0)
        infomationViewModel.getEmployeesById(tempEmployId)

        btn_save_edit.setOnClickListener {
            var tempId = txt_id.text.toString().toInt()
            var editIntent = Intent(this, EditActivity::class.java)
            editIntent.putExtra("TEMP_EM_ID", tempId)
            startActivity(editIntent)
        }

        btn_delete.setOnClickListener {
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            var mainIntent = Intent(context, MainActivity::class.java)
                            infomationViewModel.deleteEmployeesById(txt_id.text.toString().toInt())
                            startActivity(mainIntent)
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

        btn_back.setOnClickListener {
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }
}
