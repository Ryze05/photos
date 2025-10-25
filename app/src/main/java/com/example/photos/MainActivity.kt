package com.example.photos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.photos.ui.theme.PhotosTheme
import androidx.compose.foundation.lazy.items


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Photo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val photos = listOf(
    R.drawable.image1,
    R.drawable.image2,
    R.drawable.image3,
    R.drawable.image4,
    R.drawable.image5,
    R.drawable.image6,
    R.drawable.image7,
    R.drawable.image8
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Photo(modifier: Modifier = Modifier) {
    var fotoSeleccionada by remember { mutableStateOf<Int?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyRow(
                contentPadding = PaddingValues(vertical = 20.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(photos) { photo ->
                    Image(
                        painter = painterResource(id = photo),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clickable { fotoSeleccionada = photo }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (fotoSeleccionada != null) {
                    Image(
                        painter = painterResource(id = fotoSeleccionada!!),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(300.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhotosTheme {
        Photo()
    }
}