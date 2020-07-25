package com.alejandrorios.gpostfeeder.presentation.feeds

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.gpostfeeder.R
import com.alejandrorios.gpostfeeder.presentation.createpost.CreatePostActivity
import com.alejandrorios.gpostfeeder.presentation.feeds.di.FeedsComponent
import com.alejandrorios.gpostfeeder.utils.ARGUMENT_FEED
import com.alejandrorios.gpostfeeder.utils.HI_DEFAULT_USER
import com.alejandrorios.gpostfeeder.utils.LOADING
import com.alejandrorios.gpostfeeder.utils.PRETTY_DATE_FORMAT
import com.alejandrorios.gpostfeeder.utils.launchActivity
import com.alejandrorios.gpostfeeder.utils.progressDialog
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_feeds.rvFeeds
import kotlinx.android.synthetic.main.activity_feeds.tvDateTitle
import kotlinx.android.synthetic.main.activity_feeds.tvHelloUser
import kotlinx.android.synthetic.main.activity_feeds.tvWhatsOnYourMind

class FeedsActivity : BaseFeedsActivity(), FeedsContract.View {

    @Inject
    lateinit var presenter: FeedsContract.Presenter

    private var progressAlertDialog: AlertDialog? = null
    private var myFeed: Feeds? = null

    override fun injectActivityBuilder(builder: FeedsComponent) {
        builder.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)
        setUpRecycler()

        tvDateTitle?.text = getCurrentDate().toUpperCase(Locale.ROOT)
        tvHelloUser?.text = HI_DEFAULT_USER
        tvWhatsOnYourMind?.setOnClickListener {
            launchActivity<CreatePostActivity> { }
        }

        myFeed = intent?.extras?.getParcelable(ARGUMENT_FEED)
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
        progressAlertDialog = progressDialog(LOADING).apply { show() }
    }

    override fun hideLoading() {
        progressAlertDialog?.dismiss()
    }

    private fun setUpRecycler() {
        rvFeeds?.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun loadFeeds(feeds: List<Feeds>) {
        val feedList = feeds as ArrayList<Feeds>

        if (myFeed != null) {
            feedList.add(0, myFeed!!)
        }

        rvFeeds?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FeedsAdapter(feedList)
        }
    }

    private fun getCurrentDate(): String {
        return try {
            val sdf = SimpleDateFormat(PRETTY_DATE_FORMAT, Locale.ROOT)

            sdf.format(Calendar.getInstance().time)
        } catch (exception: Exception) {
            return exception.toString()
        }
    }
}
