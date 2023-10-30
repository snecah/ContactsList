package com.example.contactslist.ui.contacts

import com.example.contactslist.adapterDelegetion.DelegationAdapter
import com.example.contactslist.adapterDelegetion.DisplayableItem
import com.example.contactslist.ui.contacts.model.ContactItem
import com.example.contactslist.ui.diffUtil.BaseDiffUtilCallback

class ContactsAdapter(private val onContactItemClicked: (ContactItem) -> Unit):DelegationAdapter<DisplayableItem>(BaseDiffUtilCallback()) {
    init {
        with(delegatesManager) {
            addDelegate(ContactsScreenDelegates.AddContactListItemDelegate())
            addDelegate(ContactsScreenDelegates.ContactListItemDelegate(onContactItemClicked))
        }
    }
}