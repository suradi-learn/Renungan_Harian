package com.suradi.renunganharian.ui.screen

import com.suradi.renunganharian.utils.getMonthName
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suradi.renunganharian.R
import com.suradi.renunganharian.ui.theme.LoraFont
import com.suradi.renunganharian.ui.theme.StyleScript
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suradi.renunganharian.utils.formatDevotionalDate
import com.suradi.renunganharian.utils.getTitleColor
import com.suradi.renunganharian.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    onClickDetail: (Int) -> Unit,
    onClickPreviousDevotions: () -> Unit
) {

    val homeViewModel: HomeViewModel = viewModel()

    val todayDevotional by homeViewModel.todayDevotional.collectAsState()


    val devotional = todayDevotional

    val formattedDate = devotional?.let {
        formatDevotionalDate(it.day, it.month)
    } ?: "--"

    val today = java.time.LocalDate.now()


    val customHeaderShape = GenericShape { size, _ ->
        moveTo(0f,0f)
        lineTo(0f, size.height * 0.82f)

        quadraticBezierTo(
            size.width * 0.30f, size.height * 1.00f,
            size.width * 0.60f, size.height * 0.86f
        )

        quadraticBezierTo(
            size.width * 0.82f, size.height * 0.78f,
            size.width, size.height * 0.88f
        )

        lineTo(size.width, 0f)
        close()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7FB))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // HEADER ATAS MELENGKUNG
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(customHeaderShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF6A8DFF),
                                Color(0xFF8E7CFF),
                                Color(0xFFC5A3FF)
                            )
                        )
                    )
            ) {
                // ORNAMEN BULAT 1
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .offset(x = (-60).dp, y =20.dp)
                        .clip(CircleShape)
                        .background(Color(0x33000000))
                )

                // ORNAMEN BULAT 2
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .offset(x = 20.dp, y =110.dp)
                        .clip(CircleShape)
                        .background(Color(0x22000000))
                )

                // ORNAMEN BULAT 3
                Box(
                    modifier = Modifier
                        .size(240.dp)
                        .offset(x = 180.dp, y =(-30).dp)
                        .clip(CircleShape)
                        .background(Color(0x22FFFFFF))
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            // NAMA + SUBTITLE
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Suradi",
                    fontFamily = LoraFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F1F1F)
                )
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "suraditanadi@gmail.com",
                    fontSize = 13.sp,
                    color = Color(0xFF9E9E9E)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // CARD UTAMA
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 24.dp, bottomEnd = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),

                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Renungan Hari Ini",
                            fontFamily = LoraFont,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF222222)
                        )

                        Text(
                            text = formattedDate,
                            fontFamily = LoraFont,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF7C6CF2) // warna ungu
                        )
                    }

//                     CENTER
                    Text(
                        text = devotional?.title ?: "Sedang Loading...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = StyleScript,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = devotional?.let {getTitleColor(it.month) } ?: Color.Black
                    )

//                    devotional?.let { data ->
//                        Text(
//                            text = data.title,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(8.dp),
//                            textAlign = TextAlign.Center,
//                            fontFamily = StyleScript,
//                            style = MaterialTheme.typography.titleLarge,
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 32.sp,
//                            color = getTitleColor(data.month)
//                        )
//                    }


                    Text(
                        text = devotional?.verseReference ?: "",
                        modifier = Modifier.fillMaxWidth() .padding(bottom = 8.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = LoraFont,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color(0xFF222222)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            devotional?.let {
                                onClickDetail( it.id)
                            }
                            // nanti bisa diarahkan ke halaman detail renungan
                        },
                        enabled = devotional != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7C6CF2)
                        )
                    ) {
                        Text(
                            text = "Baca Selengkapnya",
                            fontSize = 16.sp,
                            fontFamily = LoraFont,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            onClickPreviousDevotions()
                            // nanti bisa diarahkan ke list renungan sebelumnya
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEDEBFF),
                            contentColor = Color(0XFF5C4ED8)
                        )
                    ) {
                        Text(
                            text = "Lihat Renungan Sebelumnya",
                            fontSize = 15.sp,
                            fontFamily = LoraFont,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // FOTO PROFIL MENGAMBANG
        Box(
            modifier = Modifier
                .size(112.dp)
                .align(Alignment.TopCenter)
                .offset(y = 180.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = CircleShape,
                    clip = false
                )
        ) {
        Image(
            painter = painterResource(id = R.drawable.profile_suradi),
            contentDescription = "Foto Profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onClickDetail = { _ -> },
        onClickPreviousDevotions = {}
    )
}
