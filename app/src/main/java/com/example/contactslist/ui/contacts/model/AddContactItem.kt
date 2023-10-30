package com.example.contactslist.ui.contacts.model

import com.example.contactslist.adapterDelegetion.DisplayableItem
import kotlinx.parcelize.Parcelize

@Parcelize
class AddContactItem(val s:String = "Add Contact"): DisplayableItem {
    override val id: Int
        get() = this.hashCode()
}