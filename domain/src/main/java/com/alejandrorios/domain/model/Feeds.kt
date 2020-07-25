package com.alejandrorios.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Alejandro Rios on 7/25/20
 */
@Parcelize
data class Feeds(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val postBody: String,
    val unixTimestamp: String,
    val image: String?
) : Parcelable
