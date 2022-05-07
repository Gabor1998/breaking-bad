package hu.bme.aut.breakingbad.ui.characterlist

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.breakingbad.model.Character

class CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
}