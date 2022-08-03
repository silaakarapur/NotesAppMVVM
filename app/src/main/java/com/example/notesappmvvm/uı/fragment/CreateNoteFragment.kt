package com.example.notesappmvvm.uı.fragment

import android.app.Application
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.notesappmvvm.R
import com.example.notesappmvvm.databinding.FragmentCreateNoteBinding
import com.example.notesappmvvm.databinding.FragmentHomeBinding
import com.example.notesappmvvm.model.Notes
import com.example.notesappmvvm.viewmodel.NotesViewModel
import java.lang.String.format
import java.sql.SQLOutput

import java.util.*


class CreateNoteFragment : Fragment() {
    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        priorityOption()
        binding.btnSave.setOnClickListener {
            createNote(it)

        }
        return binding.root
    }

    private fun createNote(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSub.text.toString()

        val notes = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d,yyyy", d.time)
        Log.e(TAG, "Create Notes:  ${notesDate}" )

        if (title.isNotEmpty() || subtitle.isNotEmpty() || notes.isNotEmpty()) {
            val data = Notes(
                null,
                title = title,
                subTitle = subtitle,
                notes = notes,
                data = notesDate.toString(),
                priority
            )

            viewModel.addNotes(data)
            Navigation.findNavController(it!!)
                .navigate(R.id.action_createNoteFragment_to_homeFragment)

        } else {
            Toast.makeText(requireContext(), "please fill in the blanks ", Toast.LENGTH_LONG).show()
        }

    }

    private fun priorityOption() {
        binding.color1Dot.setImageResource(R.drawable.ic_baseline_done_24)
        binding.color1Dot.setOnClickListener {
            binding.color1Dot.setImageResource(R.drawable.ic_baseline_done_24)
            priority = "2"

            binding.color2Dot.setImageResource(0)
            binding.color3Dot.setImageResource(0)

        }
        binding.color2Dot.setOnClickListener {
            priority = "1"
            binding.color2Dot.setImageResource(R.drawable.ic_baseline_done_24)

            binding.color1Dot.setImageResource(0)
            binding.color3Dot.setImageResource(0)
        }
        binding.color3Dot.setOnClickListener {
            priority = "3"
            binding.color3Dot.setImageResource(R.drawable.ic_baseline_done_24)

            binding.color2Dot.setImageResource(0)
            binding.color1Dot.setImageResource(0)

        }
    }


}


