package com.example.employeesmanagermentapplication

import com.google.gson.annotations.SerializedName

class Employees{
    @SerializedName("id")
    var id : Int? = null
    @SerializedName("employee_name")
    var emName : String? = null
    @SerializedName("employee_salary")
    var salary : Int? = null
    @SerializedName("employee_age")
    var age : Int? = null
    var imageResource :Int? = null
    constructor(emName:String, salary:Int, age:Int){
        this.emName = emName
        this.salary = salary
        this.age = age
    }
}