package com.jm.architecture_mvc

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jm.architecture_mvc.databinding.ListItemNoteBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteAdapter(
    private val onItemClick: (Int) -> Unit,
    private val onItemLongClick: (Int) -> Unit,
    private var notes: List<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(
            onItemClick,
            onItemLongClick,
            binding
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyItemRangeChanged(0, notes.size)
    }

    class NoteViewHolder(
        onItemClick: (Int) -> Unit,
        onItemLongClick: (Int) -> Unit,
        private val binding: ListItemNoteBinding
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
                }
                false
            }
        }

        fun bind(item: Note) {
            noteId = item.id
            with(binding) {
                tvTitle.text = item.title
                tvContent.text = item.content
                tvDate.text = convertLongToDate(item.timestamp)
                root.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, item.color))
            }
        }

        private fun convertLongToDate(ms: Long): String {
            val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            return formatter.format(Date(ms))
        }
    }
}