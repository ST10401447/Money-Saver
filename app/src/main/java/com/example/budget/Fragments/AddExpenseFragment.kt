package com.example.budget.Fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.example.budget.DataClasses.Expense
import com.example.budget.Databases.CategoryDatabase
import com.example.budget.Databases.ExpensesDatabaseHelper
import com.example.budget.R
import com.example.budget.databinding.FragmentAddExpenseBinding
import java.io.ByteArrayOutputStream
import java.util.Calendar


class AddExpenseFragment : Fragment() {

   private lateinit var viewBinding:FragmentAddExpenseBinding
   private lateinit var categoryDb:CategoryDatabase
   private lateinit var expenseDb :ExpensesDatabaseHelper
   private lateinit var categorySpinner: Spinner
   private val CAMERA_REQUEST = 100
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentAddExpenseBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categorySpinner = viewBinding.categoriesSpinner
        categoryDb = CategoryDatabase(viewBinding.root.context)
        expenseDb = ExpensesDatabaseHelper(viewBinding.root.context)

        // Spinner code
        val categoryItems = categoryDb.getCategories()
        val categoryTitles = mutableListOf<String>()
        var m = 0
        while (m < categoryItems.size) {
            categoryTitles.add(categoryItems[m].categoryTitle)
            m++
        }
        // Adapter
        val adapter = ArrayAdapter(
            viewBinding.root.context,
            android.R.layout.simple_spinner_item,
            categoryTitles
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        // Item selected listener
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                viewBinding.CategoryFinalTextView.text = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional
            }
        }

        // calendar btn

        viewBinding.DatefloatingActionButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Open date picker dialog
            val datePickerDialog = DatePickerDialog(viewBinding.root.context,
                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                    if(selectedMonth < 10 && selectedDay < 10){
                        val date = "$selectedYear-0${selectedMonth + 1}-0${selectedDay}"
                        viewBinding.DateEditText.text = "$date"
                    }
                    else{
                        val date = "$selectedYear-${selectedMonth + 1}-${selectedDay}"
                        viewBinding.DateEditText.text = "$date"
                    }

                }, year, month, day)

            datePickerDialog.show()
        }

        viewBinding.PictureFloatingActionButton2.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST)
        }

        viewBinding.SaveButton.setOnClickListener {
            val notes = viewBinding.NotesEditText.text.toString()
            val category = viewBinding.CategoryFinalTextView.text.toString()
            val date = viewBinding.DateEditText.text.toString()
            val amount = viewBinding.amountEditText.text.toString()
            val picture = viewBinding.ExpensePicture

            if (notes != "" && date !="" && amount != ""){
                val expense = Expense(0,notes,category,date,amount.toDouble(),imageViewToByteArray(picture))
                expenseDb.createExpense(expense)
                Toast.makeText(viewBinding.root.context,"Expense Added",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(viewBinding.root.context,"Error All Fields are REQUIRED",Toast.LENGTH_SHORT).show()
            }


        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val photo = data?.extras?.get("data") as? android.graphics.Bitmap
            viewBinding.ExpensePicture.setImageBitmap(photo)
        }
    }
    fun imageViewToByteArray(imageView: ImageView): ByteArray {
        val drawable = imageView.drawable as? BitmapDrawable
        val bitmap = drawable?.bitmap

        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }


}