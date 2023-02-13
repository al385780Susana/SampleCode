package com.al385780.sampletest

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class Network private constructor(context: Context){
    companion object : SingletonHolder<Network, Context>(::Network)

    private val queue = Volley.newRequestQueue(context)

    private val FIRST_NAME_LABEL = "First Name"
    private val LAST_NAME_LABEL = "Last Name"
    private val FULL_NAME_LABEL = FIRST_NAME_LABEL + LAST_NAME_LABEL
    private val ID_LABEL = "id"
    private val STREET_LABEL = "Street"
    private val JOB_LABEL = "Job"
    private val LAST_CHECKED_LABEL = "Last Check-In Date"
    private val LIST_LABEL = "Data"

    fun getCustomersName(listener: Response.Listener<List<CustomerName>>, errorListener: Response.ErrorListener) {
        val url = "https://19h0vwjnce.execute-api.eu-west-3.amazonaws.com/MobileInternshipAPI/data"
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processCustomerName(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)

    }
    private fun processCustomerName(response: JSONObject, listener: Response.Listener<List<CustomerName>>, errorListener: Response.ErrorListener) {
        val customerNames = ArrayList<CustomerName>()
        try {
            val nameArray = response.getJSONArray(LIST_LABEL)
            for (i in 0 until nameArray.length()) {
                val namesObject = nameArray[i] as JSONObject
                val name = namesObject.getString(FULL_NAME_LABEL)
                customerNames.add(CustomerName(name))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        customerNames.sortBy { it.name }
        listener.onResponse(customerNames)
    }

    fun getJobs(listener: Response.Listener<List<Jobs>>, errorListener: Response.ErrorListener) {
        val url = "https://19h0vwjnce.execute-api.eu-west-3.amazonaws.com/MobileInternshipAPI/data"
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processJobs(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)

    }
    private fun processJobs(response: JSONObject, listener: Response.Listener<List<Jobs>>, errorListener: Response.ErrorListener) {
        val customerJobs = ArrayList<Jobs>()
        try {
            val jobsArray = response.getJSONArray(LIST_LABEL)
            for (i in 0 until jobsArray.length()) {
                val jobsObject = jobsArray[i] as JSONObject
                val jobs = jobsObject.getString(JOB_LABEL)
                customerJobs.add(Jobs(jobs))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        customerJobs.sortBy { it.name }
        listener.onResponse(customerJobs)
    }

    fun getCustomersCheckDate(listener: Response.Listener<List<BundleCustomerDate>>, errorListener: Response.ErrorListener) {
        val url = "https://19h0vwjnce.execute-api.eu-west-3.amazonaws.com/MobileInternshipAPI/data"
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response -> processCustomerCheckDate(response, listener, errorListener) },
            { error -> errorListener.onErrorResponse(error) }
        )
        queue.add(request)

    }

    private fun processCustomerCheckDate(response: JSONObject, listener: Response.Listener<List<BundleCustomerDate>>, errorListener: Response.ErrorListener) {
        val customerCheckDate = ArrayList<BundleCustomerDate>()
        try {
            val dateArray = response.getJSONArray(LIST_LABEL)
            for (i in 0 until dateArray.length()) {
                val dateObject = dateArray[i] as JSONObject
                val date = dateObject.getString(LAST_CHECKED_LABEL)
                val name = dateObject.getString(FULL_NAME_LABEL)
                customerCheckDate.add(BundleCustomerDate(i,name,date))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        customerCheckDate.sortBy { it.date }
        listener.onResponse(customerCheckDate)
    }

}

