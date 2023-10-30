package com.example.contactslist.ui.createContact

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactslist.R
import com.example.contactslist.databinding.FragmentCreateContactBinding

private const val CREATE_CONTACT_FRAGMENT_ID = 0
class CreateContactFragment : Fragment(R.layout.fragment_create_contact) {
    private val binding by viewBinding(FragmentCreateContactBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            editButton.setOnClickListener {
                val name = createContactName.text.toString()
                val phoneNumber = createContactNumber.text.toString()
                if (checkFields(name, phoneNumber)) {
                    emptyFieldsMessage()
                } else
                    navigateToList(name, phoneNumber)
            }
        }
    }

    private fun checkFields(name: String, phoneNumber: String): Boolean {
        return name.isEmpty() or phoneNumber.isEmpty()
    }

    private fun emptyFieldsMessage() {
        Toast.makeText(
            requireContext(),
            getString(R.string.empty_contact_message), Toast.LENGTH_SHORT
        ).show()
    }

    private fun navigateToList(name: String, phoneNumber: String) {
        val action =
            CreateContactFragmentDirections.actionCreateContactFragmentToMyContactsFragment(
                phoneNumber,
                name,
                -1,
                CREATE_CONTACT_FRAGMENT_ID
            )
        findNavController().navigate(action)
    }
}