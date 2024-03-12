package com.jm.architecture_mvc.view

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jm.architecture_mvc.databinding.ListItemNoteBinding
import com.jm.architecture_mvc.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter(
    private val onItemClick: (Int) -> Unit,
    private val onItemLongClick: (Int) -> Unit,
    private val notes: MutableList<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(
            binding,
            onItemClick,
            onItemLongClick
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    fun initNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyItemRangeChanged(0, notes.size)
    }

    fun insertNote(note: Note) {
        notes.add(0, note)
        notifyItemInserted(0)
    }

    fun updateNote(note: Note) {
        val pos = notes.indexOfFirst { it.id == note.id }
        if (pos >= 0) {
            notes[pos] = note
            notifyItemChanged(pos)
        }
    }

    fun removeNote(noteId: Int) {
        val pos = notes.indexOfFirst { it.id == noteId }
        if (pos >= 0) {
            notes.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    class NoteViewHolder(
        private val binding: ListItemNoteBinding,
        onItemClick: (Int) -> Unit,
        onItemLongClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var noteId: Int? = null

        init {
            itemView.setOnClickListener {
                noteId?.let {
                    onItemClick(it)
                }
            }

            itemView.setOnLongClickListener {
                noteId?.let {
                    onItemLongClick(it)
                    true
                } ?: false
            }
        }

        fun bind(item: Note) {
            noteId = item.id
            with(binding) {
                tvTitle.text = item.title
                tvContent.text = item.content
                tvDate.text = convertLongToDate(item.timestamp)
                root.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(itemView.context, item.color))
            }
        }

        private fun convertLongToDate(ms: Long): String {
            val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            return formatter.format(Date(ms))
        }
    }
}