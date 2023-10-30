package com.example.contactslist.ui.contacts.model

import com.example.contactslist.adapterDelegetion.DisplayableItem

data class ContactItem(val contactId: Int, val name: String, val surname: String?, val phoneNumber: String):DisplayableItem {
    override val id: Int
        get() = this.hashCode()
}
