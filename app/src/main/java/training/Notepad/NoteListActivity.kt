package training.Notepad

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Adapter
import kotlin.math.log

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes: MutableList<Note> // declaration de la var notes de type liste mutable (car le contenu peut changer)
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        notes = mutableListOf<Note>() // initialisation de la var notes en un tableau mutable de type Note
        notes.add(Note("Note 1", "wsh les fdp"))
        notes.add(Note("Note 2", "wsh les salauds"))
        notes.add(Note("Note 3", "wsh les zoulou"))
        notes.add(Note("Note 4", "wsh les bg"))

        adapter = NoteAdapter(notes, this)
        var recyclerView = findViewById(R.id.notes_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onClick(v: View) {
        if(v.tag != null){
            Log.i("NoteListActivity", "click sur une note de la liste")
        }
    }
}
