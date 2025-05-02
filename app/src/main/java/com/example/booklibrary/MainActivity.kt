package com.example.booklibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booklibrary.presentation.screen.BOOK_ID
import com.example.booklibrary.presentation.screen.BookDetailScreen
import com.example.booklibrary.presentation.screen.BookListScreen
import com.example.booklibrary.presentation.screen.Screens
import com.example.booklibrary.presentation.viewModel.BookListViewModel
import com.example.booklibrary.ui.theme.BookLibraryTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel: BookListViewModel by viewModel()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            BookLibraryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    //navigation
                    NavHost(navController = navController, startDestination = Screens.BookList.route) {

                        //BookListScreen
                        composable(Screens.BookList.route) {
                            BookListScreen(navController, viewModel)
                        }

                        //BookDetailScreen
                        composable(
                            route = Screens.BookDetail.route + "?$BOOK_ID={$BOOK_ID}",
                            arguments = listOf(
                                navArgument(name = BOOK_ID) {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val bookId = backStackEntry.arguments?.getInt(BOOK_ID)
                            BookDetailScreen(navController, viewModel, bookId)
                        }
                    }
                }
            }
        }
    }
}
