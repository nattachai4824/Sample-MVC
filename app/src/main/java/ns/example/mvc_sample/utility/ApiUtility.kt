package ns.example.mvc_sample.utility

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import ns.example.mvc_sample.data.entity.ApiErrorResponse
import ns.example.mvc_sample.data.entity.ApiResponse
import ns.example.mvc_sample.extension.toObject

inline fun <reified T>transformApiResponse(response: Response, result: Result<String, FuelError>): ApiResponse<T> {
    val apiStatusCode = response.statusCode
    return if (apiStatusCode == 200) {
        ApiResponse(
            data = result.component1()?.toObject(),
            errorResponse = null
        )
    } else {
        ApiResponse(
            data = null,
            errorResponse = ApiErrorResponse(
                statusCode = apiStatusCode,
                description = String(result.component2()?.response?.data ?: byteArrayOf())
            )
        )
    }
}