package com.dobriyanovmp.rock_paper_scissors_lizard_spok

import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dobriyanovmp.rock_paper_scissors_lizard_spok.databinding.ActivityMainBinding
import com.dobriyanovmp.rock_paper_scissors_lizard_spok.databinding.AlertLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var playerWinConditions: List<Pair<Int, Int>> =
            listOf(
                (1 to 2), (2 to 3), (3 to 4), (4 to 5), (5 to 1),
                (1 to 4), (2 to 5), (3 to 1), (4 to 2), (5 to 3)
            )
    private var playersChose: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ScissorsButton.setOnClickListener { buttonClicker(binding.ScissorsButton.id) }
        binding.PaperButton.setOnClickListener { buttonClicker(binding.PaperButton.id) }
        binding.RockButton.setOnClickListener { buttonClicker(binding.RockButton.id) }
        binding.LizardButton.setOnClickListener { buttonClicker(binding.LizardButton.id) }
        binding.SpokButton.setOnClickListener { buttonClicker(binding.SpokButton.id) }
        binding.RulesButton.setOnClickListener { showRules() }
    }

    private fun buttonClicker(buttonId: Int) {
        playersChose = when (buttonId) {
            binding.ScissorsButton.id -> 1
            binding.PaperButton.id -> 2
            binding.RockButton.id -> 3
            binding.LizardButton.id -> 4
            binding.SpokButton.id -> 5
            else -> 0
        }
        getGameResults()
    }

    private fun playerWinIn(conditions: Pair<Int, Int>): Boolean {
        return playerWinConditions.contains(conditions)
    }

    private fun getGameResults() {
        val computerChose: Int = kotlin.random.Random.nextInt(1, 6)
        val gameConditions: Pair<Int, Int> = (playersChose to computerChose)
        showComputerChose(computerChose)
        if (computerChose == playersChose) {
            showDraw()
            return
        }

        if (playerWinIn(gameConditions)) {
            showWin()
        }
        else {
            showLose()
        }
    }

    private fun showWin() {
        binding.ResultText.setBackgroundColor(getColor(R.color.green))
        binding.ResultText.setText(R.string.winText)
    }

    private fun showLose() {
        binding.ResultText.setBackgroundColor(getColor(R.color.red))
        binding.ResultText.setText(R.string.loseText)
    }

    private fun showDraw() {
        binding.ResultText.setBackgroundColor(getColor(R.color.gray))
        binding.ResultText.setText(R.string.drawText)
    }

    private fun showComputerChose(chose: Int) {
        binding.ComputerChoose.setImageResource(
            when (chose) {
                1 -> R.drawable.scissors
                2 -> R.drawable.paper
                3 -> R.drawable.rock
                4 -> R.drawable.lizard
                5 -> R.drawable.spok
                else -> R.drawable.ic_launcher_background
            }
        )
    }

    private fun showRules() {
        val dialogBinding = AlertLayoutBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Rules")
            .setView(dialogBinding.root)
            .create()
        dialog.show()
    }
}