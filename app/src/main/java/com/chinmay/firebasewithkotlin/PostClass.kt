package com.chinmay.firebasewithkotlin

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_layout.view.*

class PostClass(private val useremail:ArrayList<String>,private val userImage: ArrayList<String>,private val userComment: ArrayList<String>,private val context: Activity) : ArrayAdapter<String>(context,R.layout.custom_layout,useremail){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val layoutInflater = context.layoutInflater

        val customView = layoutInflater.inflate(R.layout.custom_layout,null,true)

        customView.custom_emailText.text = useremail[position]
        customView.custom_commentText.text = userComment[position]
        //customView.custom_imageView.setImageBitmap() = userImage[position]
        Picasso.get().load(userImage[position]).into(customView.custom_imageView);
        return customView
    }
}