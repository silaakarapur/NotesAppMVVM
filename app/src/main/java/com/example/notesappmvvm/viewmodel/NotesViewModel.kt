package com.example.notesappmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesappmvvm.database.NotesDatabase
import com.example.notesappmvvm.model.Notes
import com.example.notesappmvvm.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var repository: NotesRepository


    init {
        val dao = NotesDatabase.getDatabaseInstance(application).nyNotesDao()
        repository = NotesRepository(dao)

    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllData()

    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()

    fun getHıghNotes():LiveData<List<Notes>> = repository.getHıghNotes()

    fun getMediumData():LiveData<List<Notes>> = repository.getMediumData()
    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }

}