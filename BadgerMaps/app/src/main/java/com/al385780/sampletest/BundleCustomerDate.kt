package com.al385780.sampletest

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["customerName", "dateChecked"],
    foreignKeys = [ForeignKey(
        entity = CustomerName::class,
        parentColumns = ["name"],
        childColumns = ["customerID"]
    ),
        ForeignKey(
            entity = CustomerCheckDate::class,
            parentColumns = ["date"],
            childColumns = [""]
        )],
    indices = [Index(value = ["customerName"], unique = true), Index(value = ["dateChecked"], unique = true)],
)

data class BundleCustomerDate(val customerID:Int, val name: String, val date: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(customerID)
        parcel.writeString(name)
        parcel.writeString(date)
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BundleCustomerDate> {
        override fun createFromParcel(parcel: Parcel): BundleCustomerDate {
            return BundleCustomerDate(parcel)
        }

        override fun newArray(size: Int): Array<BundleCustomerDate?> {
            return arrayOfNulls(size)
        }
    }
}
