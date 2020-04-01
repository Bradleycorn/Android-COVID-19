package net.bradball.android.covid19.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.bradball.android.covid19.ui.rootFragment.RootViewModel

@Suppress("unused")
@Module
abstract class ViewModelBuildersModule {

    @Binds
    @IntoMap
    @ViewModelKey(RootViewModel::class)
    abstract fun bindRangeListViewModel(rangeListViewModel: RootViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
