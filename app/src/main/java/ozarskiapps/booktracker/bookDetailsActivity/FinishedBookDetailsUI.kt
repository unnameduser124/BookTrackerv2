package ozarskiapps.booktracker.bookDetailsActivity

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ozarskiapps.booktracker.book.Book
import ozarskiapps.booktracker.book.BookStatus
import java.text.SimpleDateFormat
import java.util.*

class FinishedBookDetailsUI(val book: MutableState<Book>, val context: Context) {

    @Composable
    fun GenerateLayout() {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            BookAttribute(labelText = "Book title", text = book.value.title)
            BookAttribute(labelText = "Book author", text = book.value.author)
            BookAttribute(labelText = "Number of pages", text = book.value.numberOfPages.toString())
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
            BookAttribute(labelText = "Start date", text = sdf.format(book.value.startDate.time))
            BookAttribute(labelText = "End date", text = sdf.format(book.value.endDate.time))
            BookAttribute(
                labelText = "Reading time",
                text = book.value.getBookReadingTimeInDays().toString()
            )
            BookAttribute(
                labelText = "Average pages per day",
                text = book.value.getAveragePagesPerDay().toString()
            )
            TagListAttribute(context = context, book = book)

            val rowModifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            val buttonsModifier = Modifier
                .padding(start = 16.dp, bottom = 10.dp, end = 10.dp)
                .weight(0.5f)
            EditDeleteButtons(rowModifier = rowModifier, buttonsModifier = buttonsModifier)
        }
    }

    @Composable
    fun EditDeleteButtons(rowModifier: Modifier, buttonsModifier: Modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
            modifier = rowModifier
        ) {
            Button(
                onClick = {
                },
                modifier = buttonsModifier
            ) {
                Text(text = "Edit")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = buttonsModifier
            ) {
                Text(text = "Delete")
            }
        }
    }
}

@Composable
@Preview
fun FinishedBookPreview() {
    val book = remember {
        mutableStateOf(
            Book(
                "title",
                "author",
                100,
                0f,
                BookStatus.Finished,
                Calendar.getInstance(),
                Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }
            )
        )
    }

    FinishedBookDetailsUI(book = book, context = androidx.compose.ui.platform.LocalContext.current).GenerateLayout()
}
