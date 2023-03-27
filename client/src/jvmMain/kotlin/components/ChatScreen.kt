package components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.ChatMessage
import io.grpc.ManagedChannelBuilder
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

    DisposableEffect(Unit) {
        onDispose { chatClient.close() }
    }

    LazyColumn {
        items(chatMessages) {
            when (it.type) {
                ChatMessageOuterClass.MessageType.UNSPECIFIED -> TODO()
                ChatMessageOuterClass.MessageType.CHAT -> TODO()
                ChatMessageOuterClass.MessageType.JOIN -> TODO()
                ChatMessageOuterClass.MessageType.LEAVE -> TODO()
            }
        }
    }
}