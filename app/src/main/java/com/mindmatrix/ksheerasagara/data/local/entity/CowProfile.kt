package com.mindmatrix.ksheerasagara.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a cow profile in the Ksheera-Sagara system.
 */
@Entity(tableName = "cow_profiles")
data class CowProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val nameOrTag: String,

    // Unix timestamp representing when this profile was created
    val createdAt: Long = System.currentTimeMillis()
)
