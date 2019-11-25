package ns.example.mvc_sample

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = "https://cat-fact.herokuapp.com/"
    }
}