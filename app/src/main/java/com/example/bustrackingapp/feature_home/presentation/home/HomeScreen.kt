package com.example.bustrackingapp.feature_home.presentation.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bustrackingapp.core.presentation.components.CustomLoadingIndicator
import com.example.bustrackingapp.core.presentation.components.CustomOutlinedButton
import com.example.bustrackingapp.core.presentation.components.RefreshContainer
import com.example.bustrackingapp.feature_bus.domain.models.BusWithRoute
import com.example.bustrackingapp.feature_bus_stop.domain.model.BusStopWithRoutes
import com.example.bustrackingapp.feature_bus_stop.presentation.components.BusStopTile
import com.example.bustrackingapp.feature_home.presentation.components.BusTile
import com.example.bustrackingapp.ui.theme.Blue500
import com.example.bustrackingapp.ui.theme.NavyBlue300
import com.example.bustrackingapp.ui.theme.Red400
import com.example.bustrackingapp.ui.theme.White
import com.google.accompanist.swiperefresh.SwipeRefresh
import androidx.compose.material3.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel : HomeViewModel = hiltViewModel(),
    snackbarState : SnackbarHostState = remember {
        SnackbarHostState()
    },
    onNearbyBusClick : (String)->Unit,
    onNearbyBusStopClick : (String)->Unit,
    onAllBusStopsClick : ()->Unit
){

    LaunchedEffect(
        key1 = homeViewModel.uiState.errorNearbyBuses,
        key2 = homeViewModel.uiState.nearbyBusStops,
        key3 = homeViewModel.uiState.errorLocation
    ){
        Log.d("BTLogger","showSnackbar")

        if(homeViewModel.uiState.errorLocation!=null){
            snackbarState.showSnackbar(homeViewModel.uiState.errorLocation!!)
        }else if(homeViewModel.uiState.errorNearbyBuses!=null){
            snackbarState.showSnackbar(homeViewModel.uiState.errorNearbyBuses!!)
        }else if(homeViewModel.uiState.errorNearbyStops!=null){
            snackbarState.showSnackbar(homeViewModel.uiState.errorNearbyStops!!)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Vidya-Vahini",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        },

        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
            ){
                if(homeViewModel.uiState.errorNearbyBuses!=null ||
                    homeViewModel.uiState.errorNearbyStops!=null){

                    Snackbar(
                        snackbarData = it,
                        containerColor = Red400,
                        contentColor = White,
                    )
                }else{
                    Snackbar(snackbarData = it)
                }
            }
        }

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            Column {
                Text(
                    text = "Welcome, Student 👋",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Vidya-Vahini Student Transport Tracker",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Select Your Route",
                    style = MaterialTheme.typography.headlineMedium
                )

                Button(onClick = { }) {
                    Text("Kolar → Bangalore")
                }

                Button(onClick = { }) {
                    Text("Malur → College")
                }

                Button(onClick = { }) {
                    Text("Chintamani → College")
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "🚌 Live Bus Status",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Bus Last Seen: Near Railway Bridge"
                        )

                        Text(
                            text = "Estimated Arrival: 12 Minutes"
                        )

                        Text(
                            text = "Status: On Time ✅"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(onClick = { }) {
                    Text("⚠ Report Breakdown")
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = { }
                ) {
                    Text("✅ Reached College Safely")
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    "Nearby Buses",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Text(
                    text = "Nearby Buses Coming Soon",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "Nearby Bus Stops",
                        style = MaterialTheme.typography.titleSmall
                    )

                    Text(
                        "All Bus Stops",
                        style = MaterialTheme.typography.labelMedium,
                        color = Blue500,
                        modifier = Modifier
                            .clickable {
                                onAllBusStopsClick()
                            }
                            .padding(
                                start = 4.dp,
                                top = 4.dp,
                                end = 4.dp,
                                bottom = 2.dp,
                            )
                    )
                }

                Text(
                    text = "Nearby Bus Stops Coming Soon",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}