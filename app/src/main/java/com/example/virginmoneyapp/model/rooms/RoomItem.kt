package com.example.virginmoneyapp.model.rooms

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RoomItem (
    val createdAt: String,
    val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int
):Parcelable