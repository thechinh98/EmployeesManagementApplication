package com.example.employeesmanagermentapplication.Items

import com.google.gson.annotations.SerializedName

class EmployeesSend {
    @SerializedName("id")
    var id : Int? = null
    @SerializedName("name")
    var emName : String? = null
    @SerializedName("salary")
    var salary : Int? = null
    @SerializedName("age")
    var age : Int? = null
    var imageResource :Int? = null
    constructor(emName:String, salary:Int, age:Int){
        this.emName = emName
        this.salary = salary
        this.age = age
    }
    constructor()
}