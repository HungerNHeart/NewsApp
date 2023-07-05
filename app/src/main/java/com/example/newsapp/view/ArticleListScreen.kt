package com.example.newsapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.AppConstant
import com.example.newsapp.AppConstant.IMAGE_BASE_URL
import com.example.newsapp.R
import com.example.newsapp.domain.Article
import com.example.newsapp.domain.MultiMedia
import com.example.newsapp.ui.theme.NEWSAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(modifier : Modifier = Modifier, list : LazyPagingItems<Article>) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New York Times")
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyListState(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(
                    count = list.itemCount,
                    key = list.itemKey {
                        it.id
                    }
                ){
                    val item = list[it]
                    item?.let {_item->
                        ArticleListItem(
                            modifier = Modifier.fillMaxWidth(),
                            article = _item
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ArticleListItemPreview() {
    NEWSAppTheme {
        ArticleListItem(
            modifier = Modifier
                .fillMaxWidth(),
            article = Article(
                id = "",
                snippet = "A Milan-inspired cafe comes to NoMad, Dan Kluger opens a bakery in Hudson Yards and more restaurant news.",
                wordCount = 350,
                webUrl = "https://www.nytimes.com/2023/07/03/dining/nyc-restaurant-news-openings.html",
                byLine = "By Florence Fabricant",
                headLine = "Adrienne’s, Serving Tastes of Southern Italy, Opens in the Rockaways",
                kicker = "Off the Menu",
                publishedDate = "29 May",
                multiMedia = arrayListOf(
                    MultiMedia(
                        type = "xlarge",
                        height = 400,
                        width = 600,
                        url = "images/2023/07/05/multimedia/04OFF-wpqk/04OFF-wpqk-articleLarge.jpg"
                    )
                )
            )
        )
    }
}

@Composable
fun ArticleListItem(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Column {
                    Text(text = article.headLine.orEmpty(), style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = article.snippet, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Justify)
                }
                /*if(article.multiMedia.orEmpty().isEmpty()){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(IMAGE_BASE_URL+article.multiMedia.orEmpty().first().url)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RectangleShape)
                    )
                }*/
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = article.byLine.orEmpty(), style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray), fontStyle = FontStyle.Italic)
                Row{
                    Text(text = article.publishedDate, style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray))
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "\u2022",
                        style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = stringResource(
                            R.string.read_in_min,
                            article.wordCount / AppConstant.AVERAGE_WORD_READING_PER_MINUTE
                        ), style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray)
                    )
                }
            }

        }
    }
}