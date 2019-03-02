package training.Notepad

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import android.widget.Toolbar
import org.w3c.dom.Node
import kotlin.math.log

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes: MutableList<Note> // declaration de la var notes de type liste mutable (car le contenu peut changer)
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val toolbar = findViewById(R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // il y'a dans la fonction onActivityResult() 3 paramètres, le requestCode correspond à notre REQUEST_EDIT_NOTE
        // le resultCode correspond à notre RESULT_OK si on passe bien dans le save_note
        // data correspond à nos données renvoyé pas notre intent dans NoteDetailActivity
        if(resultCode != Activity.RESULT_OK || data == null){
                return
        }

        when(requestCode){
            NodeDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }
    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NodeDetailActivity.EXTRA_NOTE_INDEX, -1)
        val note = data.getParcelableExtra<Note>(NodeDetailActivity.EXTRA_NOTE)
        saveNote(note, noteIndex)
    }

    fun saveNote(note: Note, noteIndex: Int){
        notes[noteIndex] = note
        adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        if(v.tag != null){
            showNoteDetail((v.tag as Int))
            val position = v.tag as Int
            val note = notes[position]
            Toast.makeText(this, "pays sélectionné : ${note.title}", Toast.LENGTH_SHORT).show()
        }
    }

    fun showNoteDetail(noteIndex: Int){
        val note = notes[noteIndex]

        val intent = Intent(this, NodeDetailActivity::class.java)
        intent.putExtra(NodeDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NodeDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        startActivityForResult(intent, NodeDetailActivity.REQUEST_EDIT_NOTE)
    }
}
