package com.jm.architecture_mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import com.jm.architecture_mvc.databinding.ActivityAddEditNoteBinding

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBackgroundColor()
        setColorButtonListener()
        setToolbarListener()
    }

    private fun initBackgroundColor() {
        binding.background.setBackgroundColor(ContextCompat.getColor(this, R.color.soft_blue))
        binding.btnSoftBlue.isSelected = true
    }

    private fun setColorButtonListener() {
        val colorButtons = listOf(
            binding.btnSoftGreen to R.color.soft_green,
            binding.btnSoftBlue to R.color.soft_blue,
            binding.btnSoftPink to R.color.soft_pink,
            binding.btnSoftYellow to R.color.soft_yellow,
            binding.btnDarkGray to R.color.dark_gray
        )
        colorButtons.forEach { (button, color) ->
            button.setOnClickListener {
                colorButtons.forEach { (btn, _) ->
                    btn.isSelected = false
                }
                button.isSelected = true
                binding.background.setBackgroundColor(ContextCompat.getColor(this, color))
            }
        }
    }

    private fun setToolbarListener() {
        binding.toolbar.setNavigationOnClickListener {
            // Activity pop
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_done -> {
                    // 저장 + Activity pop
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_edit_note, menu)
        return true
    }
}