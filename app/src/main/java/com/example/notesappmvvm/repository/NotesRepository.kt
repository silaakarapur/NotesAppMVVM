package com.example.notesappmvvm.repository

import androidx.lifecycle.LiveData
import com.example.notesappmvvm.Dao.NotesDao
import com.example.notesappmvvm.model.Notes

class NotesRepository(val  dao: NotesDao) {
    fun getAllData():LiveData<List<Notes>> =dao.getNotes()

    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()

    fun getHıghNotes():LiveData<List<Notes>> = dao.getHighNotes()

    fun getMediumData():LiveData<List<Notes>> = dao.getMediumNotes()
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