package ru.noosle.harry_potter_mvi.ui.main.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.noosle.harry_potter_mvi.R
import ru.noosle.harry_potter_mvi.databinding.FragmentMainBinding
import ru.noosle.harry_potter_mvi.ui.main.MainViewModel
import ru.noosle.harry_potter_mvi.ui.main.adapters.PersonsAdapter
import ru.noosle.harry_potter_mvi.ui.main.adapters.SpellAdapter
import ru.noosle.harry_potter_mvi.ui.main.presenter.*


class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by activityViewModels()

    private val binding: FragmentMainBinding by viewBinding()

    private val personAdapter by lazy {
        PersonsAdapter(onClickListener = PersonsAdapter.OnClickListener { person, image, name, age, house, patronus ->
            val extras = FragmentNavigatorExtras(
                image to ViewCompat.getTransitionName(image).toString(),
                name to ViewCompat.getTransitionName(name).toString(),
                age to ViewCompat.getTransitionName(age).toString(),
                house to ViewCompat.getTransitionName(house).toString(),
                patronus to ViewCompat.getTransitionName(patronus).toString()
            )
            findNavController().navigate(
                R.id.action_mainFragment_to_detailFragment,
                bundleOf(DetailFragment.personTag to person),
                null,
                extras
            )
        })
    }

    private val spellAdapter by lazy {
        SpellAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        bindAdapters()
        setupObservers()
    }

    private fun bindAdapters() {
        binding.apply {
            personRecycler.adapter = personAdapter
            spellsRecycler.adapter = spellAdapter
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.all_person -> {
                        viewModel.send(AllPersonsEvent())
                        true
                    }
                    R.id.all_students -> {
                        viewModel.send(AllStudentsEvent())
                        true
                    }
                    R.id.staff -> {
                        viewModel.send(AllStaffEvent())
                        true
                    }
                    R.id.spells -> {
                        viewModel.send(AllSpellsEvent())
                        true
                    }
                    R.id.gryffindor,
                    R.id.hufflepuff,
                    R.id.slytherin,
                    R.id.ravenclaw -> {
                        viewModel.send(StudentsByFacultyEvent(faculty = menuItem.title.toString()))
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.personLive.collect { state ->
                when (state) {
                    is MainState.Loading -> binding.progress.isVisible = state.value
                    is MainState.Persons -> {
                        personAdapter.submitList(state.data)
                        binding.personRecycler.isVisible = true
                        binding.spellsRecycler.isVisible = false
                    }
                    is MainState.Spells -> {
                        spellAdapter.submitList(state.data)
                        binding.personRecycler.isVisible = false
                        binding.spellsRecycler.isVisible = true
                    }
                    else -> {}
                }
            }
        }
    }
}