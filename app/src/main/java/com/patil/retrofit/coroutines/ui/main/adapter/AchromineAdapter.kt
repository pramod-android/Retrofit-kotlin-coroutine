package com.patil.retrofit.coroutines.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patil.retrofit.coroutines.R
import com.patil.retrofit.coroutines.data.model.Acromine
import kotlinx.android.synthetic.main.item_layout.view.*

class AchromineAdapter(private val lfsList: ArrayList<Acromine.AcromineItem.Lf>) : RecyclerView.Adapter<AchromineAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Acromine.AcromineItem.Lf) {
            itemView.apply {
                textViewUserName.text = user.lf
                textViewUserEmail.text = user.freq.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = lfsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(lfsList[position])
    }

    fun addlfsList(lfsList: List<Acromine.AcromineItem.Lf>) {
        this.lfsList.apply {
            clear()
            addAll(lfsList)
        }

    }
}