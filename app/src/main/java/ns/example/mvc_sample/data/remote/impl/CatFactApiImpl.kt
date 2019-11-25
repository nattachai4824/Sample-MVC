package ns.example.mvc_sample.data.remote.impl

import com.github.kittinunf.fuel.httpGet
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ns.example.mvc_sample.data.entity.ApiResponse
import ns.example.mvc_sample.data.entity.CatFact
import ns.example.mvc_sample.data.remote.CatFactApi
import ns.example.mvc_sample.utility.transformApiResponse

class CatFactApiImpl : CatFactApi {
    override fun get(): Single<ApiResponse<CatFact>> {
        return Single.create<ApiResponse<CatFact>> {
            "facts".httpGet()
                .responseString { request, response, result ->
                    val apiResponse = transformApiResponse<CatFact>(response, result)

                    it.onSuccess(apiResponse)
                }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}