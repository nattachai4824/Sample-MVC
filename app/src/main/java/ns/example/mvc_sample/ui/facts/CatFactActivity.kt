package ns.example.mvc_sample.ui.facts

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cat_fact.*
import ns.example.mvc_sample.R
import ns.example.mvc_sample.data.entity.Fact
import ns.example.mvc_sample.data.remote.CatFactApi
import ns.example.mvc_sample.data.remote.impl.CatFactApiImpl
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

class CatFactActivity : AppCompatActivity() {

    private val mCatFacts = mutableListOf<Fact>()
    private var mCatFactApi: CatFactApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_fact)

        mCatFactApi = CatFactApiImpl()
        initView()
        requestCatFacts()
    }

    private fun initView() {
        with(rvFact) {
            layoutManager = LinearLayoutManager(this@CatFactActivity)
            adapter = CatFactAdapter(mCatFacts)
        }
    }

    @SuppressLint("CheckResult")
    private fun requestCatFacts() {
        showContentLoading()

        mCatFactApi?.let { catFactApi ->
            catFactApi.get().subscribe({ apiResponse ->
                dismissContentLoading()

                val data = apiResponse.data
                data?.let {
                    updateContent(it.facts)
                } ?: {
                    showRequestFailure()
                }()
            }, {})
        } ?: {
            showInitializationFailure()
        }()
    }

    private fun updateContent(catFacts: List<Fact>) {
        mCatFacts.addAll(catFacts)
    }

    private fun showRequestFailure() {
        alert("Request Failure. Please try again later") {
            okButton {

            }
        }.show()
    }

    private fun showInitializationFailure() {
        alert("Components not initialized") {
            okButton {

            }
        }.show()
    }

    private fun showContentLoading() {
        srlContent.isRefreshing = true
    }

    private fun dismissContentLoading() {
        srlContent.isRefreshing = false
    }
}