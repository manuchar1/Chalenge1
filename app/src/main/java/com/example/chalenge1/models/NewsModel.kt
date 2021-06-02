package com.example.chalenge1.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NewsModel(

    val id: Int,
    val descriptionEN: String,
    val descriptionKA: String,
    val descriptionRU: String,
    val titleEN: String,
    val titleKA: String,
    val titleRU: String,
    val createdAt: Long,
    val updatedAt: Long,
    val cover: String,
    var isLast: Boolean,
) : Parcelable