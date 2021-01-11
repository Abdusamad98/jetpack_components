package com.example.jetpackcomponents.dialogs

import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jetpackcomponents.R

class AddContactDialog(context: Context) : BaseDialog(context, R.layout.dialog_item_add) {
    private var listener: ((String,String) -> Unit)? = null
    var btn_add: Button = view.findViewById(R.id.add_contact)
    var btn_cancel: Button = view.findViewById(R.id.cancel_adding)

    var input_name: EditText = view.findViewById(R.id.name)
    var input_number: EditText = view.findViewById(R.id.number)

    init {
        view.apply {
            btn_cancel.setOnClickListener {
                close()
            }

            btn_add.setOnClickListener{
                var name = input_name.text.toString()
                var number = input_number.text.toString()
                if(name.isEmpty()||number.isEmpty()){
                    Toast.makeText(context, "Ism yoki raqamni kirit!", Toast.LENGTH_SHORT).show()
                }
                else{
                    listener?.invoke(name,number)
                    close()
                }
            }
        }
    }
    fun setOnSubmitAdd(f: ((String,String) -> Unit)?) {
        listener = f
    }
}

