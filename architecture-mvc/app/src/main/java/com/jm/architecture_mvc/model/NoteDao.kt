package com.jm.architecture_mvc.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY timestamp DESC")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id = :noteId")
    fun getNote(noteId: Int): Note

    @Update
    fun updateNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query("DELETE FROM note WHERE id = :noteId")
    fun deleteNote(noteId: Int)
}