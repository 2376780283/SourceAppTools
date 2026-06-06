package zzh.bin.sourceapptools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsApplications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import zzh.bin.sourceapptools.ui.theme.SourceAppToolsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeWindowSettings()
        setContent {
            SourceAppToolsTheme {
                AppNavigation()
            }
        }
    }
    private fun initializeWindowSettings() {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility =
            (android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}

@Composable
fun AppNavigationRail(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Introduction,
        Screen.VtfTool,
        Screen.VpkTool,
        Screen.Settings
    )
    NavigationRail {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationRailItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Row(modifier = Modifier.fillMaxSize()) {
        AppNavigationRail(navController = navController)
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.weight(1f)
        ) {
            composable("home") { HomeScreen(navController = navController) }
            composable("introduction") { IntroductionScreen(navController = navController) }
            composable("vtfTool") { VtfToolScreen(navController = navController) }
            composable("vpkTool") { VpkToolScreen(navController = navController) }
            composable("settings") { SettingsScreen(navController = navController) }
        }
    }
}



@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome to the Home Screen!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("This is the main entry point of the application.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate("introduction") }) {
                Text("Learn More")
            }
        }
    }
}

@Composable
fun IntroductionScreen(navController: NavController) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Introduction", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("This section provides an overview of the app and its features.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate("home") }) {
                Text("Go Back")
            }
        }
    }
}

@Composable
fun VtfToolScreen(navController: NavController) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("VTF Tool", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Placeholder for VTF Tool functionality.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate("home") }) {
                Text("Go Back")
            }
        }
    }
}

@Composable
fun VpkToolScreen(navController: NavController) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("VPK Tool", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Placeholder for VPK Tool functionality.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate("home") }) {
                Text("Go Back")
            }
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Settings", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Application settings will be managed here.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate("home") }) {
                Text("Go Back")
            }
        }
    }
}



// Sealed class to represent the screens
sealed class Screen(val title: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : Screen("Home", "home", androidx.compose.material.icons.Icons.Filled.Home)
    object Introduction : Screen("Introduction", "introduction", androidx.compose.material.icons.Icons.Filled.Info)
    object VtfTool : Screen("VTF Tool", "vtfTool", androidx.compose.material.icons.Icons.Filled.Build)
    object VpkTool : Screen("VPK Tool", "vpkTool", androidx.compose.material.icons.Icons.Filled.SettingsApplications)
    object Settings : Screen("Settings", "settings", androidx.compose.material.icons.Icons.Filled.Settings)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SourceAppToolsTheme {
        AppNavigation()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SourceAppToolsTheme {
        HomeScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun IntroductionScreenPreview() {
    SourceAppToolsTheme {
        IntroductionScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun VtfToolScreenPreview() {
    SourceAppToolsTheme {
        VtfToolScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun VpkToolScreenPreview() {
    SourceAppToolsTheme {
        VpkToolScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SourceAppToolsTheme {
        SettingsScreen(navController = rememberNavController())
    }
}