package com.example.try1.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

import com.example.try1.Retrofit.Data.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DashboardViewModel (application: Application) : AndroidViewModel(application) {
    val contentList: MutableLiveData<List<Content>> = MutableLiveData()

    fun fetchContents() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val url =
                    URL("https://app-xycy6mh7wa-et.a.run.app/api/v1/contents")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = StringBuilder()
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()
                    Log.d("API Response", response.toString()) // Log the response

                    val jsonResponse = JSONObject(response.toString())
                    val dataArray = jsonResponse.getJSONArray("data")

                    val contentItems = mutableListOf<Content>()
                    for (i in 0 until dataArray.length()) {
                        val dataObject = dataArray.getJSONObject(i)
                        val title = dataObject.getString("title")
                        val shortDescription = dataObject.getString("short_description")
                        val viewDescription = dataObject.getString("description")

                        val content = Content(title, shortDescription, viewDescription)
                        contentItems.add(content)
                    }
                    contentList.postValue(contentItems)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

}