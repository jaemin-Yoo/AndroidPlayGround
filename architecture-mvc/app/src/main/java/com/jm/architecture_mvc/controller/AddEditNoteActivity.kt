package com.jm.architecture_mvc.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
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

    private var noteId: Int = -1
    private var currentColor = R.color.soft_blue
    private var timestamp: Long = 0L
    private lateinit var colorButtons: List<Pair<ImageButton, Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteId = intent.getIntExtra("noteId", -1)
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
            with(binding) {
                etTitle.setText(note.title)
                etContent.setText(note.content)
                updateColorSelection(note.color)
            }
        }
    }

    private suspend fun getNote(): Note {
        return if (noteId == -1) {
            Note(
                id = null,
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

        val timestamp = if (noteId == -1) System.currentTimeMillis() else this.timestamp
        val note = Note(
            id = noteId,
            title = title,
            content = content,
            timestamp = timestamp,
            color = currentColor
        )

        if (noteId == -1) {
            saveNote(note)
        } else {
            updateNote(note)
        }
    }

    private fun saveNote(note: Note) {
        lifecycleScope.launch {
            noteDao.insertNote(note)
            showToast("저장 완료")

            setResult(SAVE_NOTE)
            finish()
        }
    }

    private fun updateNote(note: Note) {
        lifecycleScope.launch {
            noteDao.updateNote(note)
            showToast("업데이트 완료")

            val intent = Intent().apply { putExtra("noteId", note.id) }
            setResult(UPDATE_NOTE, intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_edit_note, menu)
        return true
    }
}