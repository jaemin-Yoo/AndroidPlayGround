package com.jm.architecture_mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.jm.architecture_mvc.databinding.ActivityAddEditNoteBinding

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

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