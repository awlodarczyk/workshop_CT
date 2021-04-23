package io.hindbrain.myapplication.day2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import io.hindbrain.myapplication.R
import java.lang.Exception

class SecondFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        arguments?.getString("key")?.let {
            view.findViewById<TextView>(R.id.text).text = it
        }
        arguments?.getString("name")?.let {
            view.findViewById<TextView>(R.id.name).text = it
        }
        arguments?.getString("date")?.let {
            view.findViewById<TextView>(R.id.date).text = it
        }
        arguments?.getString("number")?.let {
            view.findViewById<TextView>(R.id.number).text = it
        }

        view.findViewById<Button>(R.id.back).setOnClickListener {
            val textView = view.findViewById<TextView>(R.id.number)
            val txt = textView?.text.toString()
            var result = ""
            try{

                val intVal = txt.toInt()
                if(intVal % 3 == 0){
                    result = "Liczba $intVal jest podzielna przez 3"
                }else{
                    result = "Liczba $intVal NIE JEST podzielna przez 3"
                }
            }catch (e:Exception){
                result = "Nie udalo sie scastowac"
            }
            val bundle = Bundle()
            bundle.putString("_return_key_",result)
            setFragmentResult(FirstFragment.RESULT_KEY,bundle)
            findNavController().popBackStack()
//            requireActivity().onBackPressed()
        }
    }
}