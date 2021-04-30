package io.hindbrain.myapplication.day3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day3.extensions.inflate

class SimpleAdapter(private val data:List<String>): RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = parent.inflate(R.layout.list_item)
        return SimpleViewHolder(view)

    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView:TextView

        init {
            textView = itemView.findViewById(R.id.text)
        }
        fun bind(item:String){
            textView.text = item
        }
    }
}