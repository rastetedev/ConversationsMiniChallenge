package com.raulastete.conversationsminichallenge.emoji_reaction_bubble_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raulastete.conversationsminichallenge.ui.theme.ConversationsMiniChallengeTheme

class EmojiReactionBubbleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(

        )
        setContent {
            ConversationsMiniChallengeTheme {
                EmojiReactionBubbleScreen()
            }
        }
    }
}