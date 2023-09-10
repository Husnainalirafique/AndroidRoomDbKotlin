package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.room.database.Note
import com.example.room.database.NoteAdapter
import com.example.room.database.NoteDao
import com.example.room.database.NoteDb
import com.example.room.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteDao: NoteDao
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        noteDao = NoteDb.getDatabase(this).noteDao()
        noteAdapter = NoteAdapter()

        //Functions
        settingUpRecyclerView()
        addingNotes()
        fetchingNotesInitially()
        deletingNotes()

    }

    private fun settingUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = noteAdapter
        }
    }

    private fun addingNotes() {
        binding.button.setOnClickListener {
            val title = binding.editText.text.toString()
            var description = binding.editText2.text.toString()

            if (title.isNotEmpty()) {
                if (description.isEmpty()) {
                    description = "No text"
                }
                lifecycleScope.launch(Dispatchers.IO) {
                    noteDao.addNote(Note(0, title, description))
                    // Fetch the updated notes immediately
                    val notes = noteDao.getNotes()
                    updateRecyclerView(notes)
                }
            } else {
                binding.editText.error = "Please Fill!"
//                binding.editText2.error = "Please Fill!"
            }

            // Clear the input fields
            binding.editText.text.clear()
            binding.editText2.text.clear()
        }
    }

    private fun fetchingNotesInitially() {
        // Fetch notes initially and update the RecyclerView
        lifecycleScope.launch(Dispatchers.IO) {
            val initialNotes = noteDao.getNotes()
            updateRecyclerView(initialNotes)
        }
    }

    private fun deletingNotes() {
        binding.buttonDelete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                noteDao.deleteAllUsers()
                val afterDel = noteDao.getNotes()
                updateRecyclerView(afterDel)
            }
        }
    }

    private fun updateRecyclerView(notes: List<Note>) {
        noteAdapter.submitList(notes)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}

