package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_new_mask.*
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment

class ItemMaskFragment : Fragment() {

    private val phoneNumberAlertText = getString(R.string.alert_phone_number)
    private lateinit var viewModel: ItemMaskViewModel
    private lateinit var viewModelFactory: ItemMaskViewModelFactory
    private lateinit var fm: FragmentManager
    private var idMask: Int? = null

    companion object {

        const val MASK_ID = "mask_id"
        private const val REQUEST_CODE_PERMISSIONS_PHONE = 1

        fun newInstance(mask: Mask? = null): ItemMaskFragment {
            val fr = ItemMaskFragment()
            if (mask != null) {
                val bundle = Bundle()
                bundle.putInt(MASK_ID, mask.id)
                fr.arguments = bundle
            }
            return fr
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fm = activity?.supportFragmentManager!!
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory =
            ItemMaskViewModelFactory((activity?.applicationContext as PhoneMasksApplication).repository)

        Log.i("MasksListFragment", "Called ListMasksViewModel.get")
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemMaskViewModel::class.java)

        idMask = arguments?.getInt(MASK_ID)

        idMask?.let {
            lifecycleScope.launch {
                val mask = viewModel.getMaskById(it)
                name_mask.setText(mask.title)
                phone_mask.setText(mask.numeric)
                phone_mask.addTextChangedListener(object : TextWatcher {

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun afterTextChanged(chars: Editable?) {
                        if (chars?.length!! < 7 || chars.isNullOrEmpty()) {
                            phone_mask.error = phoneNumberAlertText
                        }
                    }
                })
            }
        }

        return inflater.inflate(R.layout.fragment_new_mask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add.setOnClickListener {

            checkingPermissionPhone()

            if (!TextUtils.isEmpty(phone_mask.text)) {

                if (idMask != null && phone_mask.text.length == 7) {
                    viewModel.update(
                        Mask(
                            id = idMask!!,
                            numeric = phone_mask.text.toString(),
                            title = name_mask.text.toString()
                        )
                    )
                    fm.commit {
                        replace(R.id.fragment_container_view, MasksListFragment.newInstance())
                    }
                } else if (phone_mask.text.length == 7) {
                    viewModel.insert(
                        Mask(
                            numeric = phone_mask.text.toString(),
                            title = name_mask.text.toString()
                        )
                    )
                    fm.commit {
                        replace(R.id.fragment_container_view, MasksListFragment.newInstance())
                    }
                } else {
                    phone_mask.error = phoneNumberAlertText
                }
            } else {
                phone_mask.error = phoneNumberAlertText
            }
        }

        button_delete.setOnClickListener {
            if (idMask != null) {
                viewModel.delete(idMask!!)
            }
            fm.commit {
                replace(R.id.fragment_container_view, MasksListFragment.newInstance())
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSIONS_PHONE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    //TODO если пользователь отклонил разрешение - показать диалог
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun checkingPermissionPhone() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) -> {
                // TODO сделать диалог объясняющий зачем нужно разрешение
            }
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.MODIFY_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            shouldShowRequestPermissionRationale(Manifest.permission.MODIFY_PHONE_STATE) -> {
                // TODO сделать диалог объясняющий зачем нужно разрешение
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(
                    arrayOf(Manifest.permission.READ_PHONE_STATE),
                    REQUEST_CODE_PERMISSIONS_PHONE
                )
            }
        }
    }
}