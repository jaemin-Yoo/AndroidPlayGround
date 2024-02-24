package com.jm.architecture_mvc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jm.architecture_mvc.databinding.ListItemNoteBinding

class NoteAdapter(private var notes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyItemRangeInserted(0, notes.size)
    }

    class NoteViewHolder(
        private val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                // 화면 이동
            }
        }

        fun bind(item: Note) {
            with(binding) {
                tvTitle.text = item.title
                tvContent.text = item.content
                tvDate.text = item.timestamp.toString()
            }
        }
    }
}