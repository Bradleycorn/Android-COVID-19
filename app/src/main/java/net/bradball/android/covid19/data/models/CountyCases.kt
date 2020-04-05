package net.bradball.android.covid19.data.models

import androidx.room.Entity
import androidx.room.Index
import java.util.Date

@Entity(primaryKeys = ["county","date"],
    indices = [
        Index(value = ["county"], name = "idx_county_date"),
        Index(value = ["county", "date"], name = "idx_county_countydate")
    ])
data class CountyCases(
    val county: String,
    override val date: Date,
    override val cases: Int,
    override val deaths: Int
): Cases