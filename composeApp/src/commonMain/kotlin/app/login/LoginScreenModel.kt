package app.loginimport cafe.adriel.voyager.core.model.StateScreenModelimport cafe.adriel.voyager.core.model.screenModelScopeimport dev.gitlive.firebase.FirebaseExceptionimport features.auth.domain.model.AuthRequestimport features.auth.domain.useCases.LoginUseCaseimport kotlinx.coroutines.flow.updateimport kotlinx.coroutines.launchclass LoginScreenModel(	private val loginUseCase: LoginUseCase) : StateScreenModel<LoginState>(LoginState()) {	fun updateEmail(value: String) {		screenModelScope.launch {			mutableState.update {				it.copy(email = value)			}		}	}	fun updatePassword(value : String) {		screenModelScope.launch {			mutableState.update {				it.copy(password = value)			}		}	}	fun login() {		screenModelScope.launch {			mutableState.update {				it.copy(					isLoading = true,					authError = null				)			}			val email = state.value.email			val password = state.value.password			if (!isValid(email, password)) {				mutableState.update {					it.copy(						emailError = validateEmail(email),						passwordError = validatePassword(password),						isLoading = false,						isSuccess = false					)				}			}			else {				try {					loginUseCase(						request = AuthRequest(							email = email,							password = password						)					)					mutableState.update {						it.copy(							emailError = null,							passwordError = null,							isLoading = false,							isSuccess = true,							authError = "Sign in success" // Remove later						)					}				} catch (e: FirebaseException) {					mutableState.update {						it.copy(							emailError = null,							passwordError = null,							isLoading = false,							isSuccess = false,							authError = e.message						)					}				} catch (e: Exception) {					mutableState.update {						it.copy(							emailError = null,							passwordError = null,							isLoading = false,							isSuccess = false,							authError = "Something went wrong"						)					}					e.printStackTrace()				}			}		}	}	private fun isValid(email: String, password: String): Boolean {		return validateEmail(email) == null && validatePassword(password) == null	}	private fun validateEmail(email: String): String? {		if (email.isBlank()) {			return "Email cannot be blank"		}		if (!email.contains('@')) {			return "Invalid email"		}		return null	}	private fun validatePassword(password: String): String? {		if (password.isBlank()) {			return "Password cannot be blank"		}		if (password.length < 6) {			return "Password should be at least 6 characters"		}		return null	}}