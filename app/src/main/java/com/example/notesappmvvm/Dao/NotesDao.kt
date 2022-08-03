package com.example.notesappmvvm.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesappmvvm.model.Notes
import java.net.IDN

@Dao
interface NotesDao {
    @Query("Select*From Notes")
    fun getNotes():LiveData<List<Notes>>

    @Query("Select*From Notes WHERE priority=3")
    fun getHighNotes():LiveData<List<Notes>>

    @Query("Select*From Notes WHERE priority=1")
    fun getMediumNotes():LiveData<List<Notes>>

    @Query("Select*From Notes WHERE priority=2")
    fun getLowNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)
    @Query("delete from notes where id=:id")
    fun deleteNotes(id:Int)
    @Update
    fun updateNotes(notes: Notes)
}