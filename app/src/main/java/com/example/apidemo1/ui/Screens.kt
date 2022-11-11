package com.example.apidemo1.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.apidemo1.data.api.model.Character
import com.example.apidemo1.data.api.model.CharacterViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnUsedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(){
    val vm = CharacterViewModel()
    LaunchedEffect(key1 = Unit, block = {
        vm.getCharacters()
    })

    Scaffold(
        topBar = {
                 TopAppBar(title = {
                     Row{
                         Text("Harry Potter Characters")
                     }
                 })
        },
        content={
                if(vm.errorMessage.isEmpty()){
                    LazyColumn(modifier = Modifier.fillMaxHeight()){
//                        item {
//                            CircularProgressIndicator(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .wrapContentSize(align = Alignment.Center)
//                            )
//                        }

                        Log.d("Tesfa", vm.characterList.count().toString())
                        Log.d("Tesfa", vm.errorMessage)
                        items(items = vm.characterList){character ->
                            CharacterImageCard(character = character)
                        }
                    }//LazyColumn
                }else{
                    Text(text = vm.errorMessage)
                }
        }, //content
    ) //Scaffold
} //HomeScreen

@Composable
fun CharacterImageCard(character: Character){
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Box{
            GlideImage(imageModel = { character.image },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                imageOptions = ImageOptions(contentScale = ContentScale.FillBounds),
                loading = {
                    Box(modifier = Modifier.matchParentSize()){
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                },
                failure = {
                    Text("Image Request Failed")
                }
            ) //Image
            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text="Real name: ${character.name}")
                    Text(text = "Actor name: ${character.actor}")
                }

            } //Surface
        } //Box

    } //Card
} //CharacterImageCard