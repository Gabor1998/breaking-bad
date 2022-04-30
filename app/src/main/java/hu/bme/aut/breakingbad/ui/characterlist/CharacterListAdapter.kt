package hu.bme.aut.breakingbad.ui.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.breakingbad.databinding.ItemCharacterBinding
import hu.bme.aut.breakingbad.model.Character

class CharacterListAdapter(
    private val onClick: (Character) -> Unit
) : ListAdapter<Character, CharacterListAdapter.CharacterViewHolder>(CharacterDiffUtil()) {

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        private val onClick: (Character) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
            binding.item.setOnClickListener { onClick(character) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(ItemCharacterBinding.inflate(layoutInflater), onClick)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}