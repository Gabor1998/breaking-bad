package hu.bme.aut.breakingbad.ui.characterdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.breakingbad.domain.GetCharacterUseCase
import hu.bme.aut.breakingbad.model.Character
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id")!!

    val character = MutableLiveData<Character>()

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        viewModelScope.launch {
            getCharacterUseCase(id)?.let { character.value = it } ?: showError()
        }
    }

    private fun showError() {
        TODO()
    }

}