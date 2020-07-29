package com.mnafis.agameofbattleships.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mnafis.agameofbattleships.BaseFragment
import com.mnafis.agameofbattleships.MainActivity
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.databinding.MenuFragmentBinding
import com.mnafis.agameofbattleships.utilities.EventBus
import com.mnafis.agameofbattleships.utilities.FragmentSwitchEvent
import com.mnafis.agameofbattleships.utilities.FragmentSwitchEvent.Scenario.GAME_DIFFICULTY_SET
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MenuFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eventBus: EventBus

    private lateinit var viewModel: MenuViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MenuViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MenuFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    private fun handleNavigation(fragmentSwitchEvent: FragmentSwitchEvent) {
        if (fragmentSwitchEvent.scenario == GAME_DIFFICULTY_SET) {
            val mainActivity = activity as MainActivity
            mainActivity.navController.navigate(R.id.fleetSetupFragment)
        }
    }

    override fun getDisposable(): CompositeDisposable = CompositeDisposable().apply {
        add(eventBus.fragmentNavigationSubject(MenuViewModel::class)
            .subscribe({ handleNavigation(it) }, { it.printStackTrace() }))
    }
}
