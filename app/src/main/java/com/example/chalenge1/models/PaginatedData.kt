package com.example.chalenge1.models

import androidx.annotation.Keep

@Keep
data class PaginatedData<T>(

    var isLast: T,
    val updatedAt: Long = 0

)