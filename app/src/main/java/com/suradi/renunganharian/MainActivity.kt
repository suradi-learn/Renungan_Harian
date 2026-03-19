package com.suradi.renunganharian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.suradi.renunganharian.data.dummy.dummyDevotionals
import com.suradi.renunganharian.navigation.AppNavGraph
import com.suradi.renunganharian.ui.screen.DetailScreen
import com.suradi.renunganharian.ui.theme.RenunganharianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RenunganharianTheme() {
                AppNavGraph()
            }
        }
    }
}



