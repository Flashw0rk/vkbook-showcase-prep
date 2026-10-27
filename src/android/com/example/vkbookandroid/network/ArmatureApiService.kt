package com.example.vkbookandroid.network

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 * API интерфейс для работы с сервером.
 * Демонстрирует структуру сетевого слоя (Retrofit).
 * Реализация скрыта для защиты интеллектуальной собственности.
 */
interface ArmatureApiService {

    @GET("actuator/health")
    suspend fun getHealth(): Response<Map<String, Any>>

    @GET("api/files/list")
    suspend fun getAllFiles(): Response<Map<String, Any>>

    @GET("api/files/download")
    suspend fun downloadFileByName(@Query("filename") filename: String): Response<ResponseBody>

    @GET("api/updates/check")
    suspend fun checkUpdates(@Query("filename") filename: String? = null): Response<ResponseBody>

    @GET("api/updates/download")
    suspend fun downloadUpdatesFile(@Query("filename") filename: String): Response<ResponseBody>

    @Multipart
    @POST("api/updates/upload")
    suspend fun uploadUpdatesFile(
        @Part file: MultipartBody.Part,
        @Query("filename") filename: String? = null
    ): Response<ResponseBody>
}
