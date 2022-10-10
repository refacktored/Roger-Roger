package com.refacktored.rogerroger

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.refacktored.rogerroger.data.SearchResponse
import com.refacktored.rogerroger.network.MyApi
import com.refacktored.rogerroger.ui.theme.RogerRogerTheme
import timber.log.Timber

class MainActivity : ComponentActivity(), SearchListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO - fixme
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent {
            RogerRogerTheme {
                val response = MyApi().userSearch("kc8dvi")
                Timber.d("***response = ", response)

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting(response.blockingGet().name.toString())
                }
            }
        }
    }

    override fun onStarted() {
        //TODO("Not yet implemented")
        Timber.d("***onStarted***")
    }

    override fun onSuccess(searchResponse: SearchResponse) {
        //TODO("Not yet implemented")
        Timber.d("***onSuccess***")
    }

    override fun onFailure(message: String) {
        //TODO("Not yet implemented")
        Timber.d("***onFailure***")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RogerRogerTheme {
        Greeting("Refacktored")
    }
}