package com.jorge.paulo.mvi.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.jorge.paulo.mvi.domain.models.Animal
import com.jorge.paulo.mvi.presentation.MainIntent
import com.jorge.paulo.mvi.presentation.MainState
import com.jorge.paulo.mvi.presentation.MainViewModel
import com.jorge.paulo.mvi.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val coroutine = CoroutineScope(Dispatchers.IO)

    when (val state = viewModel.state.value) {
        is MainState.Animals -> FetchAnimalsScreen(state.animal)
        is MainState.Error -> ErrorScreen(state.error)
        MainState.Loading -> LoadingScreen()

        MainState.Idle -> IdleScreen {
            coroutine.launch {
                viewModel.userIntent.send(
                    MainIntent.FetchAnimals
                )
            }
        }

    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(Modifier.size(30.dp))
    }
}

@Composable
fun ErrorScreen(errorMessage: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error page: $errorMessage")
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FetchAnimalsScreen(animals: List<Animal>) {

    LazyColumn {
        items(animals) { animal ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Log.d("LOG", "${Constants.BASE_URL}/${animal.image}")
                Image(
                    painter = rememberImagePainter(data = "${Constants.BASE_URL}/${animal.image}"),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .size(50.dp)
                        .weight(1f)
                )
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = animal.name, fontSize = 19.sp)
                }

            }

        }
    }

}

@Composable
fun IdleScreen(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick =onClick) {
            Text(text = "Fetch Animals")
        }
    }
}
