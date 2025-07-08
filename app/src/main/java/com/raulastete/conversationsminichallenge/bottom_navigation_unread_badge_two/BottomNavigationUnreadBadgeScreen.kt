package com.raulastete.conversationsminichallenge.bottom_navigation_unread_badge_two


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raulastete.conversationsminichallenge.R
import com.raulastete.conversationsminichallenge.ui.theme.BackgroundMain
import com.raulastete.conversationsminichallenge.ui.theme.BackgroundSecondary
import com.raulastete.conversationsminichallenge.ui.theme.Error
import com.raulastete.conversationsminichallenge.ui.theme.OnSurface
import com.raulastete.conversationsminichallenge.ui.theme.OnSurfaceAlt
import com.raulastete.conversationsminichallenge.ui.theme.OnSurfaceVar
import com.raulastete.conversationsminichallenge.ui.theme.Primary
import com.raulastete.conversationsminichallenge.ui.theme.SurfaceA30
import com.raulastete.conversationsminichallenge.ui.theme.SurfaceA50
import com.raulastete.conversationsminichallenge.ui.theme.urbanistFontFamily
import kotlinx.serialization.Serializable
import kotlin.collections.forEachIndexed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationUnreadBadgeScreen() {

    val navController = rememberNavController()

    val navItems = remember {
        mutableStateListOf(
            NavItem.Chats(),
            NavItem.Calls(),
            NavItem.Settings()
        )
    }

    var navItemSelected by remember { mutableStateOf(navItems.last()) }

    fun setNotificationInChats() {
        val chats = navItems[0]
        if (chats is NavItem.Chats) {
            navItems[0] = chats.copy(showBadge = true)
        }
    }

    fun setNotificationInCalls() {
        val calls = navItems[1]
        if (calls is NavItem.Calls) {
            navItems[1] = calls.copy(showBadge = true)
        }
    }

    fun removeNotificationInChats() {
        val chats = navItems[0]
        if (chats is NavItem.Chats) {
            navItems[0] = chats.copy(showBadge = false)
        }
    }

    fun removeNotificationInCalls() {
        val calls = navItems[1]
        if (calls is NavItem.Calls) {
            navItems[1] = calls.copy(showBadge = false)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        navItemSelected.title,
                        fontFamily = urbanistFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                        lineHeight = 25.sp.times(1.2)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundMain,
                    titleContentColor = OnSurface
                )
            )
        }
    ) { innerPadding ->
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
                )
                .padding(innerPadding)
        ) {

            NavHost(
                navController = navController,
                startDestination = SettingsDestination
            ) {

                composable<ChatsDestination> {
                    Box(Modifier.fillMaxSize())
                }
                composable<CallsDestination> {
                    Box(Modifier.fillMaxSize())
                }
                composable<SettingsDestination> {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { setNotificationInCalls() },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary
                            )
                        ) {
                            Row(
                                Modifier
                                    .width(240.dp)
                                    .padding(vertical = 12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.call),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = OnSurfaceAlt
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "Miss a call",
                                    fontFamily = urbanistFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    color = OnSurfaceAlt
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        Button(
                            onClick = { setNotificationInChats() },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary
                            )
                        ) {
                            Row(
                                Modifier
                                    .width(240.dp)
                                    .padding(vertical = 12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.chat),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = OnSurfaceAlt
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "Send Message",
                                    fontFamily = urbanistFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    color = OnSurfaceAlt
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        Button(
                            onClick = { removeNotificationInChats() },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Primary,
                                containerColor = Transparent
                            ),
                            border = BorderStroke(1.dp, color = Primary)
                        ) {
                            Row(
                                Modifier
                                    .width(240.dp)
                                    .padding(vertical = 12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.read),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "Mark as Read",
                                    fontFamily = urbanistFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp
                                )
                            }
                        }

                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                FloatingBottomBar(
                    navItemSelected = navItemSelected,
                    navItems = navItems,
                    onItemClick = {
                        navItemSelected = it
                        when(it){
                            is NavItem.Chats -> removeNotificationInChats()
                            is NavItem.Calls -> removeNotificationInCalls()
                            is NavItem.Settings -> {}
                        }
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun FloatingBottomBar(
    navItemSelected: NavItem,
    navItems: List<NavItem>,
    onItemClick: (NavItem) -> Unit
) {
    AppNavBar(
        backgroundColor = SurfaceA30
    ) {
        navItems.forEachIndexed { index, item ->
            AppNavBarItem(
                icon = item.icon,
                selected = index == navItemSelected.index,
                showBadge = item.showBadge,
                onClick = {
                    onItemClick(item)
                },
            )
        }
    }
}

@Composable
fun AppNavBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: Shape = RoundedCornerShape(18.dp),
    contentPadding: PaddingValues = PaddingValues(6.dp),
    content: @Composable RowScope.() -> Unit
) {
    Box(
        modifier = modifier
            .border(width = 1.dp, color = SurfaceA50, shape = shape)
            .background(color = SurfaceA30, shape = shape)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(color = backgroundColor, shape = shape)
                .padding(contentPadding)
        )
        Row(
            modifier = Modifier.padding(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}

@Composable
fun AppNavBarItem(
    icon: Int,
    showBadge: Boolean,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = if (selected) OnSurface else Color.Transparent
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            showBadge, modifier =
                Modifier
                    .offset(
                        x = (-12).dp,
                        y = 12.dp
                    )
                    .align(TopEnd)
        ) {
            Box(
                Modifier
                    .size(6.dp)
                    .background(color = Error, shape = CircleShape)
            )
        }

        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = if (selected) OnSurfaceAlt else OnSurfaceVar
        )
    }
}

@Serializable
sealed interface Destination

@Serializable
data object ChatsDestination : Destination

@Serializable
data object CallsDestination : Destination

@Serializable
data object SettingsDestination : Destination


sealed class NavItem(
    open val title: String,
    open val icon: Int,
    open val route: Destination,
    open val index: Int,
    open val showBadge: Boolean = false

) {
    data class Chats(
        override val title: String = "Chats",
        override val icon: Int = R.drawable.chat,
        override val route: Destination = ChatsDestination,
        override val index: Int = 0,
        override val showBadge: Boolean = false
    ) : NavItem(title, icon, route, index, showBadge)

    data class Calls(
        override val title: String = "Calls",
        override val icon: Int = R.drawable.call,
        override val route: Destination = CallsDestination,
        override val index: Int = 1,
        override val showBadge: Boolean = false
    ) : NavItem(title, icon, route, index, showBadge)

    data class Settings(
        override val title: String = "Settings",
        override val icon: Int = R.drawable.settings,
        override val route: Destination = SettingsDestination,
        override val index: Int = 2,
        override val showBadge: Boolean = false
    ) : NavItem(title, icon, route, index, showBadge)
}