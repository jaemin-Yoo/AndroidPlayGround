package com.jm.architecture_mvc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.jm.architecture_mvc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var noteDao: NoteDao
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLauncher()
        setListener()
        initNoteAdapter()
        fetchNotes()
    }

    private fun setLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == 1) {
                    fetchNotes()
                } else if (result.resultCode == 2) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val noteId = result.data!!.getIntExtra("noteId", -1)
                        val note = noteDao.getNote(noteId)

                        withContext(Dispatchers.Main) {
                            noteAdapter.updateNote(note)
                        }
                    }
                }
            }
    }

    private fun setListener() {
        binding.fabAdd.setOnClickListener { moveNoteActivity() }
    }

    private fun initNoteAdapter() {
        noteAdapter = NoteAdapter(
            onItemClick = { moveNoteActivity(it) },
            onItemLongClick = { },
            mutableListOf()
        )
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }
    }

    private fun moveNoteActivity(noteId: Int = -1) {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("noteId", noteId)
        resultLauncher.launch(intent)
    }

    private fun fetchNotes() = CoroutineScope(Dispatchers.IO).launch {
        val notes = noteDao.getNotes()

        withContext(Dispatchers.Main) {
            noteAdapter.insertNotes(notes)
            binding.rvNotes.scrollToPosition(0)
        }
    }
}