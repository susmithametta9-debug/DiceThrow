package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

private const val DIESIDE = "sidenumber"
private const val ROLL_KEY = "roll"

class DieFragment : Fragment() {

    private var currentRoll: Int = 0
    private lateinit var dieTextView: TextView
    var dieSides: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = if (this > 0) this else 6
            }
        }

        savedInstanceState?.run {
            currentRoll = getInt(ROLL_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        if (currentRoll > 0) {
            dieTextView.text = currentRoll.toString()
        } else {
            throwDie()
        }

        view.setOnClickListener {
            throwDie()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ROLL_KEY, currentRoll)
    }

    fun throwDie() {
        currentRoll = Random.nextInt(1, dieSides + 1)
        dieTextView.text = currentRoll.toString()
    }

    companion object {
        fun newInstance(sides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
        }
    }
}