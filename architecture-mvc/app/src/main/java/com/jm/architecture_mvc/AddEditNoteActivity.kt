package com.jm.architecture_mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jm.architecture_mvc.databinding.ActivityAddEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding

    @Inject
    lateinit var noteDao: NoteDao
    private var currentColor = R.color.soft_blue

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
                currentColor = color
                binding.background.setBackgroundColor(ContextCompat.getColor(this, color))
            }
        }
    }

    private fun setToolbarListener() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_done -> {
                    val title = binding.etTitle.text.toString()
                    val content = binding.etContent.text.toString()
                    if (title.isBlank() || content.isBlank()) {
                        Toast.makeText(this, "빈 칸이 존재합니다.", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener false
                    }
                    saveNote()
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()
                    finish()
                    true
                }

                else -> false
            }
        }
    }

    private fun saveNote() = CoroutineScope(Dispatchers.IO).launch {
        val note = Note(
            title = binding.etTitle.text.toString(),
            content = binding.etContent.text.toString(),
            timestamp = System.currentTimeMillis(),
            color = currentColor
        )
        noteDao.insertNote(note)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_edit_note, menu)
        return true
    }
}