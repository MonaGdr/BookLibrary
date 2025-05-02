package com.example.booklibrary.presentation.screen

const val BOOK_ID = "bookId"

sealed class Screens(val route: String) {
    data object BookList : Screens("bookList")
    data object BookDetail : Screens( "bookDetail" )

    fun withArgs(bookId: Int?):String{
        return buildString {
            append(route)
            if (bookId != null)
                append("?$BOOK_ID=$bookId")
        }
    }
}
