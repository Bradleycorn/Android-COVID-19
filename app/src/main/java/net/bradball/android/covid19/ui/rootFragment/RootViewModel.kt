package net.bradball.android.covid19.ui.rootFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.bradball.android.covid19.data.models.CountyCases
import net.bradball.android.covid19.data.repos.CovidDataRepository
import javax.inject.Inject

class RootViewModel @Inject constructor(private val covidDataRepository: CovidDataRepository) : ViewModel() {

    fun loadData() {
        viewModelScope.launch {
            covidDataRepository.fetchCountyData()
        }
    }


    fun getCases(county: String): LiveData<List<CountyCases>> {
        return covidDataRepository.getCases(county).asLiveData()
    }
}
