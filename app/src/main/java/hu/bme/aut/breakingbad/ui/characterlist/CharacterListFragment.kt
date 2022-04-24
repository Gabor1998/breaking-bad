package hu.bme.aut.breakingbad.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.breakingbad.R
import hu.bme.aut.breakingbad.databinding.FragmentCharacterListBinding

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false)
        binding.detailButton.setOnClickListener {
            findNavController().navigate(CharacterListFragmentDirections.actionToCharacterDetail())
        }
        return binding.root
    }
}