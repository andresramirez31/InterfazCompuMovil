package com.Booktrace.interfazcompumovil

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp

import com.Booktrace.interfazcompumovil.ui.theme.InterfazCompuMovilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfazCompuMovilTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Formulario()
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row {

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false)}

        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )


        Column (modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn{
        items(messages){ message ->
            MessageCard(msg = message)

        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    InterfazCompuMovilTheme {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }
}



@Preview
@Composable
fun PreviewFormulario(){
    Formulario()
}

@Composable
fun Formulario(){
    var nombre by remember { mutableStateOf(TextFieldValue(""))}
    var apellido by remember { mutableStateOf(TextFieldValue(""))}
    var submissionStatus by remember { mutableStateOf("")}

    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(text = "Simple Form", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = nombre, onValueChange = { nombre = it}, label ={Text("Nombre")}, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it},
            label ={Text("Apellido")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { submissionStatus = handleSubmit(nombre.text, apellido.text) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Guardar Formulario")

        }

        if(submissionStatus.isNotEmpty()){
            Text(
                text = submissionStatus,
                color = if(submissionStatus == "Registro exitoso"){
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }


}


fun handleSubmit(nombre: String, apellido: String): String{
    if(nombre.isEmpty() || apellido.isEmpty()){
        return "Please fill all the fields"
    } else {
        println("Nombre: $nombre")
        println("Apellido: $apellido")
        return "Registro exitoso"
    }
}
