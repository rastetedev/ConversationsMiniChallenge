package com.raulastete.conversationsminichallenge.emoji_reaction_bubble_one

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.raulastete.conversationsminichallenge.ui.theme.BackgroundMain
import com.raulastete.conversationsminichallenge.ui.theme.BackgroundSecondary
import com.raulastete.conversationsminichallenge.ui.theme.BlueA12
import com.raulastete.conversationsminichallenge.ui.theme.OnSurface
import com.raulastete.conversationsminichallenge.ui.theme.SurfaceA30
import com.raulastete.conversationsminichallenge.ui.theme.SurfaceA50
import com.raulastete.conversationsminichallenge.ui.theme.urbanistFontFamily

@Composable
fun EmojiReactionBubbleScreen() {

    var showEmojiSelectableContainer by remember { mutableStateOf(false) }

    val emojiCounterMap = remember {
        mutableStateMapOf(
            "👍" to 0,
            "😂" to 0,
            "😮" to 0,
            "❤️" to 0,
            "😢" to 0
        )
    }

    fun onSelectEmoji(emoji: String) {
        emojiCounterMap[emoji] = (emojiCounterMap[emoji] ?: 0) + 1
        showEmojiSelectableContainer = false
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        BackgroundMain,
                        BackgroundSecondary
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        ConstraintLayout {
            val (selectableEmojiContainerRef, chatTextRef) = createRefs()

            AnimatedVisibility(
                showEmojiSelectableContainer,
                modifier = Modifier.constrainAs(selectableEmojiContainerRef) {
                    start.linkTo(chatTextRef.start)
                    end.linkTo(chatTextRef.end)
                    bottom.linkTo(chatTextRef.top, margin = 6.dp)
                },
            ) {
                SelectableEmojiContainer(
                    emojiCounterMap = emojiCounterMap,
                    onTapEmoji = { emoji ->
                        onSelectEmoji(emoji)
                    }
                )
            }

            ChatText(
                modifier = Modifier.constrainAs(chatTextRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                emojiCounterMap = emojiCounterMap,
                showEmojiSelectableContainer = {
                    showEmojiSelectableContainer = true
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChatText(
    modifier: Modifier = Modifier,
    emojiCounterMap: Map<String, Int>,
    showEmojiSelectableContainer: () -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        showEmojiSelectableContainer()
                    }
                )
            }
            .background(SurfaceA50, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 20.dp, vertical = 12.dp),
    ) {

        val (textBoxRef, emojiBoxRef) = createRefs()

        Text(
            modifier = Modifier.constrainAs(textBoxRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "I'll send the draft tonight.",
            color = OnSurface,
            fontFamily = urbanistFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp
        )

        AnimatedVisibility(
            modifier = Modifier.constrainAs(emojiBoxRef) {
                top.linkTo(textBoxRef.bottom, margin = 6.dp)
                start.linkTo(textBoxRef.start)
                end.linkTo(textBoxRef.end)
                width = Dimension.fillToConstraints
            },
            visible = emojiCounterMap.any { it.value > 0 }) {
            FlowRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                emojiCounterMap
                    .forEach { (emoji, counter) ->
                        EmojiBox(emoji = emoji, counter = counter)
                    }
            }
        }
    }
}


@Composable
fun SelectableEmojiContainer(
    modifier: Modifier = Modifier,
    emojiCounterMap: Map<String, Int>,
    onTapEmoji: (String) -> Unit
) {
    Row(
        modifier
            .background(SurfaceA30, shape = RoundedCornerShape(22.dp))
            .border(width = 1.dp, color = SurfaceA50, shape = RoundedCornerShape(22.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        emojiCounterMap.keys.forEach { emoji ->
            Text(
                emoji, fontSize = 18.sp,
                modifier = Modifier.clickable {
                    onTapEmoji(emoji)
                }
            )
        }
    }
}

@Composable
fun EmojiBox(emoji: String, counter: Int) {
    AnimatedVisibility(counter > 0) {
        Row(
            modifier = Modifier
                .background(BlueA12, shape = RoundedCornerShape(8.dp))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier.clickable {},
                text = emoji,
                fontFamily = urbanistFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
            )

            AnimatedVisibility(counter > 1) {
                Text(
                    modifier = Modifier.padding(end = 6.dp),
                    text = counter.toString(),
                    color = OnSurface,
                    fontFamily = urbanistFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
        }
    }
}