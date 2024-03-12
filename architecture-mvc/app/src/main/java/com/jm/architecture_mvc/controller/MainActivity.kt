package com.jm.architecture_mvc.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jm.architecture_mvc.view.NoteAdapter
import com.jm.architecture_mvc.model.NoteDao
import com.jm.architecture_mvc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val SAVE_NOTE = 1
const val UPDATE_NOTE = 2

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var noteDao: NoteDao
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLauncher()
        setListener()
        initNoteAdapter()
        initNotes()
    }

    private fun setLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val noteId = result.data!!.getIntExtra("noteId", -1)
                if (result.resultCode == SAVE_NOTE) {
                    insertNote(noteId)
                } else if (result.resultCode == UPDATE_NOTE) {
                    updateNote(noteId)
                }
            }
    }

    private fun setListener() {
        binding.fabAdd.setOnClickListener { moveNoteActivity() }
    }

    private fun initNoteAdapter() {
        noteAdapter = NoteAdapter(
            onItemClick = ::moveNoteActivity,
            onItemLongClick = ::showDeleteDialog,
            mutableListOf()
        )
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }
    }

    private fun moveNoteActivity(noteId: Int = -1) {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("noteId", noteId)
        resultLauncher.launch(intent)
    }

    private fun showDeleteDialog(noteId: Int) {
        AlertDialog.Builder(this).apply {
            setMessage("삭제하시겠습니까?")
            setPositiveButton("예") { _, _ -> deleteNote(noteId) }
            setNegativeButton("아니오") { dialog, _ -> dialog.dismiss() }
            show()
        }
    }

    private fun initNotes() = lifecycleScope.launch {
        val notes = withContext(Dispatchers.IO) {
            noteDao.getNotes()
        }
        noteAdapter.initNotes(notes)
    }

    private fun insertNote(noteId: Int) = lifecycleScope.launch {
        val note = withContext(Dispatchers.IO) {
            noteDao.getNote(noteId)
        }
        noteAdapter.insertNote(note)
        binding.rvNotes.scrollToPosition(0)
    }

    private fun updateNote(noteId: Int) = lifecycleScope.launch {
        val note = withContext(Dispatchers.IO) {
            noteDao.getNote(noteId)
        }
        noteAdapter.updateNote(note)
    }

    private fun deleteNote(noteId: Int) = lifecycleScope.launch {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(noteId)
        }
        noteAdapter.removeNote(noteId)
        showToast("삭제되었습니다.")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}