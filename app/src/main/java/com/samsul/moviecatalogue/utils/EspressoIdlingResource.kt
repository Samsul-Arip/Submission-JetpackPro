package com.samsul.moviecatalogue.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "global"
    private val testIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = testIdlingResource.increment()
    fun decrement() = testIdlingResource.decrement()
    fun espressoIdlingResource(): IdlingResource = testIdlingResource
}