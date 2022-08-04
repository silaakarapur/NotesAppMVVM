package com.example.notesappmvvm.uı.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvvm.R
import com.example.notesappmvvm.databinding.ItemNotesBinding
import com.example.notesappmvvm.model.Notes
import com.example.notesappmvvm.uı.adapter.NotesAdapter.*
import com.example.notesappmvvm.uı.fragment.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :RecyclerView.Adapter<notesViewHolder>() {
    class notesViewHolder(val binding: ItemNotesBinding):RecyclerView.ViewHolder(binding.root) {

    }
fun filtering(newFilterlist: ArrayList<Notes>){
    notesList=newFilterlist
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
return notesViewHolder(
    ItemNotesBinding.inflate(
        LayoutInflater.from(parent.context)
    ,parent,false
    )
)   }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data =notesList[position]
        holder.binding.notesTitle.text=data.title
        holder.binding.notesSubTitle.text=data.subTitle
        holder.binding.notesDate.text=data.data

        when(data.priority){
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.color1_dot)
            }

            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.color2_dot)

            }

            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.color3_dot)
            }
        }
        holder.binding.root.setOnClickListener {
            val action= HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }
  }

    override fun getItemCount()= notesList.size
}