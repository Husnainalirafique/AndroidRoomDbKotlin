package com.example.room.database
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.google.android.material.card.MaterialCardView
import java.util.Random

class NoteAdapter : ListAdapter<Note, NoteAdapter.ViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvRecyclerViewTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvRecyclerViewDescription)
        private val cardView : MaterialCardView = itemView.findViewById(R.id.bgCardView)

        fun bind(note: Note) {
            tvTitle.text = note.noteTitle
            tvDescription.text = note.noteDescription

            val randomColorResId = getRandomColor()
            val randomColor = itemView.context.getColor(randomColorResId)
            cardView.setCardBackgroundColor(randomColor)
        }
        private fun getRandomColor(): Int {
            val colorList = listOf(
                R.color.notec1,
                R.color.notec2,
                R.color.notec3,
                R.color.notec4,
                R.color.notec5,
            )

            val randomIndex = Random().nextInt(colorList.size)
            return colorList[randomIndex]
        }

    }

}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}
