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

    val ROW_NUMBER = 10
    val COLUMN_NUMBER = 10

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBoard()
    }

    override fun getDisposable(): CompositeDisposable = CompositeDisposable().apply {
        add(
            eventBus.fragmentNavigationSubject(FleetSetupViewModel::class)
                .subscribe({}, {})
        )
    }

    private fun setupBoard() {
        val board: TableLayout = activity!!.findViewById(R.id.board_table)
        for (r in 1..ROW_NUMBER) {
            val row = TableRow(activity)
            row.apply {
                background = resources.getDrawable(R.drawable.test_background_with_border)
            }
            board.apply {
                setBackgroundColor(resources.getColor(R.color.colorAccent))
                setPadding(5, 5, 5, 5)
            }
            board.addView(row, TableLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1.0f))

            for (c in 1..COLUMN_NUMBER) {
                val cell = Cell(context!!, r, c).apply {
                    background = resources.getDrawable(R.drawable.cell_background)

//                    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(10, 10)
//                    params.setMargins(3, 1, 3, 1)
//                    layoutParams = params
                    setOnClickListener { handleCellClick(this) }
                }
//                row.addView(cell, TableLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1.0f))
                row.addView(cell)
            }
        }
    }

    private fun handleCellClick(cell: Cell) {
        Toast.makeText(
            activity,
            "Button Clicked: " + cell.rowNumber + " " + cell.columnNumber,
            Toast.LENGTH_LONG
        ).show()
    }
}
