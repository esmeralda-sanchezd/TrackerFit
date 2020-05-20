package com.esmedevelopment.trackerfit.ui.objective.add

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.esmedevelopment.trackerfit.R
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_objectives.*


/**
 * A simple [Fragment] subclass.
 * Use the [ObjectivesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObjectivesFragment : Fragment() {
    lateinit var objectivesViewModel: ObjectivesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        objectivesViewModel = ViewModelProviders.of(this).get(ObjectivesViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_objectives, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        objectivesViewModel.observeLiveData().observe(this.viewLifecycleOwner, Observer {
            renderData(it)
        })
        objectivesViewModel.observeliveDataEvents().observe(viewLifecycleOwner, Observer {
            handleEvent(it)
        })
        goals_button.setOnClickListener {
            objectivesViewModel.onGoalButtonClicked()
        }

        measurements_button.setOnClickListener {
            objectivesViewModel.onObjectiveButtonClicked()
        }

        date_picker_edit_text.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker().build()
            builder.addOnPositiveButtonClickListener {
               objectivesViewModel.onDateSelected(it)

            }
            builder.show(childFragmentManager, "DATE_PICKER")
        }
    }

    private fun handleEvent(event: ObjectivesViewEvents) {
        when(event){
            is ObjectivesViewEvents.ShowOptionsDialog -> {
                val options = event.optionsResources.map {
                    getString(it)
                }.toTypedArray()
                val builder = activity?.let { it -> AlertDialog.Builder(it) }
                builder?.setTitle(event.titleResource)
                    ?.setItems(options,
                        DialogInterface.OnClickListener { dialog, which ->
                            event.onClick(which)

                        })
                builder?.show()
            }
        }
    }

    private fun renderData(data: ObjectivesDisplayData) {
        goals_button.text= getString(data.goals)
        measurements_button.text = getString(data.measurement)
        goal_text_field.editText?.setText(data.goalValue.toString(), TextView.BufferType.EDITABLE);
        date_picker_field.editText?.setText(data.goalDate.toString(), TextView.BufferType.EDITABLE);
        notes_text.editText?.setText(data.notes.toString(), TextView.BufferType.EDITABLE);
    }


}
