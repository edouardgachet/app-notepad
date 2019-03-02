package training.Notepad

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_node_detail.*
import org.w3c.dom.Text

class NodeDetailActivity : AppCompatActivity() {

    companion object {
            val REQUEST_EDIT_NOTE = 1
            val EXTRA_NOTE = "note"
            val EXTRA_NOTE_INDEX = "noteIndex"
    }

    lateinit var note: Note
    var noteIndex: Int = -1

    lateinit var titleView: TextView
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_node_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)// permet d'afficher la flêche de retour pour la toolbar

        note = intent.getParcelableExtra<Note>(EXTRA_NOTE)
        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)

        titleView = findViewById(R.id.title) as TextView
        textView = findViewById(R.id.text) as TextView

        titleView.text = note.title
        textView.text = note.text
    }

                // création des fonctions pour appliquer notre boutton action_save sur notre tool bar

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_note_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_save -> {
                saveNote()
                return true
            }else -> return super.onOptionsItemSelected(item)
        }
    }

    fun saveNote(){
        note.title = titleView.text.toString() //on récupere les données mises à jours
        note.text = textView.text.toString()

        intent = Intent() // on crée un intent pour envoyer nos données à NoteListActivity
        intent.putExtra(EXTRA_NOTE, note)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        setResult(Activity.RESULT_OK, intent) // le setResult() permet de répondre à notre startActivityForResult() dans NoteListActivity
        finish() // on précise ici qu'on en a fini avec NoteListActivity
    }
}