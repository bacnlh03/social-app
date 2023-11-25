import androidx.compose.runtime.Composable
import app.login.LoginScreen
import app.register.components.RegisterScreen
import app.utils.theme.SocialAppTheme

@Composable
fun App() {
    SocialAppTheme {
        RegisterScreen()
    }
}