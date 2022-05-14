package hu.bme.aut.breakingbad.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.breakingbad.domain.GetCharacterUseCase
import hu.bme.aut.breakingbad.domain.GetRandomQuoteByAuthor
import hu.bme.aut.breakingbad.model.Character
import hu.bme.aut.breakingbad.model.Quote
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getRandomQuoteByAuthor: GetRandomQuoteByAuthor
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id")!!

    val character = MutableLiveData<Character>()
    val quote = MutableLiveData<Quote>()
    val loading = MutableLiveData<Boolean>()

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        viewModelScope.launch {
            loading.value = true

            getCharacterUseCase(id)?.let {
                character.value = it
                getRandomQuoteByAuthor(it.name).onSuccess { result ->
                    quote.value = result
                }

                createFirebaseEvent(it)
            }

            loading.value = false
        }
    }

    private fun createFirebaseEvent(character: Character) {
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, character.id.toLong())
            param(FirebaseAnalytics.Param.ITEM_NAME, character.name)
        }
    }
}