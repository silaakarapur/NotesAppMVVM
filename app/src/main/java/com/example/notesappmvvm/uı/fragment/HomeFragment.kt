package com.example.notesappmvvm.uı.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesappmvvm.R
import com.example.notesappmvvm.databinding.FragmentHomeBinding
import com.example.notesappmvvm.uı.adapter.NotesAdapter
import com.example.notesappmvvm.viewmodel.NotesViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getData().observe(viewLifecycleOwner) { notesList ->
            for (i in notesList) {
                binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)

        }
        filterAction()
        return binding.root
    }
fun filterAction(){
    binding.allFilter.setOnClickListener {
        viewModel.getData().observe(viewLifecycleOwner) { notesList ->
            for (i in notesList) {
                binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }
    }
    binding.filterHigh.setOnClickListener {
        viewModel.getHıghNotes().observe(viewLifecycleOwner) { notesList ->
            for (i in notesList) {
                binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }
    }
    binding.filterLow.setOnClickListener {

        viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
            for (i in notesList) {
                binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }
    }
    binding.filterMedium.setOnClickListener {
        viewModel.getMediumData().observe(viewLifecycleOwner) { notesList ->
            for (i in notesList) {
                binding.rcvAddNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAddNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }
    }
}

}