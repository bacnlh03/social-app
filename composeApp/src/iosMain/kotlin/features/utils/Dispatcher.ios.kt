package features.utilsimport kotlinx.coroutines.CoroutineDispatcherimport kotlinx.coroutines.Dispatchersclass IOSDispatcher: DispatcherProvider {	override val main: CoroutineDispatcher		get() = Dispatchers.Main	override val io: CoroutineDispatcher		get() = Dispatchers.Default}actual fun provideDispatcher(): DispatcherProvider = IOSDispatcher()