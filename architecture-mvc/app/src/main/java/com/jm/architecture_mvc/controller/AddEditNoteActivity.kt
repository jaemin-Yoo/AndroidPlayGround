package com.jm.architecture_mvc.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jm.architecture_mvc.model.Note
import com.jm.architecture_mvc.model.NoteDao
import com.jm.architecture_mvc.R
import com.jm.architecture_mvc.databinding.ActivityAddEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding

    @Inject
    lateinit var noteDao: NoteDao
    private var currentColor = R.color.soft_blue

    private var noteId: Int = -1
    private lateinit var colorButtons: List<Pair<ImageButton, Int>>
    private var timestamp: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        colorButtons = listOf(
            binding.btnSoftGreen to R.color.soft_green,
            binding.btnSoftBlue to R.color.soft_blue,
            binding.btnSoftPink to R.color.soft_pink,
            binding.btnSoftYellow to R.color.soft_yellow,
            binding.btnDarkGray to R.color.dark_gray
        )
        noteId = intent.getIntExtra("noteId", -1)
        fetchNote(noteId)
        setColorButtonListener()
        setToolbarListener()
    }

    private fun fetchNote(noteId: Int) {
        if (noteId != -1) {
            CoroutineScope(Dispatchers.IO).launch {
                val note = noteDao.getNote(noteId)

                withContext(Dispatchers.Main) {
                    currentColor = note.color
                    timestamp = note.timestamp
                    with(binding) {
                        etTitle.setText(note.title)
                        etContent.setText(note.content)
                        binding.background.setBackgroundColor(
                            ContextCompat.getColor(
                                this@AddEditNoteActivity,
                                note.color
                            )
                        )
                        colorButtons.forEach { (button, color) ->
                            button.isSelected = note.color == color
                        }
                    }
                }
            }
        }

        binding.background.setBackgroundColor(
            ContextCompat.getColor(
                this@AddEditNoteActivity,
                currentColor
            )
        )
        colorButtons.forEach { (button, color) ->
            button.isSelected = currentColor == color
        }
    }

    private fun setColorButtonListener() {
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
                    val color = currentColor
                    if (title.isBlank() || content.isBlank()) {
                        Toast.makeText(this, "빈 칸이 존재합니다.", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener false
                    }

                    val message = if (noteId == -1) {
                        saveNote(title, content, System.currentTimeMillis(), color)
                        setResult(1)
                        "저장 완료"
                    } else {
                        updateNote(title, content, timestamp, color)
                        val intent = Intent()
                        intent.putExtra("noteId", noteId)
                        setResult(2, intent)
                        "업데이트 완료"
                    }
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

    private fun updateNote(title: String, content: String, timestamp: Long, color: Int) =
        CoroutineScope(Dispatchers.IO).launch {
            val note = Note(
                id = noteId,
                title = title,
                content = content,
                timestamp = timestamp,
                color = color
            )
            noteDao.updateNode(note)
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_edit_note, menu)
        return true
    }
}