package com.example.contactslist.ui.contacts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactslist.R
import com.example.contactslist.databinding.FragmentMyContactsBinding
import com.example.contactslist.ui.contacts.model.AddContactItem
import com.example.contactslist.ui.contacts.model.ContactItem

private const val PHONE_NUMBER = "phoneNumber"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_ID = "contactId"
private const val FRAGMENT_ID = "fragmentId"
private const val EDIT_CONTACT_FRAGMENT_ID = 1
private const val CREATE_CONTACT_FRAGMENT_ID = 0

class ContactsFragment : Fragment(R.layout.fragment_my_contacts) {

    private val viewModel by viewModels<ContactsViewModel>()
    private val binding by viewBinding(FragmentMyContactsBinding::bind)
    private val adapter by lazy {
        ContactsAdapter(
            onContactItemClicked(),
            onAddContactItemClicked(),
            onContactItemLongClick()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.setItems(viewModel.getContacts())


        binding.fab.setOnClickListener {
            viewModel.deleteContacts()
            adapter.setItems(viewModel.getContacts())
        }


        if (arguments?.isEmpty == false) {
            val arg = arguments
            val newContactName = arg?.getString(CONTACT_NAME)
            val newContactPhoneNumber = arg?.getString(PHONE_NUMBER)
            val editedContactId = arg?.getInt(CONTACT_ID)
            val fragmentId = arg?.getInt(FRAGMENT_ID)
            if (fragmentId == EDIT_CONTACT_FRAGMENT_ID)
                viewModel.editContact(editedContactId, newContactName, newContactPhoneNumber)
            if(fragmentId == CREATE_CONTACT_FRAGMENT_ID)
                viewModel.addContact(newContactName, newContactPhoneNumber)
            adapter.setItems(viewModel.getContacts())
        }
    }




    private fun onContactItemClicked(): (ContactItem) -> Unit = {
        val contactName = viewModel.constructName(it.name, it.surname)
        val action = ContactsFragmentDirections.actionMyContactsFragmentToEditContactFragment(
            it.phoneNumber,
            contactName,
            it.contactId
        )
        findNavController().navigate(action)
    }

    private fun onAddContactItemClicked(): (AddContactItem) -> Unit = {
        val action = ContactsFragmentDirections.actionMyContactsFragmentToCreateContactFragment()
        findNavController().navigate(action)
    }

    private fun onContactItemLongClick():(ContactItem, position: Int) -> Unit = { contact, position ->
        viewModel.markContact(contact)
        adapter.notifyItemChanged(position)
    }

}