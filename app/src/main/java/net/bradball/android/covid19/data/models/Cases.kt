package net.bradball.android.covid19.data.models

import java.util.*

interface Cases {
    val date: Date
    val cases: Int
    val deaths: Int
}