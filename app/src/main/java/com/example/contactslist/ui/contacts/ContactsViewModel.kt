package com.example.contactslist.ui.contacts

import androidx.lifecycle.ViewModel
import com.example.contactslist.Utils.contacts
import com.example.contactslist.Utils.generateContacts
import com.example.contactslist.adapterDelegetion.DisplayableItem
import com.example.contactslist.ui.contacts.model.AddContactItem
import com.example.contactslist.ui.contacts.model.ContactItem
import kotlin.random.Random

class ContactsViewModel : ViewModel() {
    private var hardcodedContacts = contacts

    fun constructName(name: String, surname: String?): String {
        return if (surname.isNullOrEmpty())
            name
        else
            "$name $surname"
    }

    fun setContacts(contacts: MutableList<DisplayableItem>) {
        hardcodedContacts = contacts
    }

    fun getContacts(): List<DisplayableItem> = hardcodedContacts
    fun editContact(
        editedContactId: Int?,
        editedContactName: String?,
        editedContactPhoneNumber: String?
    ) {
        for (i in 1 until hardcodedContacts.size) {
            val contact = hardcodedContacts[i] as ContactItem
            if (contact.contactId == editedContactId) {
                contact.name = editedContactName ?: contact.name
                contact.surname = null
                contact.phoneNumber = editedContactPhoneNumber ?: contact.phoneNumber
            }
        }
    }
}
