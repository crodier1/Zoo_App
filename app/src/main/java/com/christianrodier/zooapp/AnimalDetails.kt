package com.christianrodier.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.animal_ticket.*

class AnimalDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)

        val bundle: Bundle? = intent.extras

        val name = bundle!!.getString(AnimalIntentVars.name)
        val description = bundle!!.getString(AnimalIntentVars.description)
        val image = bundle!!.getInt(AnimalIntentVars.image)

        ivAnimalImage.setImageResource(image)

        tvName.text = name

        tvDescription.text = description

    }

    fun btnBackClick(view: View) {

        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)

    }
}
