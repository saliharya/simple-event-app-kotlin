package com.salih.core.data.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
)