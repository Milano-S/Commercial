package com.exclr8.n1corporate.APIHelpers.ResponseModels.Login

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
class ImageKeyListResponse: BaseResponse(){
	var ImageKeys: MutableList<String> = mutableListOf()
}
