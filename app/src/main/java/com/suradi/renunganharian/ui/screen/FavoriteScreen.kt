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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.suradi.renunganharian.ui.theme.StyleScript
import com.suradi.renunganharian.utils.formatDevotionalDate
import com.suradi.renunganharian.utils.getCardColor
import com.suradi.renunganharian.utils.getMonthName
import com.suradi.renunganharian.utils.getTitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(  // composable utama halaman favorite
    devotionals: List<Devotional>,
    favoriteViewModel: FavoriteViewModel, // sumber data favorite yang dibagikan dari AppNavGraph
    onBackClick: () -> Unit, // callback untuk tombol panah kembali
    onClickDetail: (Int) -> Unit
) {
    val favoriteIds = favoriteViewModel.favoriteIds.value  // mengambil daftar favorite dari ViewModel
    val favorites = devotionals.filter { it.id in favoriteIds }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar( // membuat header halaman yang konsisten dengan detail screen
                title = {
                    Text(
                        text = "Renungan Pilihan",
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = LoraFont,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) { // saat ditekan, halaman kembali ke screen sebelumnya
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
    ) { innerPadding -> // padding dari Scaffold agar konten tidak tertutup topbar
        if (favorites.isEmpty()) { // mengecek apakah daftar favorite masih kosong
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F7))
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally // membuat tampilan empty state dan memusatkan komponen ke tengah layar
            ) {
                Text(
                    text = "Belum ada renungan yang dipilih.",
                    textAlign = TextAlign.Center, // meratakan isi teks ke tengah di dalam area Text
                    fontFamily = LoraFont,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth() // memperlebar area Text agar textAlign Center bekerja jelas
                )
            }
        } else {
            LazyColumn( // jika favorite ada, tampilkan daftar dalam bentuk list vertikal
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F7))
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favorites) { devotional -> // melakukan iterasi setiap objek Devotional di list favorites
                    FavoriteItem(  // setiap item meneruskan data devotional yang dipilih ke callback
                        devotional = devotional,
                        onClick = { onClickDetail(devotional.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(   // composable untuk satu kartu favorite
    devotional: Devotional,
    onClick: () -> Unit, // callback ketika kartu ditekan
    ) {
    Card(
        modifier = Modifier  // card dibuat selebar layar dan dapat diklik
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = getCardColor(devotional.month)
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
                    text = formatDevotionalDate(devotional.day, devotional.month),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = LoraFont,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6A4C93)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = devotional.title,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = StyleScript,
                fontWeight = FontWeight.Bold,
                color = getTitleColor(devotional.month)
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