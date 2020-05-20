package com.esmedevelopment.trackerfit.ui.objective.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esmedevelopment.trackerfit.R
import com.esmedevelopment.trackerfit.api.models.GoalsTypes
import com.esmedevelopment.trackerfit.api.models.MeasurementTypes
import com.esmedevelopment.trackerfit.utils.toDisplayFormat
import java.util.*

class ObjectivesViewModel: ViewModel(){

    private val displayLiveData = MutableLiveData<ObjectivesDisplayData>()
    private val eventsLiveData = MutableLiveData<ObjectivesViewEvents>()

    fun observeLiveData() = displayLiveData as LiveData<ObjectivesDisplayData>
    fun observeliveDataEvents() = eventsLiveData as LiveData<ObjectivesViewEvents>

    fun onGoalButtonClicked() {
        val options = GoalsTypes.values().map { it.stringResource }
       val event=  ObjectivesViewEvents.ShowOptionsDialog(
            R.string.objectives_dialog_name,
            GoalsTypes.values().map { it.stringResource },
           {
               val optionSelected = options[it]
               val currentValue = displayLiveData.value?.copy(goals = optionSelected)
               displayLiveData.postValue(currentValue)
               eventsLiveData.postValue(ObjectivesViewEvents.EmptyEvent)
           }
        )
        eventsLiveData.postValue(event)
    }


    fun onObjectiveButtonClicked(){
        val objectivesOptions = MeasurementTypes.values().map { it.stringResource }
        val objectivesEvent = ObjectivesViewEvents.ShowOptionsDialog(
            R.string.measurement_dialog_name,
            MeasurementTypes.values().map { it.stringResource },
            {
                val optionSelected = objectivesOptions[it]
                val currentValueSelected = displayLiveData.value?.copy(measurement = optionSelected)
                displayLiveData.postValue(currentValueSelected)
                eventsLiveData.postValue(ObjectivesViewEvents.EmptyEvent)
            }
        )
        eventsLiveData.postValue(objectivesEvent)
    }

    fun onDateSelected(it: Long) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = it

        val dateSelected = displayLiveData.value?.copy(goalDate = calendar.time.toDisplayFormat())
        displayLiveData.postValue(dateSelected)
    }

    init {
        displayLiveData.postValue(DEFAULT_VIEW_STATE)
    }

    companion object{
        val DEFAULT_VIEW_STATE = ObjectivesDisplayData(
            R.string.goals,
            R.string.measurements,
            0f,
            "",
            ""
        )
    }

}