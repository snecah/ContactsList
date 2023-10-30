package com.example.contactslist.ui.contacts.model

import com.example.contactslist.adapterDelegetion.DisplayableItem

class AddContactItem(val s:String = "Add Contact"): DisplayableItem {
    override val id: Int
        get() = this.hashCode()
}