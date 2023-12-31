package com.example.contactslist.ui.contacts

import androidx.lifecycle.ViewModel
import com.example.contactslist.Utils.ContactsGenerator
import com.example.contactslist.adapterDelegetion.DisplayableItem
import com.example.contactslist.ui.contacts.model.AddContactItem
import com.example.contactslist.ui.contacts.model.ContactItem

class ContactsViewModel : ViewModel() {

    private var hardcodedContacts = ContactsGenerator.contacts
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

    fun addContact(newContactName: String?, newContactPhoneNumber: String?) {
        val nextId = (hardcodedContacts.last() as ContactItem).contactId + 1
        hardcodedContacts.add(
            ContactItem(
                nextId,
                newContactName ?: "",
                null,
                newContactPhoneNumber ?: "88005553535"
            )
        )
    }

    fun markContact(contact: ContactItem) {
        contact.isSelected = !contact.isSelected
    }

    fun deleteContacts() {
        val toSave = mutableListOf<DisplayableItem>(AddContactItem())
        for(i in 1 until hardcodedContacts.size) {
            if (!(hardcodedContacts[i] as ContactItem).isSelected)
                toSave.add(hardcodedContacts[i])
        }
        ContactsGenerator.saveContacts(toSave)
        hardcodedContacts = toSave
    }

}
