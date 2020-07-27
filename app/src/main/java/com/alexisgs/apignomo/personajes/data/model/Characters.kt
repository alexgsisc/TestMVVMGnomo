package com.alexisgs.apignomo.personajes.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Characters(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("age") val age: Int,
    @SerializedName("weight") val weight: Double,
    @SerializedName("height") val height: Double,
    @SerializedName("hair_color") val hair_color: String,
    @SerializedName("professions") val professions: ArrayList<String>,
    @SerializedName("friends") val friends: List<String>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(thumbnail)
        parcel.writeInt(age)
        parcel.writeDouble(weight)
        parcel.writeDouble(height)
        parcel.writeString(hair_color)
        parcel.writeStringList(professions)
        parcel.writeStringList(friends)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Characters> {
        override fun createFromParcel(parcel: Parcel): Characters {
            return Characters(parcel)
        }

        override fun newArray(size: Int): Array<Characters?> {
            return arrayOfNulls(size)
        }
    }

}



