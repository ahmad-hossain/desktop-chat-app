package util

import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.ChatMessage
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.MessageType
import com.ahmad_hossain.desktopchatapp.common.ChatServiceGrpcKt.ChatServiceCoroutineStub
import com.ahmad_hossain.desktopchatapp.common.chatMessage
import io.grpc.ManagedChannel
import java.io.Closeable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ChatClient (
    private val userName: String,
    private val channel: ManagedChannel
) : Closeable {
    private val stub: ChatServiceCoroutineStub = ChatServiceCoroutineStub(channel)
    private val userId = Random.nextInt()

    suspend fun startChat(onMessage: (ChatMessage) -> Unit) {
        val req = chatMessage {
            senderId = userId
            senderName = userName
            type = MessageType.JOIN
        }
        stub.startChat(req).collect(onMessage)
    }

    suspend fun sendMessage(msg: String) {
        val req = chatMessage {
            senderId = userId
            senderName = userName
            type = MessageType.CHAT
            message = msg
        }
        stub.sendMessage(req)
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}