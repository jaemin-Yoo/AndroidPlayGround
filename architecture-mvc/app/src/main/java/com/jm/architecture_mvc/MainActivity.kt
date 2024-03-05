package com.jm.architecture_mvc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNoteAdapter()
        setListener()
        fetchNotes()
    }

    private fun setListener() {
        binding.fabAdd.setOnClickListener { moveNoteActivity() }
    }

    private fun initNoteAdapter() {
        noteAdapter = NoteAdapter(
            onItemClick = { moveNoteActivity() },
            onItemLongClick = {  },
            emptyList()
        )
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }
    }

    private fun moveNoteActivity() {
        val intent = Intent(this, AddEditNoteActivity::class.java)
        startActivity(intent)
    }

    private fun fetchNotes() {
        CoroutineScope(Dispatchers.IO).launch {
            val notes = noteDao.getNotes()

            withContext(Dispatchers.Main) {
                noteAdapter.updateNotes(notes)
            }
        }
    }
}