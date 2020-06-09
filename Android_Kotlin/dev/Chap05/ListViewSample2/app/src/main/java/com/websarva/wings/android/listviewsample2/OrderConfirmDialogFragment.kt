package com.websarva.wings.android.listviewsample2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class OrderConfirmDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create Dialog Builder
        val builder = AlertDialog.Builder(activity)
        // Set Dialog Title
        builder.setTitle(R.string.dialog_title)
        // Set Dialog Message
        builder.setMessage(R.string.dialog_msg)
        // Set Positive Button
        builder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListener())
        // Set Negative Button
        builder.setNegativeButton(R.string.dialog_btn_ng, DialogButtonClickListener())
        // Set Neutral Button
        builder.setNeutralButton(R.string.dialog_btn_nu, DialogButtonClickListener())
        // Create Dialog object
        return builder.create()
    }

    // Member class that Action Button of Dialog is tapped
    private inner class DialogButtonClickListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            // For Toast message
            var msg = ""
            // Switch by Tapped Action Button
            when(which) {
                // Positive Button
                DialogInterface.BUTTON_POSITIVE ->
                    // Message for Order
                    msg = getString(R.string.dialog_ok_toast)
                // Negative Button
                DialogInterface.BUTTON_NEGATIVE ->
                    // Message for Cancel
                    msg = getString(R.string.dialog_ng_toast)
                // Neutral Button
                DialogInterface.BUTTON_NEUTRAL ->
                    // Message for Contact
                    msg = getString(R.string.dialog_nu_toast)
            }
            // Show Toast
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }

    }
}