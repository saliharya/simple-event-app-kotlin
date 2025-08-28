package com.salih.presentation.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.salih.core.domain.model.EventEntity
import com.salih.presentation.databinding.FragmentNewEventBinding
import com.salih.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class NewEventFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNewEventBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventViewModel by viewModel()

    private var selectedThumbnailUri: Uri? = null
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                selectedThumbnailUri = uri
                grantUriPermission(uri)
                updateUploadButtonText(uri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener { dismiss() }

        binding.btnUploadThumbnail.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.etStartDate.setOnClickListener {
            showDatePicker { date -> binding.etStartDate.setText(date) }
        }

        binding.etEndDate.setOnClickListener {
            showDatePicker { date -> binding.etEndDate.setText(date) }
        }

        binding.etStartTime.setOnClickListener {
            showTimePicker { time -> binding.etStartTime.setText(time) }
        }

        binding.etEndTime.setOnClickListener {
            showTimePicker { time -> binding.etEndTime.setText(time) }
        }

        binding.btnSubmitEvent.setOnClickListener {
            submitEvent()
        }
    }

    private fun grantUriPermission(uri: Uri) {
        val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        try {
            requireContext().contentResolver.takePersistableUriPermission(uri, takeFlags)
        } catch (e: SecurityException) {
            Log.e("NewEventFragment", "Failed to take persistable permission", e)
        }
    }

    private fun updateUploadButtonText(uri: Uri) {
        // Ambil nama file dari URI (fallback ke uri.toString() jika tidak ditemukan)
        val fileName = getFileNameFromUri(uri) ?: uri.lastPathSegment ?: "Selected Image"
        binding.btnUploadThumbnail.text = fileName
    }

    private fun getFileNameFromUri(uri: Uri): String? {
        val contentResolver = requireContext().contentResolver
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val nameIndex = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                if (nameIndex != -1) {
                    return it.getString(nameIndex)
                }
            }
        }
        return null
    }

    private fun submitEvent() = with(binding) {
        val eventName = etEventName.text.toString().trim()
        val description = etDescription.text.toString().trim()
        val location = etLocation.text.toString().trim()
        val startDate = etStartDate.text.toString().trim()
        val startTime = etStartTime.text.toString().trim()
        val endDate = etEndDate.text.toString().trim()
        val endTime = etEndTime.text.toString().trim()
        val organizer = etOrganizer.text.toString().trim()

        if (eventName.isEmpty() || startDate.isEmpty() || startTime.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Please fill at least event name and start date/time",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val startDateTime = "$startDate $startTime"
        val endDateTime = if (endDate.isNotEmpty() && endTime.isNotEmpty()) {
            "$endDate $endTime"
        } else {
            ""
        }
        val thumbnailUriString = selectedThumbnailUri?.toString() ?: ""
        val newEvent = EventEntity(
            id = 0L,
            name = eventName,
            thumbnailUrl = thumbnailUriString,
            startDateTime = startDateTime,
            endDateTime = endDateTime,
            organizer = organizer,
            location = location,
            description = description
        )

        viewModel.insertEvent(newEvent)
        dismiss()
    }

    override fun onStart() {
        super.onStart()
        val bottomSheet =
            dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val behavior = com.google.android.material.bottomsheet.BottomSheetBehavior.from(it)
            behavior.state =
                com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val selectedDate = String.format("%02d-%02d-%d", dayOfMonth, month + 1, year)
                onDateSelected(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker(onTimeSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                onTimeSelected(selectedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }
}