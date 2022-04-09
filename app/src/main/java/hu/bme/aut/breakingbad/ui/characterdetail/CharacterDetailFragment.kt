package hu.bme.aut.breakingbad.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.breakingbad.R
import hu.bme.aut.breakingbad.databinding.FragmentCharacterDetailBinding

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        return binding.root
    }

}