package training.Notepad

import android.support.v7.view.menu.MenuView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NoteAdapter(val notes: List<Note>, val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){ // permet de créer le ViewHolder qui sera recyclé pour afficher un nouvel élément
        val cardView = itemView.findViewById(R.id.card_view) as CardView
        val titleView = cardView.findViewById(R.id.title) as TextView
        val excerptView = cardView.findViewById(R.id.excerpt) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder { // Ici nous définissons le layout qui définira le ViewHolder
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(viewItem) // afin de lier notre layout au viewHolder, on renvoie un ViewHolder avec l'instance qui sera crée du viewItem
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) { // dans cette fonction, on définit les données qui vont alimenter notre ViewHolder
        val note = notes[p1]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = p1
        holder.titleView.text = note.title
        holder.excerptView.text = note.text
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}
