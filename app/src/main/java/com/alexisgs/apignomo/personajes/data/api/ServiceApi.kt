package com.alexisgs.apignomo.personajes.data.api

import com.alexisgs.apignomo.common.service.BaseServiceBuilders
import com.alexisgs.apignomo.personajes.data.model.ApiResponse
import rx.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApi {
    @GET("master/data.json")
    fun getCharacters(): Observable<ApiResponse>

    class Builder() : BaseServiceBuilders<ServiceApi>() {
        override fun build(): ServiceApi {
            return retrofitBuild.build().create(ServiceApi::class.java)
        }
    }
}