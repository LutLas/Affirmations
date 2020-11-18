package io.erehsawsaltul.affirmations.data

import android.content.ContentValues.TAG
import android.util.Log
import io.erehsawsaltul.affirmations.R
import io.erehsawsaltul.affirmations.model.Affirmation
import io.erehsawsaltul.affirmations.model.Hymns

class Datasource {
    fun loadAffirmations():List<Affirmation>{
        return listOf<Affirmation>(
                Affirmation(R.string.affirmation1, R.drawable.image1),
                Affirmation(R.string.affirmation2, R.drawable.image2),
                Affirmation(R.string.affirmation4, R.drawable.image4),
                Affirmation(R.string.affirmation5, R.drawable.image5),
                Affirmation(R.string.affirmation6, R.drawable.image6),
                Affirmation(R.string.affirmation7, R.drawable.image7),
                Affirmation(R.string.affirmation8, R.drawable.image8),
                Affirmation(R.string.affirmation9, R.drawable.image9),
                Affirmation(R.string.affirmation10, R.drawable.image10)
        )
    }
    //DataSource File loadHymns Function
    fun loadHymns(song: List<Hymns>):List<Hymns> {



        /*GlobalScope.launch(Dispatchers.Main) {

        }*/
        return song
    }
}