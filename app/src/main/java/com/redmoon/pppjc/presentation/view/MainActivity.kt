package com.redmoon.pppjc.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redmoon.pppjc.R
import com.redmoon.pppjc.common.local.NotesEntity
import com.redmoon.pppjc.presentation.viewModel.MainViewModel
import com.redmoon.pppjc.ui.theme.PPPjcTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsCompose(viewModel)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun LetsCompose(viewModel: MainViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    PPPjcTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(modifier = Modifier.fillMaxSize()) {
                LiveDataComponent(viewModel)
                SetButton(viewModel, openDialog)
                AddNoteDialog(viewModel, openDialog)
            }

        }
    }
}

@Composable
fun SetButton(viewModel: MainViewModel, openDialog: MutableState<Boolean>) {
    Button(
        onClick = {
            openDialog.value = true
        },
    ) {
        Text(text = "Add Note")
    }
}

@Composable
fun AddNoteDialog(viewModel: MainViewModel, openDialog: MutableState<Boolean>) {
    if (openDialog.value) {
        val title = remember {
            mutableStateOf(TextFieldValue(""))
        }
        val description = remember {
            mutableStateOf(TextFieldValue(""))
        }
        AlertDialog(onDismissRequest = {
            openDialog.value = false
        },
            title = {


                Column(Modifier.padding(8.dp)) {
                    Text(text = "Add Note")
                    OutlinedTextField(value = title.value,
                        onValueChange = {
                            title.value = it
                        },
                        label = { Text(text = "Title") }
                    )
                    OutlinedTextField(value = description.value,
                        onValueChange = {
                            description.value = it
                        },
                        label = { Text(text = "Description") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addItem(title = title.value.text, description = description.value.text)
                    openDialog.value = false
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    openDialog.value = false
                }) {
                    Text(text = "Dismiss")
                }
            })
    }
}

@ExperimentalMaterialApi
@Composable
fun LiveDataComponent(viewModel: MainViewModel) {

    val notes by viewModel.items.observeAsState(initial = emptyList())
    if (notes.isEmpty()) {
        LiveDataLoadingComponent()
    } else {
        ItemsComponent(notes, viewModel)
    }

}

@ExperimentalMaterialApi
@Composable
fun ItemsComponent(itemsList: List<NotesEntity>, viewModel: MainViewModel) {
    MaterialTheme {
        LazyColumn {
            items(items = itemsList, itemContent = { note ->
                Card(
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(8.dp)
                ) {
                    ListItem(text = {
                        Text(
                            text = note.title,
                            style = TextStyle(
                                fontFamily = FontFamily.Serif, fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }, secondaryText = {
                        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        val date = java.util.Date(note.timestamp)
                        sdf.format(date)
                        Text(
                            text = "${note.description} \n${sdf.format(date)}",
                            style = TextStyle(
                                fontFamily = FontFamily.Serif, fontSize = 15.sp,
                                fontWeight = FontWeight.Light, color = Color.DarkGray
                            )
                        )
                    }, icon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .clickable(onClick = { viewModel.deleteItem(note.id) })
                        )
                    })
                }
            })
        }
    }
}

@Composable
fun LiveDataLoadingComponent() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = CenterHorizontally
//    ) {
//        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
//    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PPPjcTheme {
        LiveDataLoadingComponent()
    }
}