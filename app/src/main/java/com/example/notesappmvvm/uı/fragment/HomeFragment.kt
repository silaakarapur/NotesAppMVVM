package com.example.notesappmvvm.uı.fragment

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesappmvvm.R
import com.example.notesappmvvm.databinding.FragmentHomeBinding
import com.example.notesappmvvm.model.Notes
import com.example.notesappmvvm.uı.adapter.NotesAdapter
import com.example.notesappmvvm.viewmodel.NotesViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            for (i in notesList) {
                oldMyNotes = notesList as ArrayList<Notes>
                adapter= NotesAdapter(requireContext(),notesList)
                binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAddNotes.adapter = adapter
            }

        }
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)

        }
        filterAction()
        searcViewAction()
        return binding.root

    }
    private fun searcViewAction(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(po: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(po: String?): Boolean {
                notesFiltering(po)
                return true
            }
        })
    }

    private fun notesFiltering(po: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.contains(po!!) || i.subTitle.contains(po!!)) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

    fun filterAction() {
        binding.allFilter.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                for (i in notesList) {
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter= NotesAdapter(requireContext(),notesList)

                    binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rcvAddNotes.adapter = adapter
                }

            }
        }
        binding.filterHigh.setOnClickListener {
            viewModel.getHıghNotes().observe(viewLifecycleOwner) { notesList ->
                for (i in notesList) {
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter= NotesAdapter(requireContext(),notesList)

                    binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rcvAddNotes.adapter = adapter
                }

            }
        }
        binding.filterLow.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                for (i in notesList) {
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter= NotesAdapter(requireContext(),notesList)

                    binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rcvAddNotes.adapter = adapter
                }

            }
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumData().observe(viewLifecycleOwner) { notesList ->
                for (i in notesList) {
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter= NotesAdapter(requireContext(),notesList)
                    binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rcvAddNotes.adapter = adapter
                }

            }
        }
    }

}