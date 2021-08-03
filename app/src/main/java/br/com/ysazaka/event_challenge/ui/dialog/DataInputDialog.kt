package br.com.ysazaka.event_challenge.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.ysazaka.event_challenge.R
import br.com.ysazaka.event_challenge.databinding.FragmentDataInputDialogBinding
import br.com.ysazaka.event_challenge.ui.activity.EventDetailActivity


/**
 * Created by Glauco Sazaka on 03/08/2021.
 */
class DataInputDialogFragment : DialogFragment(), View.OnClickListener, TextWatcher {

    private lateinit var binding: FragmentDataInputDialogBinding

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val params = dialog.window!!.attributes
        dialog.window!!.attributes = params
        return dialog
    }

    override fun onResume() {
        super.onResume()

        val dialog = dialog
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val params = dialog.window!!.attributes
        dialog.window!!.attributes = params
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        val params = dialog!!.window!!.attributes
        dialog.window!!.attributes = params
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataInputDialogBinding.inflate(inflater, container, false)

        binding.etName.addTextChangedListener(this)
        binding.etEmail.addTextChangedListener(this)
        binding.btnOK.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnOK -> {
                dismiss()
                (activity as EventDetailActivity).getDialogDataToCheckin(
                    binding.etName.text.toString(), binding.etEmail.text.toString()
                )
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        binding.btnOK.isEnabled =
            binding.etName.text.toString().isNotEmpty() &&
            binding.etEmail.text.toString().isNotEmpty() &&
            isValidEmail(binding.etEmail.text.toString())
    }

}