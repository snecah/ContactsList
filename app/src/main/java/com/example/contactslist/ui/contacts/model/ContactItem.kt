package com.example.contactslist.ui.contacts.model

import com.example.contactslist.adapterDelegetion.DisplayableItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactItem(val contactId: Int, var name: String, var surname: String?, var phoneNumber: String):DisplayableItem {
    override val id: Int
        get() = this.hashCode()
}
