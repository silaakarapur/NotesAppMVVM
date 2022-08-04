package com.example.notesappmvvm.uı.fragment

import android.content.ContentValues
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesappmvvm.R
import com.example.notesappmvvm.databinding.FragmentEditNotesBinding
import com.example.notesappmvvm.model.Notes
import com.example.notesappmvvm.viewmodel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class EditNotesFragment : Fragment() {
    var priority: String = "1"
    val edtnotes by navArgs<EditNotesFragmentArgs>()
    val viewModel: NotesViewModel by viewModels()
    lateinit var binding: FragmentEditNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        binding.edtTitle.setText(edtnotes.data.title)
        binding.edtSub.setText(edtnotes.data.subTitle)
        binding.edtNotes.setText(edtnotes.data.notes)


        priorityOption()

        binding.btnEdit.setOnClickListener {
            updateNotes(it)
        }
        binding.btnDelete.setOnClickListener {
            deleteNotes()
        }
        return binding.root
    }

    private fun deleteNotes() {
        val buttomSheat: BottomSheetDialog = BottomSheetDialog(requireContext())
        buttomSheat.setContentView(R.layout.dialog_details)
        buttomSheat.show()
        val textViewYes = buttomSheat.findViewById<TextView>(R.id.textViewYes)
        var textViewNo = buttomSheat.findViewById<TextView>(R.id.textViewNo)

        textViewYes?.setOnClickListener {
            viewModel.deleteNotes(edtnotes.data.id!!)
            buttomSheat.dismiss()
            //   Navigation.findNavController(it).navigate(R.id.action_editNotesFragment_to_homeFragment)
            activity?.onBackPressed()
            val action = AnimationUtils.loadAnimation(context, R.anim.enter_left_to_right)
            binding.editNotesFragment.startAnimation(action)
        }
        textViewNo?.setOnClickListener {
            buttomSheat.dismiss()
        }

    }

    private fun updateNotes(it: View) {

        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSub.text.toString()

        val notes = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d,yyyy", d.time)
        Log.e(ContentValues.TAG, "Create Notes:  ${notesDate}")

        if (title.isNotEmpty() || subtitle.isNotEmpty() || notes.isNotEmpty()) {
            val data = Notes(
                edtnotes.data.id,
                title = title,
                subTitle = subtitle,
                notes = notes,
                data = notesDate.toString(),
                priority
            )

            viewModel.updateNotes(data)
            Navigation.findNavController(it!!)
                .navigate(R.id.action_editNotesFragment_to_homeFragment)
            Toast.makeText(requireContext(), "Notes Updated Succesfully ", Toast.LENGTH_LONG).show()
            val action = AnimationUtils.loadAnimation(context, R.anim.enter_left_to_right)
            binding.editNotesFragment.startAnimation(action)
        } else {
            Toast.makeText(requireContext(), "please fill in the blanks ", Toast.LENGTH_LONG).show()
        }


    }

    private fun priorityOption() {
        binding.color1Dot.setImageResource(R.drawable.ic_baseline_done_24)
        binding.color1Dot.setOnClickListener {
            priority = "1"
            binding.color1Dot.setImageResource(R.drawable.ic_baseline_done_24)
            binding.color2Dot.setImageResource(0)
            binding.color3Dot.setImageResource(0)

        }
        binding.color2Dot.setOnClickListener {
            priority = "2"
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