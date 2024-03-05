package com.jm.architecture_mvc

import android.app.Activity
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
                    val timestamp = System.currentTimeMillis()
                    val color = currentColor
                    if (title.isBlank() || content.isBlank()) {
                        Toast.makeText(this, "빈 칸이 존재합니다.", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener false
                    }
                    saveNote(title, content, timestamp, color)
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()
                    setResult(Activity.RESULT_OK)
                    finish()
                    true
                }

                else -> false
            }
        }
    }

    private fun saveNote(title: String, content: String, timestamp: Long, color: Int) =
        CoroutineScope(Dispatchers.IO).launch {
            val note = Note(
                title = title,
                content = content,
                timestamp = timestamp,
                color = color
            )
            noteDao.insertNote(note)
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_edit_note, menu)
        return true
    }
}