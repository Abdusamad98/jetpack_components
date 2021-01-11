package com.example.jetpackcomponents.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY name")
    fun getContacts(): List<Contact>//>LiveData<

    @Query("SELECT * FROM contacts WHERE id = :contactId")
    fun getContact(contactId: String): Flow<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(contacts: List<Contact>)//suspend

    @Delete
     fun deleteContact(contact: Contact)//suspend

    @Query("DELETE FROM contacts ")
    fun clear()

    @Insert
     fun insertContact(contact: Contact)//suspend

    @Update
     fun updateContact(contact: Contact)//suspend
}