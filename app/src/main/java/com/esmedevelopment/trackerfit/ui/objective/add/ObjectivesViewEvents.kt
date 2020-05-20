package com.esmedevelopment.trackerfit.ui.objective.add

sealed class ObjectivesViewEvents{
    data class ShowOptionsDialog(
        val titleResource: Int,
        val optionsResources: List<Int>,
        val onClick: (Int) -> Unit
    ):ObjectivesViewEvents()
    object EmptyEvent: ObjectivesViewEvents()
}