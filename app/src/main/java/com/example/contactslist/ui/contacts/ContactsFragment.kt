package com.example.contactslist.ui.contacts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactslist.R
import com.example.contactslist.databinding.FragmentMyContactsBinding
import com.example.contactslist.ui.contacts.model.ContactItem
private const val PHONE_NUMBER = "phoneNumber"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_ID = "contactId"

class ContactsFragment : Fragment(R.layout.fragment_my_contacts) {

    private val viewModel by viewModels<ContactsViewModel>()
    private val binding by viewBinding(FragmentMyContactsBinding::bind)
    private val adapter by lazy { ContactsAdapter(onContactItemClicked()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.setItems(viewModel.getContacts())

        if(arguments?.isEmpty == false) {
            val arg = arguments
            val editedContactName = arg?.getString(CONTACT_NAME)
            val editedContactPhoneNumber = arg?.getString(PHONE_NUMBER)
            val editedContactId = arg?.getInt(CONTACT_ID)
            viewModel.editContact(editedContactId, editedContactName, editedContactPhoneNumber)
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

}