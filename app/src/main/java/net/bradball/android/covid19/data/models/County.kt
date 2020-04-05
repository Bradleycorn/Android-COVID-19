package net.bradball.android.covid19.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], name = "idx_county_id")])
data class County(
    val id: String,
    val displayName: String,
    @Ignore val dailyCases: List<CountyCases> = listOf()
)