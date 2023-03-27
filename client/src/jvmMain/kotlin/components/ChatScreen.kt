package components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.ChatMessage
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.launch
import util.ChatClient

@Composable
fun ChatScreen(userName: String) {
    val channel = remember {
        ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build()
    }
    val chatClient = remember { ChatClient(userName, channel) }
    val chatMessages = remember { mutableStateListOf<ChatMessage>() }

    LaunchedEffect(chatClient) {
        chatClient.startChat { chatMessages.add(it) }
    }

    DisposableEffect(Unit) {
        onDispose { chatClient.close() }
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(chatMessages) { msg ->
                when (msg.type) {
                    ChatMessageOuterClass.MessageType.UNSPECIFIED -> TODO()
                    ChatMessageOuterClass.MessageType.CHAT -> TODO()
                    ChatMessageOuterClass.MessageType.JOIN -> TODO()
                    ChatMessageOuterClass.MessageType.LEAVE -> TODO()
                }
            }
        }
        var message by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = message,
                onValueChange = { message = it },
                label = { Text("Send Message") }
            )
            IconButton(
                onClick = {
                    scope.launch {
                        chatClient.sendMessage(message)
                        message = ""
                    }
                }
            ) {
                Icon(Icons.Default.Send, null)
            }
        }
    }
}