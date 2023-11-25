import androidx.compose.runtime.Composable
import app.di.appModule
import app.login.LoginScreen
import app.utils.theme.SocialAppTheme
import cafe.adriel.voyager.navigator.Navigator
import features.di.featuresModule
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(
                appModule() + featuresModule()
            )
        }
    ) {
        SocialAppTheme {
            Navigator(LoginScreen)
        }
    }
}