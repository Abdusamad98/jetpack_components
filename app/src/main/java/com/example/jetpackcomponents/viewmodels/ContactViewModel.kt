package com.example.jetpackcomponents.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jetpackcomponents.database.Contact
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomponents.database.ContactDao
import kotlinx.coroutines.launch

class ContactViewModel(
    val database: ContactDao,
    application: Application
) : AndroidViewModel(application) {

  val contacts = database.getContacts()

    private suspend fun insert(contact: Contact) {
        database.insertContact(contact)
    }

    fun addContact(contact: Contact){
      viewModelScope.launch {
            insert(contact)
      }
    }






}
