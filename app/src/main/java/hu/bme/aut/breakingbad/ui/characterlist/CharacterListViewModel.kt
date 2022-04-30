package hu.bme.aut.breakingbad.ui.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        loadCharactersByName()
    }

    fun loadCharactersByName() {
        viewModelScope.launch {
            characters.value = getCharactersUseCase.invoke(name.value.orEmpty())
        }
    }
}