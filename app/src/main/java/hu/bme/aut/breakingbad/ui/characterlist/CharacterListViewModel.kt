package hu.bme.aut.breakingbad.ui.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.breakingbad.domain.GetCharactersUseCase
import hu.bme.aut.breakingbad.model.Character
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val characters = MutableLiveData(emptyList<Character>())
    val name = MutableLiveData("")
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    init {
        loadCharactersByName()
    }

    fun loadCharactersByName() {
        viewModelScope.launch {
            loading.value = true

            val term = name.value!!
            createFirebaseEvent(term)

            val results = getCharactersUseCase.invoke(term)
            characters.value = results
            empty.value = results.isEmpty()

            loading.value = false
        }
    }

    private fun createFirebaseEvent(term: String) {
        if (term.isNotBlank()) {
            Firebase.analytics.logEvent(FirebaseAnalytics.Event.SEARCH) {
                param(FirebaseAnalytics.Param.SEARCH_TERM, term)
            }
        }
    }

    fun throwException(): Nothing = TODO()
}