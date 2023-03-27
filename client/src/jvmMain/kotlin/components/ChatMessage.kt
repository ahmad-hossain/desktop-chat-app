package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmad_hossain.desktopchatapp.common.ChatMessageOuterClass.ChatMessage

private val BubbleTextPadding = 8.dp

@Composable
fun ChatMsg(msg: ChatMessage, isSender: Boolean) {
    val (horizAlignment, bubbleColor) = if (isSender)
        Pair(Alignment.End, MaterialTheme.colors.primary) else
        Pair(Alignment.Start, MaterialTheme.colors.secondary)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = horizAlignment
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(modifier = Modifier.padding(start = BubbleTextPadding), text = msg.senderName)
            Row {
                Box(
                    modifier = Modifier
                        .background(bubbleColor, RoundedCornerShape(8.dp))
                        .padding(BubbleTextPadding)
                ) {
                    Text(
                        text = msg.message,
                        color = contentColorFor(bubbleColor)
                    )
                }
            }
        }
    }
}