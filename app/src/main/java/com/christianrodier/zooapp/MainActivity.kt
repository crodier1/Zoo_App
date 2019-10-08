package com.christianrodier.zooapp

import android.content.Context
import android.content.Intent
import android.graphics.PostProcessor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_killer_ticket.view.*
import kotlinx.android.synthetic.main.animal_ticket.view.ivAnimalImage
import kotlinx.android.synthetic.main.animal_ticket.view.tvDescription
import kotlinx.android.synthetic.main.animal_ticket.view.tvName

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()

    var adapter: AnimalsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load animals
        listOfAnimals.add(Animal("Baboon", "Climbs up trees", R.drawable.baboon, true))
        listOfAnimals.add(Animal("Bull Dog", "Barks at mail man", R.drawable.bulldog, true))
        listOfAnimals.add(Animal("Panda", "Struggles with depression", R.drawable.panda, false))
        listOfAnimals.add(Animal("Swallow", "Eats worms", R.drawable.swallow_bird, false))
        listOfAnimals.add(Animal("White Tiger", "Performs at Vegas", R.drawable.white_tiger, true))
        listOfAnimals.add(Animal("Zebra", "Black on white?", R.drawable.zebra, false))


        adapter = AnimalsAdapter(this, listOfAnimals)

        lvAnimals.adapter = adapter

    }

    fun delete(index: Int){
        listOfAnimals.removeAt(index)

        adapter!!.notifyDataSetChanged()

    }

    inner class AnimalsAdapter: BaseAdapter {
        var listOfAnimals = ArrayList<Animal>()
        var context: Context? = null

        constructor(context: Context, listOfAnimals: ArrayList<Animal>): super(){
            this.context = context
            this.listOfAnimals = listOfAnimals
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


            val animal = listOfAnimals[position]


            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            var animalView: View = setAnimalView(animal.isKiller, inflater, animal)

            setAnimalPictureOnClickListener(animalView ,animal)

            setAnimalDeleteOnClickListener(animalView,position)


            return animalView

        }

        private fun setAnimalView(killer: Boolean?, inflater: LayoutInflater, animal: Animal): View {
                lateinit var myView: View

            if (killer!!){

                myView = inflater.inflate(R.layout.animal_killer_ticket, null)


            } else {
                myView = inflater.inflate(R.layout.animal_ticket, null)

            }

            myView.tvName.text = animal.name!!
            myView.tvDescription.text = animal.description!!
            myView.ivAnimalImage.setImageResource(animal.image!!)
            myView.tvDelete.text = "Delete"

            return myView

        }


        private fun setAnimalPictureOnClickListener(myView: View?, animal: Animal) {


            var myView = myView

            myView!!.ivAnimalImage.setOnClickListener{

                //

                val intent = Intent(context, AnimalDetails::class.java)

                intent.putExtra(AnimalIntentVars.name, animal.name!!)
                intent.putExtra(AnimalIntentVars.description, animal.description!!)
                intent.putExtra(AnimalIntentVars.image, animal.image!!)
                context!!.startActivity(intent)
            }

        }

        private fun setAnimalDeleteOnClickListener(animalView: View, index: Int){
            var deleteAnimalView = animalView

            deleteAnimalView.tvDelete.setOnClickListener{
                delete(index)
            }
        }


        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
