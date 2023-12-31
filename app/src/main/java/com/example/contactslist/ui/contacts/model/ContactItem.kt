package com.example.contactslist.ui.contacts.model

import com.example.contactslist.adapterDelegetion.DisplayableItem

data class ContactItem(
    val contactId: Int,
    var name: String,
    var surname: String?,
    var phoneNumber: String,
    var isSelected:Boolean = false
) : DisplayableItem {
    override val id: Int
        get() = this.hashCode()
}
