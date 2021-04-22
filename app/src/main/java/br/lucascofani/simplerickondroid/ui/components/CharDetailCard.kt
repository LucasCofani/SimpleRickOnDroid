package br.lucascofani.simplerickondroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.lucascofani.simplerickondroid.models.character.Character
import com.google.accompanist.glide.GlideImage

@Composable
fun CharDetailCard(
    char: Character
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GlideImage(
            data = char.image,
            contentScale = ContentScale.Crop,
            contentDescription = char.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
            ) {
                Text(
                    text = char.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Episodes:",
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        items(char.episode) { c ->
                            EpisodeChip(
                                episodeNumber = c.replace(
                                    "https://rickandmortyapi.com/api/episode/",
                                    ""
                                )
                            )
                        }
                    }
                }
                Row(modifier = Modifier.padding(10.dp)) {
                    Column {
                        Text(text = "Gender:")
                        Text(text = "Species:")
                        Text(text = "Status:")
                        Text(text = "Origin:")
                        Text(text = "Location:")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 8.dp)
                    ) {
                        Text(text = char.gender, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = char.species, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = char.status, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(
                            text = char.origin.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = char.location.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}