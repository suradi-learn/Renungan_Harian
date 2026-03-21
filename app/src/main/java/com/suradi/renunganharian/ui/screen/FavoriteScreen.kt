package com.suradi.renunganharian.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.suradi.renunganharian.model.Devotional
import com.suradi.renunganharian.ui.theme.LoraFont
import com.suradi.renunganharian.viewmodel.FavoriteViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.suradi.renunganharian.ui.theme.StyleScript

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel,
    onBackClick: () -> Unit,
    onItemClick: (Devotional) -> Unit
) {
    val favorites = favoriteViewModel.favorites

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Renungan Pilihan",
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3EEFF)
                )
            )
        }
    ) { innerPadding ->
        FavoriteContent(
            favorites = favorites,
            innerPadding = innerPadding,
            onItemClick = onItemClick
        )
    }
}

@Composable
fun FavoriteContent(
    favorites: List<Devotional>,
    innerPadding: PaddingValues,
    onItemClick: (Devotional) -> Unit
) {
    if (favorites.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Belum ada renungan favorite.",
                textAlign = TextAlign.Center,
                fontFamily = LoraFont,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(favorites) { devotional ->
                FavoriteItem(
                    devotional = devotional,
                    onClick = {
                        onItemClick(devotional)
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteItem(
    devotional: Devotional,
    onClick: () -> Unit
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Month: ${devotional.month}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = LoraFont,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A4C93)
                )
                Text(
                    text = "Day: ${devotional.day}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = LoraFont,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A4C93)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = devotional.title,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = StyleScript,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2E2E2E)
            )

            Text(
                text = devotional.verseReference,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = LoraFont,
                color = Color(0xFF7B61FF)
            )
        }
    }
}