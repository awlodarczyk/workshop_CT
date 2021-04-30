package io.hindbrain.myapplication.day2.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import io.hindbrain.myapplication.R
import java.text.SimpleDateFormat
import java.util.*

class FirstFragment: Fragment() {
    companion object{
        val RESULT_KEY = "RESULT_KEY"
    }
    private lateinit var edittext_name:EditText
    private lateinit var textview_date:TextView
    private lateinit var edittext_number:EditText
    private lateinit var button_date:Button

    var cal = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first,container,false)
    }

    override fun onResume() {
        super.onResume()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        edittext_name = view.findViewById<EditText>(R.id.name)
        textview_date = view.findViewById<TextView>(R.id.date)
        edittext_number = view.findViewById<EditText>(R.id.number)
        button_date = view.findViewById<Button>(R.id.set_date)
//        textview_date.text = "--/--/----"
        updateDateInView()
        setFragmentResultListener(RESULT_KEY){ requestKey, bundle ->
            bundle.get("_return_key_")?.let {
                if(it is String) {
                    view.findViewById<TextView>(R.id.text).text = it
                }
            }
        }
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        button_date.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))
                .show()
            }

        })

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val args = Bundle()
            args.putString("name",edittext_name.text.toString())
            args.putString("date",textview_date.text.toString())
            args.putString("number",edittext_number.text.toString())
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment,args)
        }
        view.findViewById<Button>(R.id.button_third).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_thirdFragment)
        }
    }
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        textview_date.text = sdf.format(cal.time)
    }
}