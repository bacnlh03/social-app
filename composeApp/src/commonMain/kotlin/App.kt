import androidx.compose.runtime.Composable
import app.login.LoginScreen
import app.utils.theme.SocialAppTheme
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun App() {
    SocialAppTheme {
        Navigator(LoginScreen)
    }
}