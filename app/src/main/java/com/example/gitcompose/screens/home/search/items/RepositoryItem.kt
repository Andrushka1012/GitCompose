package com.example.gitcompose.screens.home.search.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.gitcompose.R
import com.example.gitcompose.models.domainModels.RepositoryModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RepositoryItem(
    repository: RepositoryModel,
    onItemSelected: (repository: RepositoryModel) -> Unit
) {
    Card(
        onClick = { onItemSelected(repository) },
        elevation = 10.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .fillMaxSize(),
        ) {
            Image(
                rememberImagePainter(
                    data = repository.imageSrc,
                    builder = {
                        placeholder(R.drawable.ic_launcher_foreground)
                        scale(Scale.FIT)
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = "Picture",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(30.dp)
            )
            Box(modifier = Modifier.size(width = 12.dp, height = 1.dp))
            Column {
                Text(text = repository.name, fontSize = 20.sp)
                Text(text = repository.owner, fontSize = 16.sp)
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun RepositoryItemPreview() {
    RepositoryItem(
        repository = RepositoryModel(
            cloneUrl = "qwe",
            watchersCount = 100,
            createdAt = "123",
            forksCount = 20,
            id = 12,
            imageSrc = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg",
            license = "asd",
            name = "Dlash",
            openIssuesCount = 10,
            owner = "qwe",
            programmingLanguage = "QWE",
            starsCount = 12

        ), onItemSelected = {}
    )
}