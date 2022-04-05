package com.example.spotifymusicrecommendation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spotifymusicrecommendation.ui.theme.SpotifyMusicRecommendationTheme
import com.example.spotifymusicrecommendation.viewmodel.MusicViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = MusicViewModel()
        super.onCreate(savedInstanceState)

        setContent {
            SpotifyMusicRecommendationTheme {
                MusicView(vm = vm)
            }
        }
    }

    @Composable
    fun MusicView(vm: MusicViewModel) {
        LaunchedEffect(Unit, block = {
            vm.getMusicList()
        })

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row {
                            Text("Music List")
                        }
                    })
            },
            content = {
                if (vm.errorMessage.isEmpty()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        LazyColumn(modifier = Modifier.fillMaxHeight()) {
                            items(vm.musicList) { music ->
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                        ) {
                                            Text(
                                                music.name,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                                Divider()
                            }
                        }
                    }
                } else {
                    Text(vm.errorMessage)
                }
            }
        )
    }
}
