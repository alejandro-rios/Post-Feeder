package com.alejandrorios.gpostfeeder.presentation.feeds

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandrorios.gpostfeeder.R
import com.alejandrorios.gpostfeeder.domain.model.Feeds
import com.alejandrorios.gpostfeeder.presentation.feeds.di.FeedsComponent
import com.alejandrorios.gpostfeeder.utils.progressDialog
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_feeds.*

class FeedsActivity : BaseFeedsActivity(), FeedsContract.View {

    @Inject
    lateinit var presenter: FeedsContract.Presenter

    private var progressAlertDialog: AlertDialog? = null

    override fun injectActivityBuilder(builder: FeedsComponent) {
        builder.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)
        setUpRecycler()

        tvDateTitle?.text = getCurrentDate().toUpperCase()
        tvHelloUser?.text = "Hello Jane"
    }

    override fun onResume() {
        super.onResume()
        presenter.run {
            bind(this@FeedsActivity)
            getFeeds()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun showLoading() {
        progressAlertDialog = progressDialog("Cargando").apply { show() }
    }

    override fun hideLoading() {
        progressAlertDialog?.dismiss()
    }

    private fun setUpRecycler() {
        rvFeeds?.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun loadFeeds(feeds: List<Feeds>) {

        rvFeeds?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FeedsAdapter(feeds as ArrayList<Feeds>)
        }
    }

    private fun getCurrentDate(): String {
        return try {
            val sdf = SimpleDateFormat("EEEE, MMMM d")

            sdf.format(Calendar.getInstance().time)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}
