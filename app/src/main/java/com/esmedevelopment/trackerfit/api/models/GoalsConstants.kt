package com.esmedevelopment.trackerfit.api.models

import com.esmedevelopment.trackerfit.R

enum class GoalsTypes( val stringResource: Int){
     MAINTAIN(R.string.maintain),
     GAIN(R.string.gain),
     LOSE(R.string.lose);
}

enum class MeasurementTypes( val stringResource: Int){
    BMI(R.string.bmi),
    WEIGHT(R.string.weight),
    BODY_FAT(R.string.body_fat)
}