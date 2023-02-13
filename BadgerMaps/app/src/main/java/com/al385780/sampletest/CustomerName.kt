package com.al385780.sampletest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerName (
    @PrimaryKey
    val name: String,
    ) {
    override fun toString(): String {
        return name
    }

}