//Compose modules
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
//For checking if file exists
import java.io.File

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

public var errorMessage: String = ""


@Composable
fun UIContent() {

    val playerConfigUIWidth = 300.dp

    var playerConfigInput by remember { mutableStateOf("basePlayerConfig.csv") }

    var playerConfig by remember { mutableStateOf("") }


    var playerConfigMessage by remember { mutableStateOf("Awaiting a Player Config..") }
    
    var bossConfigInput by remember { mutableStateOf("baseBossTimeline.csv") }

    var bossConfig by remember { mutableStateOf("") }

    var bossConfigMessage by remember { mutableStateOf("Awaiting a Boss Timeline..") }



    // CONFIG INPUTS
    Row(modifier = androidx.compose.ui.Modifier.padding(16.dp)){
        Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
            TextField(
                value = playerConfigInput,
                onValueChange = { playerConfigInput = it },
                label = { Text("Enter config, example:") },
                modifier = androidx.compose.ui.Modifier.width(playerConfigUIWidth)
            )

            Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))

            Button(
                onClick = {
                    playerConfigMessage = "Loading..."
                    // Add "config/" prefix if missing
                    val normalizedInput = if (playerConfigInput.startsWith("config/")) {
                        playerConfigInput.trim()
                    } else {
                        "config/${playerConfigInput.trim()}"
                    }

                    playerConfig = normalizedInput

                    if (playerConfig.isEmpty()) {
                        playerConfigMessage = "ENTER A VALID PLAYER CONFIG"
                    } else if (!csvFileExists(playerConfig)) {
                        playerConfigMessage = "FILE DOES NOT EXIST OR WAS NOT FOUND: $playerConfig"
                    } else {
                        val playerList = initializeParty(file = playerConfig, mode = "lowRisk")
                        playerConfigMessage = "Loaded Config: $playerConfig"
                        for(player in playerList){
                            player.introduceYourself()
                        }
                    }
                }
                ,
                modifier = androidx.compose.ui.Modifier.width(playerConfigUIWidth))
            {
                Text("Load Player Configurations")
            }

            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            Text(playerConfigMessage)
        }

        Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
            TextField(
                value = bossConfigInput,
                onValueChange = { bossConfigInput = it },
                label = { Text("Enter config, example:") },
                modifier = androidx.compose.ui.Modifier.width(playerConfigUIWidth)
            )

            Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))

            Button(
                onClick = {
                    bossConfigMessage = "Loading..."
                    // Add "timeline/" prefix if missing
                    val normalizedInput = if (bossConfigInput.startsWith("timeline/")) {
                        bossConfigInput.trim()
                    } else {
                        "timeline/${bossConfigInput.trim()}"
                    }

                    bossConfigInput = normalizedInput

                    if (bossConfigInput.isEmpty()) {
                        bossConfigMessage = "ENTER A VALID BOSS TIMELINE"
                    } else if (!csvFileExists(bossConfigInput)) {
                        bossConfigMessage = "FILE DOES NOT EXIST OR WAS NOT FOUND: $bossConfigInput"
                    } else {
                        initializeTimeline(file = bossConfigInput)
                        bossConfigMessage = "Loaded Timeline: $bossConfigInput"
                    }
                }
                ,
                modifier = androidx.compose.ui.Modifier.width(playerConfigUIWidth))
            {
                Text("Load Boss Timeline")
            }

            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            Text(bossConfigMessage)
        }
    }
}



fun csvFileExists(fileName: String, directoryPath: String = "."): Boolean {
    val file = File(directoryPath, fileName)
    return file.exists() && file.extension.lowercase() == "csv"
}

@Composable
fun PressableButton(
    onClick: suspend () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    var isPressed by remember { mutableStateOf(false) }

    Button(
        onClick = {
            isPressed = true
            scope.launch {
                onClick()
                isPressed = false
            }
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isPressed) Color.DarkGray else Color.Gray
        )
    ) {
        content()
    }
}