package ns.example.mvc_sample.data.remote

import io.reactivex.Single
import ns.example.mvc_sample.data.entity.ApiResponse
import ns.example.mvc_sample.data.entity.CatFact

interface CatFactApi {
    fun get(): Single<ApiResponse<CatFact>>
}