package com.al385780.sampletest

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CustomerCheckDate (
    @PrimaryKey
    val date: String
) {
    override fun toString(): String {
        return date
    }
}