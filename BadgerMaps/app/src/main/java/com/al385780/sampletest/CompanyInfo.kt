package com.al385780.sampletest

import java.util.Date

data class CompanyInfo(
    val firstName: String,
    val lastName: String,
    val street: String,
    val zip: String,
    val city: String,
    val type: String,
    val job: String,
    val phone: Int,
    val lastCheckInDate: Date,
    val companyCheck: String)
