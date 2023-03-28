package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.ChatMessage
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.MessageType

@Composable
fun JoinLeaveMsg(msg: ChatMessage) {
    val statusText = remember {
        when (msg.type) {
            MessageType.JOIN -> " joined the chat"
            else -> " left the chat"
        }
    }
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(msg.senderName)
            }
            append(statusText)
        },
        textAlign = TextAlign.Center
    )
}