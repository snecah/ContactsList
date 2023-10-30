package com.example.contactslist.ui.editContact

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.contactslist.R
import com.example.contactslist.databinding.FragmentEditContactBinding

private const val PHONE_NUMBER = "phoneNumber"
private const val CONTACT_NAME = "contactName"
private const val CONTACT_ID = "contactId"
private const val EDIT_CONTACT_FRAGMENT_ID = 1

class EditContactFragment : Fragment(R.layout.fragment_edit_contact) {


    private val binding by viewBinding(FragmentEditContactBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            detailedContactName.setText(arguments?.getString(CONTACT_NAME))
            detailedPhoneNumber.setText(arguments?.getString(PHONE_NUMBER))
            editButton.setOnClickListener {
                val name = detailedContactName.text.toString()
                val phoneNumber = detailedPhoneNumber.text.toString()
                val contactId = arguments?.getInt(CONTACT_ID)
                if (checkFields(name, phoneNumber))
                    emptyFieldsMessage()
                else
                    navigateToList(name, phoneNumber, contactId ?: -1)
            }
        }
    }

    private fun navigateToList(name: String, phoneNumber: String, contactId: Int) {
        val action =
            EditContactFragmentDirections.actionEditContactFragmentToMyContactsFragment(
                phoneNumber,
                name,
                contactId,
                EDIT_CONTACT_FRAGMENT_ID
            )
        findNavController().navigate(action)
    }

    private fun emptyFieldsMessage() {
        Toast.makeText(
            requireContext(),
            getString(R.string.empty_contact_message), Toast.LENGTH_SHORT
        ).show()
    }

    private fun checkFields(name: String, phoneNumber: String): Boolean {
        return name.isEmpty() or phoneNumber.isEmpty()
    }
}