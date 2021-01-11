package com.example.jetpackcomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackcomponents.adapters.ContactsAdapter
import com.example.jetpackcomponents.database.Contact
import com.example.jetpackcomponents.database.ContactDao
import com.example.jetpackcomponents.database.ContactDatabase
import com.example.jetpackcomponents.dialogs.AddContactDialog
import com.example.jetpackcomponents.dialogs.UpdateContactDialog
import com.example.jetpackcomponents.models.MyContact
import com.example.jetpackcomponents.viewmodels.ContactViewModel
import com.example.jetpackcomponents.viewmodels.ContactViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var dataSource:ContactDao
    lateinit var viewModel: ContactViewModel
    lateinit var data: ArrayList<Contact>
    private lateinit var contactsAdapter: ContactsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "My Contacts"

        val application = requireNotNull(this).application
        dataSource = ContactDatabase.getInstance(application).contactDao

        data = ArrayList<Contact>()
        data.addAll(dataSource.getContacts())
        //dataSource.insertContact(Contact(0,name,number,R.drawable.person))
        //dataSource.deleteContact(data[it])
        //data =dataSource.getContacts()



     //   val viewModelFactory = ContactViewModelFactory(dataSource, application)

// Get a reference to the ViewModel associated with this fragment.
     //   viewModel = ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)


        // data = prepareContactData()

//        viewModel.contacts.observe(this, Observer { it ->
//            data=it as ArrayList<Contact>
//            for (x in 0..it.size - 1) {
//                data.add(MyContact(it[x].name, it[x].number, it[x].imgUrl))
//            }
//            Toast.makeText(this, "" + it.size, Toast.LENGTH_SHORT).show()
//        })



        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        contactsAdapter = ContactsAdapter(data)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = contactsAdapter


        contactsAdapter.setOnDeleteListener {
            dataSource.deleteContact(data[it])
            data.removeAt(it)
            contactsAdapter.notifyDataSetChanged()
        }


        contactsAdapter.setOnUpdateListener {
            val dialog = UpdateContactDialog(this, data[it])
            dialog.show()
            dialog.setOnSubmitAdd { name, number ->
                var contact = Contact(data[it].contactId, name, number, R.drawable.person)
                dataSource.updateContact(contact)
                data[it] =contact
            contactsAdapter.notifyDataSetChanged()
            }
        }
   }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                data.clear()
                dataSource.clear()
                contactsAdapter.notifyDataSetChanged()
                return true
            }

            R.id.add -> {
                val dialog = AddContactDialog(this)
                dialog.show()
                dialog.setOnSubmitAdd { name, number ->
                    var contact = Contact(0L, name, number, R.drawable.person)
                     data.add(contact)
                  //  viewModel.addContact(d)
                    dataSource.insertContact(contact)//Databasega contakt qo'shish
                    contactsAdapter.notifyDataSetChanged()
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }


    }
}