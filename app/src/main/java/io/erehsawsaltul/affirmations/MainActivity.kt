package io.erehsawsaltul.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import io.erehsawsaltul.affirmations.adapter.ItemAdapter
import io.erehsawsaltul.affirmations.data.Datasource
import io.erehsawsaltul.affirmations.data.MyAPI
import io.erehsawsaltul.affirmations.databinding.ActivityMainBinding
//import io.erehsawsaltul.affirmations.model.Hymns
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException

//import io.erehsawsaltul.affirmations.data.Datasource

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
        val myDataSet = Datasource()
        val recyclerView = binding.dummyRecyclerView
        myStorageRef = storeRef()
        val bucketURL = myStorageRef.reference.bucket
        //signInAnonymously()
        val retroAPI = retro("$bucketURL/o/")
        //Log.d("myStorageRefOnline", "Value is: $bucketURL")
        coRoutine(retroAPI,myDataSet,recyclerView)
    }

    //Firebase Reference
    private fun storeRef(): FirebaseStorage {
        return Firebase.storage
    }

    //Retrofit Initilization
    private fun retro(myRef: String): MyAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL + myRef)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)
    }

    //Retrofit with Coroutine
    private fun coRoutine(api: MyAPI,dataSource: Datasource, recyclerView: RecyclerView) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getHymns().execute()
                if (response.isSuccessful) {
                    //Log.d("Hymning", "${response.body()}")


                    /*for (hymn in response.body()!!) {

                        loadHymns(hymn)
                        *//*Log.d("newHymnList", hymn.no)
                        binding.dummyTv.text = hymn.title.size.toString()*//*

                    }*/
                    GlobalScope.launch (Dispatchers.Main){
                        val finalDataSet = dataSource.loadHymns(response.body()!!)
                        recyclerView.adapter = ItemAdapter(this@MainActivity, finalDataSet)
                        recyclerView.setHasFixedSize(true)
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
                        Toast.makeText(
                            this@MainActivity,
                            "INTERNET IS REQUIRED, TIME OUT!",
                            LENGTH_LONG
                        )
                            .show()
                    } else {
                        Log.d("OtherHymningError", "$e")
                        Toast.makeText(
                            this@MainActivity,
                            "CONNECTION TO THE NETWORK FAILED",
                            LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


    }

    //DataSource File loadHymns Function
    /*private fun loadHymns(song: List<Hymns>) {



        *//*GlobalScope.launch(Dispatchers.Main) {

        }*//*
    }*/
}