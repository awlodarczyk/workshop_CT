package io.hindbrain.myapplication.day4.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day3.extensions.inflate
import io.hindbrain.myapplication.day4.model.Priority
import io.hindbrain.myapplication.day4.model.ShoppingItem

class ShoppingAdapter(private var data:List<ShoppingItem>):
    RecyclerView.Adapter<ShoppingAdapter.ShoppingHolder>(), Filterable {

    private var filteredData: List<ShoppingItem> = emptyList()
    init {
        this.filteredData = data
    }

    fun updateAdapter(list: List<ShoppingItem>){
        this.data = list
        this.filteredData = this.data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingHolder {
        return ShoppingHolder(parent.inflate(R.layout.shopping_item))
    }

    override fun onBindViewHolder(holder: ShoppingHolder, position: Int) {
        holder.bind(filteredData[position])
    }

    override fun getItemCount(): Int = filteredData.size

    class ShoppingHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val name:TextView
        private val description:TextView
        private val qty:TextView
        private val priority: ImageView
        init {
            name = itemView.findViewById(R.id.name)
            description = itemView.findViewById(R.id.description)
            qty = itemView.findViewById(R.id.qty)
            priority = itemView.findViewById(R.id.priority)
        }
        fun bind(item:ShoppingItem){

            name.text = item.name
            description.text = item.comment
            qty.text = item.qty.toString()
            val drawable = when(item.priority){
                 Priority.HIGH -> ContextCompat.getDrawable(itemView.context,R.drawable.ic_high)
                 Priority.MEDIUM -> ContextCompat.getDrawable(itemView.context,R.drawable.ic_medium)
                 else -> null
            }
            if(drawable!=null) {
                priority.visibility = View.VISIBLE
                priority.setImageDrawable(drawable)
            }else{
                priority.visibility = View.INVISIBLE
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val queryString = p0.toString().toLowerCase()
                val filterResult = Filter.FilterResults()

                filterResult.values = if(queryString==null || queryString.isBlank())
                    data
                else
                    data.filter { itm -> itm.name.toLowerCase().contains(queryString) || itm.comment.toLowerCase().contains(queryString) }

                return filterResult
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                filteredData = filterResults?.values as List<ShoppingItem>
                notifyDataSetChanged()
            }

        }
    }
}