package com.mnafis.agameofbattleships

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    private lateinit var disposable: CompositeDisposable

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        disposable = getDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    open fun getDisposable(): CompositeDisposable = CompositeDisposable()
}