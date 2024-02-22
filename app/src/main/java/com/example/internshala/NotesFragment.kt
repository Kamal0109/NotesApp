package com.example.internshala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotesFragment : Fragment() {

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        // Placeholder list of notes
        val notesList = listOf(
            Note("Note 1", "Content 1"),
            Note("Note 2", "Content 2"),
            // Add more notes as needed
        )

        // Initialize RecyclerView and adapter with the notes list
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        notesAdapter = NotesAdapter(notesList)
        recyclerView.adapter = notesAdapter

        // Handle add new note button click
        view.findViewById<Button>(R.id.btnAddNote).setOnClickListener {
            // Code to add a new note
        }

        return view
    }
}