package com.cp.babygrowth.data.profileanak

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseAddProfile(

    @field:SerializedName("listadd")
    val listStory: List<AddProfileItem>
)

@Parcelize
data class AddProfileItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("tempat")
    val place: String? = null,

    @field:SerializedName("tanggal")
    val date_birth: String? = null,

    @field:SerializedName("gender")
    val gender: Boolean = false,

    @field:SerializedName("berat")
    val berat: String? = null,

    @field:SerializedName("tinggi")
    val tinggi: String? = null,
) : Parcelable