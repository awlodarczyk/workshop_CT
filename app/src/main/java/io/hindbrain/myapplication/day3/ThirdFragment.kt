package io.hindbrain.myapplication.day3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.hindbrain.myapplication.R
import io.hindbrain.myapplication.day3.adapter.ShoppingAdapter
import io.hindbrain.myapplication.day3.model.Priority
import io.hindbrain.myapplication.day3.model.ShoppingItem

class ThirdFragment: Fragment() {

    private val list:List<String> = generateList()
    private val shoppingList:List<ShoppingItem> = generateShopingList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var search: SearchView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = ShoppingAdapter(shoppingList)

        search = view.findViewById(R.id.search)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                (recyclerView.adapter as ShoppingAdapter).filter.filter(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                (recyclerView.adapter as ShoppingAdapter).filter.filter(p0)
                return true
            }

        })
    }

    private fun generateList():List<String>{
        val items = emptyList<String>().toMutableList()
        for (i in 0..10){
            items.add(i,"To jest komorka nr: $i")
        }
        return items
    }
    private fun generateShopingList():List<ShoppingItem>{
        val items = emptyList<ShoppingItem>().toMutableList()
        for (i in 0..10){
            val priority = when (i%2){
                0 -> Priority.MEDIUM
                else -> Priority.HIGH
            }
            val item = ShoppingItem("to jest zakup $i",i,priority,"To jest komentarz nr $i")
            items.add(i,item)
        }
        items.add(ShoppingItem("dodatkowy el", 4,Priority.LOW,"Komentarz"))
        items.add(ShoppingItem("dodatkowy el", 2,Priority.LOW,"Komentarz"))
        return items
    }
}