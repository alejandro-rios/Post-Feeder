package com.alejandrorios.gpostfeeder.presentation.createpost

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.alejandrorios.gpostfeeder.R
import com.alejandrorios.gpostfeeder.presentation.createpost.di.CreatePostComponent
import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsActivity
import com.alejandrorios.gpostfeeder.utils.ARGUMENT_FEED
import com.alejandrorios.gpostfeeder.utils.COUNTER
import com.alejandrorios.gpostfeeder.utils.DEFAULT_HEIGHT
import com.alejandrorios.gpostfeeder.utils.DEFAULT_WIDTH
import com.alejandrorios.gpostfeeder.utils.GALLERY_IMAGE_REQ_CODE
import com.alejandrorios.gpostfeeder.utils.JPEG_TYPE
import com.alejandrorios.gpostfeeder.utils.JPG_TYPE
import com.alejandrorios.gpostfeeder.utils.PNG_TYPE
import com.alejandrorios.gpostfeeder.utils.afterTextChanged
import com.alejandrorios.gpostfeeder.utils.launchActivity
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_create_post.edPostInfo
import kotlinx.android.synthetic.main.activity_create_post.ivImage
import kotlinx.android.synthetic.main.activity_create_post.tvAddPhoto
import kotlinx.android.synthetic.main.activity_create_post.tvCounter
import kotlinx.android.synthetic.main.create_post_toolbar.createPostToolbar
import kotlinx.android.synthetic.main.create_post_toolbar.tvSharePost

class CreatePostActivity : BaseCreatePostActivity(), CreatePostContract.View {

    @Inject
    lateinit var presenter: CreatePostContract.Presenter

    private var filePath: String? = null
    private val fileTypes = arrayOf(
        PNG_TYPE,
        JPG_TYPE,
        JPEG_TYPE
    )

    override fun injectActivityBuilder(builder: CreatePostComponent) {
        builder.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        setSupportActionBar(createPostToolbar)

        edPostInfo?.afterTextChanged {
            tvCounter?.text = String.format(COUNTER, it.length)
        }

        tvAddPhoto?.setOnClickListener {
            openGallery()
        }

        tvSharePost?.setOnClickListener {
            presenter.savePost(
                edPostInfo?.text.toString(),
                filePath
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun openGallery() {
        ImagePicker.with(this)
            .crop()
            .galleryOnly()
            .galleryMimeTypes(mimeTypes = fileTypes)
            .maxResultSize(DEFAULT_WIDTH, DEFAULT_HEIGHT)
            .start(GALLERY_IMAGE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                filePath = ImagePicker.getFilePath(data)!!
                Glide.with(this).load(filePath).into(ivImage)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun gotToFeeds(feed: com.alejandrorios.domain.model.Feeds) {
        launchActivity<FeedsActivity> {
            putExtra(ARGUMENT_FEED, feed)
        }
        finish()
    }
}
