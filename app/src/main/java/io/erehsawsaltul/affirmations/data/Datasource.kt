package io.erehsawsaltul.affirmations.data

import android.content.ContentValues.TAG
import android.util.Log
import io.erehsawsaltul.affirmations.R
import io.erehsawsaltul.affirmations.model.Affirmation
import io.erehsawsaltul.affirmations.model.Hymns

class Datasource {
    fun loadAffirmations():List<Affirmation>{
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1),
            Affirmation(R.string.affirmation2),
            Affirmation(R.string.affirmation3),
            Affirmation(R.string.affirmation4),
            Affirmation(R.string.affirmation5),
            Affirmation(R.string.affirmation6),
            Affirmation(R.string.affirmation7),
            Affirmation(R.string.affirmation8),
            Affirmation(R.string.affirmation9),
            Affirmation(R.string.affirmation10)
        )
    }
    //DataSource File loadHymns Function
    fun loadHymns(song: List<Hymns>):List<Hymns> {



        /*GlobalScope.launch(Dispatchers.Main) {

        }*/
        return song
    }
}