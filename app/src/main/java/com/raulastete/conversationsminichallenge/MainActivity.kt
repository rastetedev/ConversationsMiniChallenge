package com.raulastete.conversationsminichallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raulastete.conversationsminichallenge.bottom_navigation_unread_badge_two.BottomNavigationUnreadBadgeActivity
import com.raulastete.conversationsminichallenge.emoji_reaction_bubble_one.EmojiReactionBubbleActivity
import com.raulastete.conversationsminichallenge.ui.theme.ConversationsMiniChallengeTheme
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConversationsMiniChallengeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                EmojiReactionBubbleActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "Mini challenge #1 - Emoji Reaction Bubble",
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                BottomNavigationUnreadBadgeActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "Mini challenge #2 - Bottom Navigation with Unread Badge",
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}