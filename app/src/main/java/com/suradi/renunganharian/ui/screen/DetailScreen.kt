package com.suradi.renunganharian.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suradi.renunganharian.model.Devotional
import com.suradi.renunganharian.ui.theme.LoraFont
import com.suradi.renunganharian.ui.theme.RenunganharianTheme
import com.suradi.renunganharian.ui.theme.StyleScript
import com.suradi.renunganharian.viewmodel.FavoriteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suradi.renunganharian.utils.formatDevotionalDate
import com.suradi.renunganharian.utils.getMonthName
import com.suradi.renunganharian.utils.getTitleColor
import com.suradi.renunganharian.viewmodel.DetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    devotionalId: Int,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    favoriteViewModel: FavoriteViewModel
) {
    val detailViewModel: DetailViewModel = viewModel()
    val devotionalState = detailViewModel.devotional.collectAsState()

    LaunchedEffect(devotionalId) {
        detailViewModel.fetchDevotional(devotionalId)
    }

    val devotional = devotionalState.value

    if (devotional == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally)
            )
        }
        return
    }

    val isFavorite = favoriteViewModel.isFavorite(devotional.id)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detail Renungan",
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = LoraFont,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            favoriteViewModel.toggleFavorite(devotional.id)
                            onFavoriteClick()
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) {
                                Icons.Filled.Favorite
                            } else {
                                Icons.Outlined.FavoriteBorder
                            },
                            contentDescription = "Favorite",
                            tint = Color.Red
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3EEFF)
                )
            )
        }
    ) { innerPadding ->
        DetailContent(
            devotional = devotional,
            innerPadding = innerPadding
        )
    }
}

@Composable
fun DetailContent(
    devotional: Devotional,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(innerPadding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = devotional.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = StyleScript,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = getTitleColor(devotional.month)
                )
                Text(
                    text = devotional.verseReference,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = LoraFont,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF7B61FF)
                )
                Text(
                    text = "\"${devotional.verseText}\"",
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = LoraFont,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF555555)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color(0xFFE0E0E0)
                )

                Text(
                    text = devotional.content,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = LoraFont,
                    color = Color(0xFF333333)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color(0xFFE0E0E0)
                )

                Text(
                    text = devotional.closing ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = LoraFont,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A4C93)
                )

                Text(
                    text = "Tanggal Renungan: ${formatDevotionalDate(devotional.day, devotional.month)}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailScreenPreview() {
    RenunganharianTheme {

    }
}