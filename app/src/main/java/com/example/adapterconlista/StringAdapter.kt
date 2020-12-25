package com.example.adapterconlista

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import kotlin.random.nextInt

class StringAdapter(var listaString : MutableList<String>) : RecyclerView.Adapter<StringAdapter.StringViewHolder>() {

    class StringViewHolder(var root: View, var textView: TextView, var checkbox: CheckBox) : RecyclerView.ViewHolder(root)
    var contador = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val twTextView = view.findViewById<TextView>(R.id.textView)
        val checkbox = view.findViewById<CheckBox>(R.id.checkbox)
        return StringViewHolder(view, twTextView,checkbox)
    }

    override fun getItemCount(): Int {
        return listaString.size + 3
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        if (position == 0){
            holder.textView.text = "Borrar"
            if (listaString.size > 0) {
                holder.root.setOnClickListener {
                    listaString.removeAt(Random.nextInt(listaString.size))
                    notifyDataSetChanged()
                }
            }
        }else{
            if (position == listaString.size + 1){
                holder.textView.text = "Insertar"
                holder.root.setOnClickListener{
                    listaString.add(listaString.last()+"PC-${position}")
                    notifyDataSetChanged()
                }
            }else{
                if (position == listaString.size + 2){
                    holder.textView.text = "Contar encencidos"
                    holder.textView.setOnClickListener{
                        val toast = Toast.makeText(it.context, "Encendidos: ${contador}",Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()
                    }
                }else{
                    holder.textView.text = "${listaString[position]}"
                }
            }
        }
        for (i in 0..5){
            val checkAleatorio = Random.nextInt(0..listaString.size)
            if (checkAleatorio == position){ holder.checkbox.isChecked = true}
        }

        if(holder.checkbox.isChecked){
            holder.textView.setBackgroundColor(Color.parseColor("#43E418"))
            contador++
        }else{
            holder.textView.setBackgroundColor(Color.parseColor("#EC2B11"))
            contador--
        }

    }
}