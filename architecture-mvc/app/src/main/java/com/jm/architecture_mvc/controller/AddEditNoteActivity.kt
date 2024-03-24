package com.jm.architecture_mvc.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.jm.architecture_mvc.model.Note
import com.jm.architecture_mvc.model.NoteDao
import com.jm.architecture_mvc.R
import com.jm.architecture_mvc.controller.ToastUtils.showToast
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

    private var noteId: Long = -1L
    private var currentColor = R.color.soft_blue
    private var timestamp: Long = 0L
    private lateinit var colorButtons: List<Pair<ImageButton, Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteId = intent.getLongExtra("noteId", -1L)
        setupUI()
    }

    private fun setupUI() {
        setToolbarListener()
        configureColorButtons()
        updateUIWithNoteData()
    }

    private fun setToolbarListener() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_done -> {
                    handleNoteAction()
                    true
                }

                else -> false
            }
        }
    }

    private fun configureColorButtons() {
        colorButtons = listOf(
            binding.btnSoftGreen to R.color.soft_green,
            binding.btnSoftBlue to R.color.soft_blue,
            binding.btnSoftPink to R.color.soft_pink,
            binding.btnSoftYellow to R.color.soft_yellow,
            binding.btnDarkGray to R.color.dark_gray
        )

        colorButtons.forEach { (button, color) ->
            button.setOnClickListener {
                updateColorSelection(color) // 특정 색상 클릭 시 배경 색상 변경
            }
        }
    }

    private fun updateUIWithNoteData() {
        CoroutineScope(Dispatchers.Main).launch {
            val note = getNote()
            currentColor = note.color
            timestamp = note.timestamp
            with(binding) {
                etTitle.setText(note.title)
                etContent.setText(note.content)
                updateColorSelection(note.color)
            }
        }
    }

    private suspend fun getNote(): Note {
        return if (noteId == -1L) {
            Note(
                id = 0L,
                title = "",
                content = "",
                timestamp = 0L,
                color = R.color.soft_blue
            )
        } else {
            withContext(Dispatchers.IO) { noteDao.getNote(noteId) }
        }
    }

    private fun updateColorSelection(targetColor: Int) {
        colorButtons.forEach { (btn, color) ->
            btn.isSelected = targetColor == color
        }
        currentColor = targetColor
        binding.background.setBackgroundColor(ContextCompat.getColor(this, targetColor))
    }

    private fun handleNoteAction() {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        if (title.isBlank() || content.isBlank()) {
            showToast("빈 칸이 존재합니다.")
            return
        }

        val id = if (noteId == -1L) 0L else noteId
        val timestamp = if (noteId == -1L) System.currentTimeMillis() else this.timestamp
        val note = Note(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp,
            color = currentColor
        )

        if (noteId == -1L) {
            saveNote(note)
        } else {
            updateNote(note)
        }
    }

    private fun saveNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            val noteId = noteDao.insertNote(note)
            withContext(Dispatchers.Main) {
                val intent = Intent().apply { putExtra("noteId", noteId) }
                setResult(SAVE_NOTE, intent)
                finish()
            }
        }
        showToast("저장 완료")
    }

    private fun updateNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.updateNote(note)
            withContext(Dispatchers.Main) {
                val intent = Intent().apply { putExtra("noteId", note.id) }
                setResult(UPDATE_NOTE, intent)
                finish()
            }
        }
        showToast("업데이트 완료")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_edit_note, menu)
        return true
    }
}