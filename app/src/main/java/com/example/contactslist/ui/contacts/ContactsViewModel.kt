package com.example.contactslist.ui.contacts

import androidx.lifecycle.ViewModel
import com.example.contactslist.adapterDelegetion.DisplayableItem
import com.example.contactslist.ui.contacts.model.AddContactItem
import com.example.contactslist.ui.contacts.model.ContactItem
import kotlin.random.Random

class ContactsViewModel : ViewModel() {
    private val hardcodedContacts = mutableListOf<DisplayableItem>(
        AddContactItem()
    )
    
    fun generateContacts() {
        for (i in 0..100) {
            val name = generateName()
            val surname = generateSurname()
            val phoneNumber = generatePhoneNumber()
            val contact = ContactItem(i, name, surname, phoneNumber)
            hardcodedContacts.add(contact)
        }
    }

    fun getContacts() = hardcodedContacts

    private fun generatePhoneNumber(): String {
        var phoneNumber = "+7"
        for (j in 0..8) {
            val digit = Random.nextInt(10)
            phoneNumber += digit.toString()
        }
        return phoneNumber
    }

    private fun generateSurname(): String? {
        val surnames = listOf("Smith", "James", "Cooper", null, "Allen", "Harris", "Bell", "Johnson", "Roberts", "Edwards")
        return surnames.random()
    }

    private fun generateName(): String {
        val names = listOf("Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Martin", "David", "Carl", "Masha", "Victor")
        return names.random()
    }
}