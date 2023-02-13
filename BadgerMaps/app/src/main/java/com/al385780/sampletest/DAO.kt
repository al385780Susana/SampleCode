package com.al385780.sampletest


import androidx.room.*

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNames(names: List<CustomerName>)

    @Query("SELECT * FROM CustomerName ORDER BY CustomerName.name")
    fun getCustomersName(): List<CustomerName>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertJobs(jobs: List<Jobs>)

    @Query("SELECT * FROM jobs ORDER BY jobs.name")
    fun getJobs(): List<Jobs>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDates(checkDates: List<CustomerCheckDate>)

    @Query("SELECT * FROM CustomerCheckDate ORDER BY CustomerCheckDate.date")
    fun getCustomersCheckDate(): List<CustomerCheckDate>


    @Query("SELECT UPPER(BundleCustomerDate.name ) FROM BundleCustomerDate ORDER BY BundleCustomerDate.date ASC")
    fun getCustomerByLastChecked(date: String): List<BundleCustomerDate>

    @Query("SELECT UPPER(BundleCustomerDate.name ) FROM BundleCustomerDate ORDER BY BundleCustomerDate.name DESC")
    fun getCustomerByFirstChecked(date: String): List<BundleCustomerDate>
}