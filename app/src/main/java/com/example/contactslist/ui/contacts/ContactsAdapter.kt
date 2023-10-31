package com.example.contactslist.ui.contacts

import com.example.contactslist.adapterDelegetion.DelegationAdapter
import com.example.contactslist.adapterDelegetion.DisplayableItem
import com.example.contactslist.ui.contacts.model.AddContactItem
import com.example.contactslist.ui.contacts.model.ContactItem
import com.example.contactslist.ui.diffUtil.BaseDiffUtilCallback

class ContactsAdapter(
    private val onContactItemClicked: (ContactItem) -> Unit,
    private val onAddContactItemClicked: (AddContactItem) -> Unit,
    private val  onContactItemLongClick:(ContactItem, position: Int) -> Unit) :
    DelegationAdapter<DisplayableItem>(BaseDiffUtilCallback()) {
    init {
        with(delegatesManager) {
            addDelegate(ContactsScreenDelegates.AddContactListItemDelegate(onAddContactItemClicked))
            addDelegate(ContactsScreenDelegates.ContactListItemDelegate(onContactItemClicked, onContactItemLongClick))
        }
    }
}