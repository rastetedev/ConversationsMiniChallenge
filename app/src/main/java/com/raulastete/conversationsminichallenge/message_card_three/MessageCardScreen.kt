package com.raulastete.conversationsminichallenge.message_card_three

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulastete.conversationsminichallenge.R
import com.raulastete.conversationsminichallenge.ui.theme.BackgroundMain
import com.raulastete.conversationsminichallenge.ui.theme.BackgroundSecondary
import com.raulastete.conversationsminichallenge.ui.theme.Blue
import com.raulastete.conversationsminichallenge.ui.theme.OnSurface
import com.raulastete.conversationsminichallenge.ui.theme.OnSurfaceAlt
import com.raulastete.conversationsminichallenge.ui.theme.OnSurfaceVar
import com.raulastete.conversationsminichallenge.ui.theme.Primary
import com.raulastete.conversationsminichallenge.ui.theme.SurfaceA50
import com.raulastete.conversationsminichallenge.ui.theme.SurfaceAltA30
import com.raulastete.conversationsminichallenge.ui.theme.urbanistFontFamily

enum class MessageState {
    SENT, DELIVERED, READ
}

@Composable
fun MessageCardScreen() {

    var currentState by remember { mutableStateOf(MessageState.SENT) }

    Column(
        Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        BackgroundMain,
                        BackgroundSecondary
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Row(Modifier.padding(horizontal = 20.dp), verticalAlignment = Alignment.Bottom) {
            Avatar("D")
            Spacer(Modifier.width(8.dp))
            MessageCard(
                modifier = Modifier.weight(1f),
                currentState = currentState
            )
        }

        Spacer(Modifier.weight(1f))

        CustomButton(
            text = "Mark as Sent",
            icon = R.drawable.unread,
            enabled = currentState != MessageState.SENT
        ) {
            currentState = MessageState.SENT
        }
        Spacer(Modifier.height(12.dp))
        CustomButton(
            text = "Mark as Delivered",
            icon = R.drawable.read,
            enabled = currentState != MessageState.DELIVERED
        ) {
            currentState = MessageState.DELIVERED
        }
        Spacer(Modifier.height(12.dp))
        CustomButton(
            text = "Mark as Read",
            icon = R.drawable.read,
            enabled = currentState != MessageState.READ
        ) {
            currentState = MessageState.READ
        }
        Spacer(Modifier.height(26.dp))
    }

}

@Composable
fun MessageCard(modifier: Modifier = Modifier, currentState: MessageState) {

    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = SurfaceA50
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "DreamSyntaxHiker",
                    fontFamily = urbanistFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    color = OnSurface,
                )

                Text(
                    text = "1 day ago",
                    fontFamily = urbanistFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = SurfaceAltA30
                )
            }

            Text(
                text = stringResource(R.string.message_card_body),
                fontFamily = urbanistFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = OnSurface,
            )

            MessageStateLabel(currentState)

        }
    }
}

@Composable
fun Avatar(singleInitial: String) {

    Box(
        Modifier
            .size(28.dp)
            .background(color = Primary, shape = CircleShape), contentAlignment = Alignment.Center
    ) {
        Text(
            singleInitial,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = OnSurfaceAlt
        )
    }
}

@Composable
fun MessageStateLabel(currentState: MessageState) {

    val icon = when (currentState) {
            MessageState.SENT -> R.drawable.unread
            else -> R.drawable.read
        }

    val text = when (currentState) {
        MessageState.SENT -> "Sent"
        MessageState.DELIVERED -> "Delivered"
        MessageState.READ -> "Read"
    }

    val color by animateColorAsState(
        when (currentState) {
            MessageState.READ -> Blue
            else -> OnSurfaceVar
        }
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = color
        )
        Text(
            text,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            color = color
        )
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            contentColor = Primary,
            containerColor = Transparent,
            disabledContainerColor = Transparent,
            disabledContentColor = OnSurfaceVar
        ),
        border = BorderStroke(1.dp, color = if (enabled) Primary else SurfaceA50)
    ) {
        Row(
            Modifier
                .width(240.dp)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text,
                fontFamily = urbanistFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}