import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.ChatMessage
import com.ahmad_hossain.desktopchatapp.common.ChatServiceGrpcKt
import com.google.protobuf.Empty
import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class ChatServer(private val port: Int) {
    val server: Server = ServerBuilder
        .forPort(port)
        .addService(ChatService())
        .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                this@ChatServer.stop()
                println("*** server shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    internal class ChatService : ChatServiceGrpcKt.ChatServiceCoroutineImplBase() {

        private val chatMessage = MutableSharedFlow<ChatMessage>()

        override fun startChat(request: ChatMessage): Flow<ChatMessage> {
            println("${request.senderName} joined the chat")

            return flow {
                chatMessage.collect {
                    emit(it)
                }
            }
        }

        override suspend fun sendMessage(request: ChatMessage): Empty {
            println("Received: ${request.message} from senderId=${request.senderId}")
            chatMessage.emit(request)
            return Empty.getDefaultInstance()
        }

    }
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 50051
    val server = ChatServer(port)
    server.start()
    server.blockUntilShutdown()
}