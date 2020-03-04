package com.mnafis.agameofbattleships.pause

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mnafis.agameofbattleships.BaseFragment
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.utilities.EventBus
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PauseFragment : BaseFragment() {

    @Inject
    lateinit var eventBus: EventBus

    lateinit var viewModel: PauseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pause_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PauseViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun getDisposable(): CompositeDisposable = CompositeDisposable().apply {
        add(eventBus.fragmentNavigationSubject(PauseViewModel::class)
            .subscribe( {}, {} ))
    }
}
