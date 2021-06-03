package com.example.chalenge1.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//import com.google.gson.annotations.SerializedName
@Parcelize
data class NewsModel(

    val id: Int = 0,
    val descriptionEN: String = "",
    val descriptionKA: String = "",
    val descriptionRU: String = "",
    val titleEN: String = "",
    val titleKA: String = "",
    val titleRU: String = "",
    val cover: String = "",

    //@SerializedName("created_at")
    val createdAt: Long = 0,

) : Parcelable