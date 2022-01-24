package com.samsul.moviecatalogue.data

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {

    companion object {

        fun <T> success(body: T): ApiResponse<T> {
            return ApiResponse(StatusResponse.SUCCESS, body, null)
        }

        fun <T> empty(msg: String, body: T): ApiResponse<T> {
            return ApiResponse(StatusResponse.EMPTY, body, msg)
        }

        fun <T> error(msg: String, body: T): ApiResponse<T> {
            return ApiResponse(StatusResponse.ERROR, body, msg)
        }
    }

}