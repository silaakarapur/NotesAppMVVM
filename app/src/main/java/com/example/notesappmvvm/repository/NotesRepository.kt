package com.example.notesappmvvm.repository

import androidx.lifecycle.LiveData
import com.example.notesappmvvm.Dao.NotesDao
import com.example.notesappmvvm.model.Notes

class NotesRepository(val  dao: NotesDao) {
    fun getAllData():LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}