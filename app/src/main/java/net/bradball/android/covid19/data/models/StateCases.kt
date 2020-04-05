package net.bradball.android.covid19.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(indices = [Index(value = ["date"], name = "idx_state_date")])
data class StateCases(
    @PrimaryKey override val date: Date,
    override val cases: Int,
    override val deaths: Int
): Cases