package com.salih.core.data.remote

import com.salih.core.data.model.request.LoginRequest
import com.salih.core.data.model.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
}