package com.refacktored.rogerroger

import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakewharton.threetenabp.AndroidThreeTen
import com.refacktored.rogerroger.data.SearchResponse
import com.refacktored.rogerroger.repositories.SearchRepository
import com.refacktored.rogerroger.ui.theme.RogerRogerTheme
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import timber.log.Timber

class MainActivity : ComponentActivity(), SearchListener {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        // TODO - use this time stuff to add a time field to the response once koin fixes the api trash
        try {
            val localTime: ZonedDateTime = ZonedDateTime.now(ZoneId.systemDefault())
            // Convert Local Time to UTC
            val utcTime: OffsetDateTime =
                localTime.toOffsetDateTime().withOffsetSameInstant(ZoneOffset.UTC)
            Toast.makeText(applicationContext,"Local:$localTime",Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext,"--> UTCTime:$utcTime",Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //TODO - fixme - this is actually garabage
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent {
            RogerRogerTheme {

                MainScreen(mainViewModel = mainViewModel)

                val searchRepo = SearchRepository()
                val response = searchRepo.userSearch("kc8tnt")
                // TODO - make search bar actually work and search callsigns api
                Surface(modifier = Modifier.padding(top = 56.dp), color = MaterialTheme.colors.background) {
                    val results = response.blockingGet()
                    Column {
                        TypeView(type = results.type.toString())
                        CallsignView(callsign = results.current?.callsign.toString())
                        ClassView(class_of_callsign = results.current?.operClass.toString())
                        NameAndAddressView(
                            name = results.name.toString(),
                            address_line_one = results.address?.line1.toString(),
                            address_line_two = results.address?.line2.toString(),
                            address_attn = results.address?.attn.toString()
                        )
                        LocationView(latitude = results.location?.latitude.toString(), longitude = results.location?.longitude.toString(), grid_square = results.location?.gridsquare.toString())
                        OtherLicenseInfoView(effective = results.otherInfo?.grantDate.toString(), expires = results.otherInfo?.expiryDate.toString(), last_action = results.otherInfo?.lastActionDate.toString(), frn = results.otherInfo?.frn.toString())
                        StatusView(status = results.status.toString())
                    }
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
fun StatusView(status: String) {
    Text(text = "Status: $status", modifier = Modifier.padding(8.dp))
}

@Composable
fun TypeView(type: String) {
    Text(text = "Type: $type", modifier = Modifier.padding(8.dp))
}

@Composable
fun CallsignView(callsign: String) {
    Text(text = "Callsign: $callsign", modifier = Modifier.padding(8.dp))
}

@Composable
fun ClassView(class_of_callsign: String) {
    Text(text = "Class: $class_of_callsign", modifier = Modifier.padding(8.dp))
}

@Composable
fun NameAndAddressView(name: String, address_line_one: String, address_line_two: String, address_attn: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "******NAME-AND-ADDRESS******")
        Text(text = "Name: $name", modifier = Modifier.padding(start = 8.dp))
        Text(text = "Address: $address_line_one", modifier = Modifier.padding(start = 8.dp))
        Text(text = "City/State: $address_line_two", modifier = Modifier.padding(start = 8.dp))
        Text(text = "Attn: $address_attn", modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun LocationView(latitude: String, longitude: String, grid_square: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "******LOCATION******")
        Text(text = "Latitude: $latitude", modifier = Modifier.padding(start = 8.dp))
        Text(text = "Longitude: $longitude", modifier = Modifier.padding(start = 8.dp))
        Text(text = "Grid Square: $grid_square", modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun OtherLicenseInfoView(effective: String, expires: String, last_action: String, frn: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "******OTHER-LICENSE-INFORMATION******")
        Text(text = "Effective: $effective", modifier = Modifier.padding(start = 8.dp))
        Text(text = "Expires: $expires", modifier = Modifier.padding(start = 8.dp))
        Text(text = "Last Action: $last_action", modifier = Modifier.padding(start = 8.dp))
        Text(text = "FRN: $frn", modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RogerRogerTheme {
        Column {
            TypeView(type = "global")
            CallsignView("kc8tnt")
            ClassView(class_of_callsign = "TECHNICIAN")
            NameAndAddressView("Refacktored", "220 Jefferson Ave", "Medina, Ohio", "ATTN SPOT")
            LocationView("41.065443", "-81.647012", "EN91eb")
            OtherLicenseInfoView("03/20/1945", "05/16/2028", "03/02/2016", "0003213212")
            StatusView(status = "invalid")
        }
    }
}