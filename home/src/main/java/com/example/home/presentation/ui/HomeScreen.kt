package com.example.home.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.common.theme.Amber
import com.example.home.R
import com.example.home.presentation.DevicePreviewSettings
import com.example.home.util.debugComposable
import com.example.common.theme.DarkRed

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var itemToSearch by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = 1)
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .debugComposable("Scaffold Parent")
            .fillMaxSize(),
        bottomBar = {},
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .debugComposable("Scaffold Parent"),
        ) {
            GreetingsHeader()
            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                value = itemToSearch,
                onValueChange = {
                    itemToSearch = it
                },
                keyboardActions = KeyboardActions(
                    onSend = {}
                ),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    imeAction = ImeAction.Search
                ),
                shape = MaterialTheme.shapes.medium,
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .debugComposable("Search topbar"),
                placeholder = {
                    Text(
                        stringResource(R.string.search),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Categories", style = MaterialTheme.typography.titleLarge)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "See all",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.LightGray
                        ),
                    )
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        tint = Color.LightGray,
                        contentDescription = null
                    )
                }
            }
            LazyRow(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.debugComposable("LazyRowChips")
            ) {
                items(listOfCategories) {
                    Box(
                        modifier = Modifier
                            .debugComposable("MovieChipBox")
                            .padding(end = 8.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(8.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.film_reel),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(30.dp)
                            )
                            Text(it, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            Text(
                "Feature Movies",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxHeight()
                    .debugComposable("LazyRowFilm"),
                contentPadding = PaddingValues(16.dp),
                state = listState,
            ) {
                items(5) {
                    FilmItemCard()
                }
            }
        }
    }

}

@Composable
fun GreetingsHeader(
    modifier: Modifier = Modifier,
    name: String = "Elmoogy",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Red)
        ) {
            Image(
                painterResource(id = R.drawable.avatar),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Column(
            modifier = modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                stringResource(id = R.string.greeting, name),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                stringResource(R.string.find_your_favorite_movie),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Box(
            modifier = Modifier
                .clip(
                    MaterialTheme.shapes.medium
                )
                .background(MaterialTheme.colorScheme.surface)
                .padding(4.dp)
        ) {
            Icon(
                Icons.Outlined.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@DevicePreviewSettings.DevicePreview
@Composable
fun FilmItemCard(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.large)
                .width(300.dp)
                .height(450.dp)
        ) {
            Image(
                painterResource(id = R.drawable.poster),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.White.copy(alpha = 0.3f))
                            .graphicsLayer {
                                alpha = 0.7f // Adjusts transparency for glass effect
                                shadowElevation = 8.dp.toPx() // Adds a shadow for depth
                                shape = RoundedCornerShape(16.dp)
                                clip = true
                            }
                            .blur(radius = 16.dp)
                    ) {
                        Text(
                            "Action", style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp)
                        )
                    }
                    Text(
                        "Action", style = MaterialTheme.typography.bodySmall.copy(Color.White),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                    )
                }

                Box(
                    modifier = modifier
                        .clip(MaterialTheme.shapes.large)
                        .background(Amber)
                ) {
                    Text(
                        "IMDb 9.3", style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Box(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White.copy(alpha = 0.3f))
                        .graphicsLayer {
                            alpha = 0.7f // Adjusts transparency for glass effect
                            shadowElevation = 8.dp.toPx() // Adds a shadow for depth
                            shape = RoundedCornerShape(16.dp)
                            clip = true
                        }
                        .blur(radius = 16.dp)
                        .padding(16.dp)
                ) {
                    Text(
                        "Action", style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        Modifier
                            .padding(horizontal = 8.dp)
                            .weight(2f)
                    ) {
                        Text(
                            "Spiderman: No way home",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                        )
                        Text(
                            "2021",
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                        )
                    }

                    Text(
                        "1.1M",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                    )

                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .width(2.dp)
                            .height(20.dp),
                        color = Color.LightGray
                    )
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clip(CircleShape)
                            .background(DarkRed)
                    ) {
                        Icon(
                            Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}


val listOfCategories = listOf(
    "Action",
    "Comedy",
    "Drama",
    "Horror",
    "Science Fiction (Sci-Fi)",
    "Romance",
    "Thriller",
    "Fantasy",
    "Documentary",
    "Animation"
)