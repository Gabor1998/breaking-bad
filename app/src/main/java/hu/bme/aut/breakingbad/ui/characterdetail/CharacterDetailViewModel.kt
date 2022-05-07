package hu.bme.aut.breakingbad.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            }

            loading.value = false
        }
    }
}