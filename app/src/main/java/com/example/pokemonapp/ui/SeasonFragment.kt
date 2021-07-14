package com.example.pokemonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentSeasonBinding
import com.example.pokemonapp.infra.common.OscillatingScrollListener
import com.example.pokemonapp.infra.common.extensions.setupError
import com.example.pokemonapp.infra.common.extensions.showError
import com.example.pokemonapp.infra.common.extensions.showRetry
import com.example.pokemonapp.ui.adapters.SeasonAdapter
import com.example.pokemonapp.ui.viewmodels.SeasonViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class SeasonFragment : Fragment() {

    private val binding by lazy { FragmentSeasonBinding.inflate(layoutInflater) }
    private val viewModel by sharedViewModel<SeasonViewModel>()
    private lateinit var adapter: SeasonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStart()
        setupRestart()
        setupAdapter()
        subscribeUi()
        getSeasons()
    }

    private fun setupStart() {
        binding.buttonStart.setOnClickListener {
            if (adapter.hasSeasonsSelected()) {
                viewModel.updateSeasons()
            } else showError("Not season selected")
        }
    }

    private fun setupRestart() {
        binding.buttonRestart.setOnClickListener {
            deleteDatabase()
        }
    }

    private fun setupAdapter() {
        adapter = SeasonAdapter {
            adapter.handleSeasonEnable(it)
        }
        binding.recyclerViewSeason.adapter = adapter
        // tentar a parada do reverse tando no submite qnto no recyclerviewja vol
        //binding.recyclerViewSeason.smoothScrollToPositionWithSpeed(adapter.itemCount)
        binding.recyclerViewSeason.addOnScrollListener(
            OscillatingScrollListener(resources.getDimensionPixelSize(R.dimen.size_16))
        )
    }

    private fun subscribeUi() {
        setupError(viewModel)
        setupSeasonListObserver()
        setupUpdatedSeasonsObserver()
        setupDeleteDatabaseObserver()
    }

    private fun setupSeasonListObserver() {
        viewModel.seasonList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {adapter.submit(it)}
            else showRetry("Not found seasons") {
                Timber.d("try again")
                getSeasons()
            }
        }
    }

    private fun setupUpdatedSeasonsObserver() {
        viewModel.updatedSeasons.observe(viewLifecycleOwner) {
            Timber.d("updated seasons are $it")
            if (it) onNavigateAction()
            else showError("Not seasons selected")
        }
    }

    private fun setupDeleteDatabaseObserver() {
        viewModel.isDeletedDatabase.observe(viewLifecycleOwner) {
            Timber.d("deleted databse is $it")
            getSeasons()
        }
    }

    private fun getSeasons() {
        Timber.d("get seasons")
        viewModel.seasons()
    }

    private fun deleteDatabase() {
        Timber.d("delete database")
        viewModel.deleteDatabase()
    }

    private fun onNavigateAction() {
        Timber.d("navigate to loading journey")
        val action = SeasonFragmentDirections.actionSplashFragmentToLoadingJourneyFragment()
        findNavController().navigate(action)
    }

}
