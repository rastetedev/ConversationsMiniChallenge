package com.raulastete.conversationsminichallenge.emoji_reaction_bubble_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raulastete.conversationsminichallenge.ui.theme.ConversationsMiniChallengeTheme
import com.raulastete.conversationsminichallenge.ui.theme.SurfaceA30

class EmojiReactionBubbleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(SurfaceA30.value.toInt())
        )
        setContent {
            ConversationsMiniChallengeTheme {
                EmojiReactionBubbleScreen()
            }
        }
    }
}