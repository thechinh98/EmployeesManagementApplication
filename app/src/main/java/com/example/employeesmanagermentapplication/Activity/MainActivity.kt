package com.example.employeesmanagermentapplication.Activity

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeesmanagermentapplication.AdapterEmployeesList
import com.example.employeesmanagermentapplication.Items.EmployeesReceive
import com.example.employeesmanagermentapplication.R
import com.example.employeesmanagermentapplication.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var viewAdapter = AdapterEmployeesList(ArrayList<EmployeesReceive>())
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialized()
        bindingData()
        pBar.visibility = View.GONE
    }

    private fun bindingData() {
        mainViewModel.allEmployees.observe(this, Observer { employeesDataList ->
            viewAdapter.updateList(employeesDataList)
        })
    }

    private fun initialized() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getAll()
        var regisIntent = Intent(this, RegistrationActivity::class.java)
        var infoIntent = Intent(this, InfomationActivity::class.java)
        var spacing = VerticalSpaceItemDecoration(14)

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
