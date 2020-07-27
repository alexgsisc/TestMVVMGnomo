package com.alexisgs.apignomo.personajes.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("Brastlewark") val characters: List<Characters>
)