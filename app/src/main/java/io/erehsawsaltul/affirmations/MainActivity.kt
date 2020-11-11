package io.erehsawsaltul.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import io.erehsawsaltul.affirmations.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/"

class MainActivity : AppCompatActivity() {

    private lateinit var myStorageRef: FirebaseStorage
    private lateinit var binding: ActivityMainBinding
    //private lateinit var authFire: FirebaseAuth

    //MainActivity onCreate LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //authFire = Firebase.auth
        myStorageRef = storeRef()
        val bucketURL = myStorageRef.reference.bucket
        //signInAnonymously()
        val retroAPI = retro("$bucketURL/o/")
        //Log.d("myStorageRefOnline", "Value is: $bucketURL")
        coRoutine(retroAPI)
    }

    //Firebase Reference
    private fun storeRef(): FirebaseStorage {
        return Firebase.storage
    }

    //Retrofit Initilization
    private fun retro(myRef: String): MyAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL + myRef)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)
    }

    //Retrofit with Coroutine
    private fun coRoutine(api: MyAPI) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getHymns().execute()
                if (response.isSuccessful) {
                    //Log.d("Hymning", "${response.body()}")
                    for (hymn in response.body()!!) {
                        //Log.d("Hymning", "$hymn")
                        @Suppress("UNCHECKED_CAST") val hymnPair =
                            hymn.toPair() as Pair<String, List<Any>>
                        val newHymnList = hymnPair.second
                        for (song in newHymnList) Log.d("newHymnList", "$song")

                    }

                } else {
                    GlobalScope.launch(Dispatchers.Main) {
                        Log.d("HymningError", "$response")
                        Toast.makeText(this@MainActivity, "Failed To Download Hymns", LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: IOException) {
                GlobalScope.launch(Dispatchers.Main) {
                    if (e.message == "connect timed out") {
                        Toast.makeText(this@MainActivity, "INTERNET IS REQUIRED", LENGTH_LONG)
                            .show()
                    } else {
                        Log.d("OtherHymningError", "$e")
                        Toast.makeText(this@MainActivity, "${e.message}", LENGTH_SHORT).show()
                    }
                }
            }
        }


    }
}