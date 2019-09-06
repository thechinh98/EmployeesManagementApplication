package com.example.employeesmanagermentapplication.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeesmanagermentapplication.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class MainActivity : AppCompatActivity() {
    private var viewAdapter = AdapterEmployeesList()
    override fun onCreate(savedInstanceState: Bundle?) {
        var hdlr :Handler? = null
        var progressStatus = 0
        var isStarted = false
        var regisIntent = Intent(this, RegistrationActivity::class.java)
        var infoIntent = Intent(this, InfomationActivity::class.java)
        var spacing = VerticalSpaceItemDecoration(14)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val employeesService = ApiInstance.getRetrofit().create(EmployeesService::class.java)
        val employees = employeesService.getEmployees()

        employees.enqueue(object : Callback<ArrayList<Employees>> {
            override fun onFailure(call: Call<ArrayList<Employees>>?, t: Throwable?) {
                Log.d("FAILED", t?.message.toString())

            }

            override fun onResponse(call: Call<ArrayList<Employees>>?, response: Response<ArrayList<Employees>>?) {
                if (response?.body() != null) {
                    viewAdapter.updateList(response.body())
                }
            }


        })
        rcc_em_list.adapter = viewAdapter
        rcc_em_list.addItemDecoration(spacing)
        rcc_em_list.layoutManager = LinearLayoutManager(this)
        fab.setOnClickListener { view ->
            startActivity(regisIntent)
        }
        viewAdapter.callBack = object : ItemClickHandler {
            override fun setOnItemClickHandler(tempEmployId: Int) {
                infoIntent.putExtra("TEMP_EM_ID", tempEmployId)
                startActivity(infoIntent)
            }

        }
    }

    class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = verticalSpaceHeight
        }
    }

    override fun onResume() {
        super.onResume()
        viewAdapter.notifyDataSetChanged()
    }
    interface ItemClickHandler {
        fun setOnItemClickHandler(tempEmployId: Int)
    }

}
