package com.mnafis.agameofbattleships.menu

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mnafis.agameofbattleships.BaseFragment
import com.mnafis.agameofbattleships.MainActivity
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.databinding.MenuFragmentBinding
import com.mnafis.agameofbattleships.utilities.EventBus
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel::class.java)
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun navigateToPauseMenu() {
        val textView: TextView = activity!!.findViewById(R.id.play_button)
        textView.text = "Nice"
        val mainActivity = activity as MainActivity
        mainActivity.navController.navigate(R.id.pauseFragment)
    }

    override fun getDisposable(): CompositeDisposable = CompositeDisposable().apply {
        add(eventBus.fragmentNavigationSubject(MenuViewModel::class)
            .subscribe({ navigateToPauseMenu() }, { it.printStackTrace() }))
    }
}
