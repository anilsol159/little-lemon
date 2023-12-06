package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.bg
import com.example.littlelemon.ui.theme.yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavHostController){
    val context = LocalContext.current
    var sharedPreferences: SharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE)
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
                .size(50.dp)

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        )
        {

            Image(painter= ColorPainter(bg), contentDescription = "Backgroung")
            Text(text = "Let's get to know you", fontSize = 25.sp, color = Color.White)
        }

        Text(text = "Personal Information", fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.padding(top=20.dp, bottom = 10.dp))

        OutlinedTextField(
            value = firstName, onValueChange = {firstName = it},
            label = { Text(text = "First name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = yellow,
                focusedLabelColor = Color.Black
            )
            )
        OutlinedTextField(value = lastName, onValueChange = {lastName = it},label = { Text(text = "Last name")},modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = yellow,
            focusedLabelColor = Color.Black
        ))
        OutlinedTextField(value = email, onValueChange = {email = it},label = { Text(text = "Email")},modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = yellow,
            focusedLabelColor = Color.Black
        ))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                          if (firstName.isBlank()||lastName.isBlank()||email.isBlank()){
                              Toast.makeText(context,"Registration unsuccessful. Please enter all data.",Toast.LENGTH_SHORT).show()
                          }else{
                              sharedPreferences.edit()
                                  .putString("firstName",firstName)
                                  .putString("lastName",lastName)
                                  .putString("email",email)
                                  .putBoolean("Logged",true)
                                  .apply()
                              Toast.makeText(context,"Registration successful",Toast.LENGTH_SHORT).show()
                              navController.navigate(Home.route)
                          }
                          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = yellow,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .size(60.dp)
            ) {
                Text(text = "Register", fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding(){
//    Onboarding()
}