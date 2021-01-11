package com.example.jetpackcomponents.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackcomponents.R
import com.example.jetpackcomponents.database.Contact
import com.example.jetpackcomponents.models.MyContact

internal class ContactsAdapter(private var moviesList: List<Contact>) :
    RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    private var listener_update: ((Int) -> Unit)? = null
    private var listener_delete: ((Int) -> Unit)? = null


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var number: TextView = view.findViewById(R.id.number)
        var image: ImageView = view.findViewById(R.id.image)
        var delete: TextView = view.findViewById(R.id.deleting)
        var updating: TextView = view.findViewById(R.id.updating)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = moviesList[position]
        holder.name.text = contact.name
        holder.number.text = contact.number
        holder.image.setBackgroundResource(contact.imgUrl)

        holder.updating.setOnClickListener {
            listener_update?.invoke(position)
        }

        holder.delete.setOnClickListener {
            listener_delete?.invoke(position)
        }


    }
    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setOnUpdateListener(f: ((Int) -> Unit)?) {
        listener_update = f
    }


    fun setOnDeleteListener(f: ((Int) -> Unit)?) {
        listener_delete = f
    }
}