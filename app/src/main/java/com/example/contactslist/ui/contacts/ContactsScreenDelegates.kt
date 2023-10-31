package com.example.contactslist.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactslist.R
import com.example.contactslist.adapterDelegetion.DisplayableItem
import com.example.contactslist.adapterDelegetion.ListItemAdapterDelegate
import com.example.contactslist.databinding.AddContactItemBinding
import com.example.contactslist.databinding.ContactItemBinding
import com.example.contactslist.ui.contacts.model.AddContactItem
import com.example.contactslist.ui.contacts.model.ContactItem

object ContactsScreenDelegates {
    class ContactListItemDelegate(
        private val onContactItemClicked: (ContactItem) -> Unit,
        private val onContactItemLongClick: (ContactItem, position: Int) -> Unit
    ) :
        ListItemAdapterDelegate<ContactItem, DisplayableItem, ContactListItemDelegate.ContactViewHolder>() {
        inner class ContactViewHolder(private val biding: ContactItemBinding) :
            RecyclerView.ViewHolder(biding.root) {

            fun bind(contact: ContactItem) {
                with(biding) {
                    if (contact.surname.isNullOrEmpty())
                        contactName.text = contact.name
                    else
                        contactName.text = root.context.getString(
                            R.string.contact_name,
                            contact.name,
                            contact.surname
                        )

                    phoneNumber.text = contact.phoneNumber

                    if (contact.isSelected)
                        checkbox.visibility = View.VISIBLE
                    else
                        checkbox.visibility = View.INVISIBLE
                }
            }

            fun bindSelectd(item: ContactItem) {
                if (item.isSelected)
                    biding.checkbox.visibility = View.VISIBLE
                else
                    biding.checkbox.visibility = View.VISIBLE
            }
        }

        override fun isForViewType(item: DisplayableItem): Boolean {
            return item is ContactItem
        }

        override fun onCreateViewHolder(parent: ViewGroup): ContactViewHolder {
            val biding =
                ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ContactViewHolder(biding)
        }

        override fun onBindViewHolder(
            item: ContactItem,
            holder: ContactViewHolder
        ) {
            holder.bind(item)
            holder.itemView.setOnClickListener { onContactItemClicked(item) }
            holder.itemView.setOnLongClickListener {
                onContactItemLongClick(item, holder.adapterPosition)
                true
            }
        }
    }

    class AddContactListItemDelegate(private val onAddContactItemClicked: (AddContactItem) -> Unit) :
        ListItemAdapterDelegate<AddContactItem, DisplayableItem, AddContactListItemDelegate.AddContactViewHolder>() {
        inner class AddContactViewHolder(private val biding: AddContactItemBinding) :
            RecyclerView.ViewHolder(biding.root) {
            fun bind(contact: AddContactItem) {}
        }

        override fun isForViewType(item: DisplayableItem): Boolean {
            return item is AddContactItem
        }

        override fun onCreateViewHolder(parent: ViewGroup): AddContactViewHolder {
            val biding =
                AddContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AddContactViewHolder(biding)
        }

        override fun onBindViewHolder(
            item: AddContactItem,
            holder: AddContactViewHolder
        ) {
            holder.bind(item)
            holder.itemView.setOnClickListener { onAddContactItemClicked(item) }
        }
    }

}