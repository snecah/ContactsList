package com.example.contactslist.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactslist.R
import com.example.contactslist.databinding.FragmentMyContactsBinding
import com.example.contactslist.ui.contacts.model.AddContactItem
import com.example.contactslist.ui.contacts.model.ContactItem

class ContactsFragment : Fragment(R.layout.fragment_my_contacts) {

    private val viewModel by viewModels<ContactsViewModel>()
    private val binding by viewBinding(FragmentMyContactsBinding::bind)
    private val adapter by lazy {ContactsAdapter(onContactItemClicked())}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.generateContacts()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        adapter.setItems(viewModel.getContacts())
    }


    private fun onContactItemClicked(): (ContactItem) -> Unit = {
        val action = ContactsFragmentDirections.actionMyContactsFragmentToEditContactFragment(it.phoneNumber, it.name + it.surname)
        findNavController().navigate(action)
    }
}