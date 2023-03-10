package ozarskiapps.booktracker.mainActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ozarskiapps.booktracker.mockBookList

@Composable
fun LayoutMainBooks(){
    val text = remember { mutableStateOf("Reading") }
    val isOpen = remember {
        mutableStateOf(false)
    }
    val openCloseDropdown: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text.value = it
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Row(modifier = Modifier.fillMaxWidth()){
            Text(text = text.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 10.dp)
                    .clickable { isOpen.value = true },
                fontSize = 20.sp,
                textAlign = TextAlign.Start)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            val list = mutableListOf<String>()
            for (i in 0..20) {
                list.add("Tag $i")
            }
            DropdownTagList(list = list,
                request = openCloseDropdown,
                selectedString = userSelectedString,
                requestToOpen = isOpen.value)
            BookListLazyColumn(bookList = mockBookList())
        }
    }
}

@Composable
private fun DropdownTagList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
){
    DropdownMenu(expanded = requestToOpen, onDismissRequest = { request(false) }, modifier = Modifier.fillMaxWidth()){
        list.forEach {
            DropdownMenuItem(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray), onClick = {
                selectedString(it)
                request(false)
                println("Selected: $it")
            }){
                Text(it)
            }
        }
    }
}