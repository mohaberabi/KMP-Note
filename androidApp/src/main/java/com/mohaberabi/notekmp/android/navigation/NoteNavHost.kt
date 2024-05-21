package com.mohaberabi.notekmp.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mohaberabi.notekmp.android.details.screen.DetailScreenRoot
import com.mohaberabi.notekmp.android.details.viewmodel.DetailViewModel
import com.mohaberabi.notekmp.android.navigation.NoteNavConst.DETAIL_SCREEN
import com.mohaberabi.notekmp.android.navigation.NoteNavConst.HOME_SCREEN
import com.mohaberabi.notekmp.android.navigation.NoteNavConst.NOTE_ID_KEY
import com.mohaberabi.notekmp.android.note.screen.NoteListScreenRoot


object NoteNavConst {
    const val HOME_SCREEN = "homeScreen"
    const val DETAIL_SCREEN = "homeScreen"
    const val NOTE_ID_KEY = "NOTE_ID_KEY"

}

@Composable
fun NoteNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {


    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN
    ) {

        composable(HOME_SCREEN) {

            NoteListScreenRoot(
                onGoToDetails = {
                    navController.navigate("${DETAIL_SCREEN}/${it}")
                },
            )
        }

        composable(
            route = "${DETAIL_SCREEN}/{${NOTE_ID_KEY}}",
            arguments = listOf(
                navArgument(NOTE_ID_KEY) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {

            DetailScreenRoot(
                onGoBack = {
                    navController.popBackStack()
                },
            )
        }

    }
}