package ozarskiapps.booktracker.bookDetailsActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ozarskiapps.booktracker.book.Book
import ozarskiapps.booktracker.book.BookStatus
import ozarskiapps.booktracker.database.BookDBService

class BookDetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //replace with book from database
            val id = intent.getLongExtra("BOOK_ID", -1)
            if (id == -1L) {
                onBackPressedDispatcher.onBackPressed()
                throw Exception("Book id not found")
            }
            val book = remember {
                mutableStateOf(
                    BookDBService(this).getBookByID(id)
                )
            }
            if(book.value == null) {
                onBackPressedDispatcher.onBackPressed()
                throw Exception("Book not found")
            }
            else{
                val notNullBook = remember { mutableStateOf(book.value!!) }
                BookDetailsActivityUI(notNullBook, this).GenerateLayout()
            }
        }
    }

}

@Preview
@Composable
private fun BookDetailsLayout() {
    val book = remember {
        mutableStateOf(
            Book(
                "The Lord of the Rings",
                "J.R.R. Tolkien",
                1178,
                0f,
                BookStatus.WantToRead,
                java.util.Calendar.getInstance(),
                java.util.Calendar.getInstance(),
                0
            )
        )
    }
    BookDetailsActivityUI(book = book, LocalContext.current).GenerateLayout()
}
