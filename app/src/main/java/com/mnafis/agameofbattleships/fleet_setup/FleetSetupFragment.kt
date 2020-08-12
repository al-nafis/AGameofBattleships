package com.mnafis.agameofbattleships.fleet_setup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mnafis.agameofbattleships.BaseFragment
import com.mnafis.agameofbattleships.Cell
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.databinding.FleetSetupFragmentBinding
import com.mnafis.agameofbattleships.utilities.EventBus
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FleetSetupFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var eventBus: EventBus

    lateinit var viewModel: FleetSetupViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FleetSetupViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FleetSetupFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fleet_setup_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun getDisposable(): CompositeDisposable = CompositeDisposable().apply {
        add(
            eventBus.fragmentNavigationSubject(FleetSetupViewModel::class)
                .subscribe({}, {})
        )
    }
}
