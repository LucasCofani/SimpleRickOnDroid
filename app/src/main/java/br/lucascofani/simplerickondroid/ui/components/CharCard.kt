package br.lucascofani.simplerickondroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavOptionsBuilder
import br.lucascofani.simplerickondroid.models.character.Character
import br.lucascofani.simplerickondroid.navigation.Screen
import com.google.accompanist.glide.GlideImage

@Composable
fun CharCard(
    char: Character,
    navControllerNavigate: (String, NavOptionsBuilder.() -> Unit) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(140.dp)
            .clickable(onClick = {
                navControllerNavigate(Screen.CharDetail.route + "/${char.id}"){}
            }),
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 8.dp,
                modifier = Modifier
                    .width(140.dp)
                    .padding(10.dp),
            ) {
                GlideImage(
                    data = char.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = char.name,
                )
            }
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = char.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(modifier = Modifier.fillMaxSize()) {
                    Column {
                        Text(text = "Status: ", fontWeight = FontWeight.Bold)
                        Text(text = "Specie: ", fontWeight = FontWeight.Bold)
                        Text(text = "Gender: ", fontWeight = FontWeight.Bold)
                        Text(text = "Origin: ", fontWeight = FontWeight.Bold)
                    }
                    Column {
                        Text(text = char.status, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = char.species, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = char.gender, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(
                            text = char.origin.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}