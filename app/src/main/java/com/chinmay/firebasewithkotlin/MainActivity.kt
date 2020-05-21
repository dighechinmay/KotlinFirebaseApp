package com.chinmay.firebasewithkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


var useremailsFromFB: ArrayList<String> = ArrayList<String>()
    var userImageFromFB: ArrayList<String> = ArrayList<String>()
    var userCommentFromFB: ArrayList<String> =ArrayList<String>()
    var firebaseDatabase: FirebaseDatabase? = null
    var myRef : DatabaseReference? = null
    var adapter: PostClass? = null


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.reference


        adapter = PostClass(useremailsFromFB, userImageFromFB, userCommentFromFB,this)

        listView.adapter = adapter

        getDataFromFireBase()
    }


    fun getDataFromFireBase(){

        val newReference = firebaseDatabase!!.getReference("Posts")

        newReference.addValueEventListener(object: ValueEventListener {


            override fun onDataChange(p0: DataSnapshot) {


                adapter!!.clear()
                useremailsFromFB.clear()
                userCommentFromFB.clear()
                userImageFromFB.clear()

                for(snapshot in p0.children){

                    val hashMap = snapshot.value as HashMap<String,String>

                    if(hashMap.size>0){

                        val email = hashMap["useremail"]
                        val comment = hashMap["comment"]
                        val image =hashMap["downloadURL"]

                        if(email != null){
                            useremailsFromFB.add(email)
                        }

                        if(comment != null){
                            userCommentFromFB.add(comment)
                        }

                        if(image != null){
                            userImageFromFB.add(image)
                        }

                        adapter!!.notifyDataSetChanged()
                    }

                }


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }



        })


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.upload_content_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.add_image){

            val intent = Intent(applicationContext,UploadActivity::class.java)
            startActivity(intent)
        }



        return super.onOptionsItemSelected(item)
    }
}
