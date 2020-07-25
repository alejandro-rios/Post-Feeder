package com.alejandrorios.gpostfeeder.presentation.createpost

import com.alejandrorios.domain.model.Feeds
import com.alejandrorios.gpostfeeder.utils.CoroutinesContextProvider
import com.alejandrorios.gpostfeeder.utils.DEFAULT_LAST_NAME
import com.alejandrorios.gpostfeeder.utils.DEFAULT_NAME
import com.alejandrorios.gpostfeeder.utils.IMAGE_EMPTY_MESSAGE
import com.alejandrorios.gpostfeeder.utils.POST_EMPTY_MESSAGE
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.Job

/**
 * Created by Alejandro Rios on 7/25/20
 */
class CreatePostPresenter @Inject constructor(
    override val coroutinesContextProvider: CoroutinesContextProvider
) : CreatePostContract.Presenter {

    override val parentJob = Job()
    override var view: CreatePostContract.View? = null

    override fun savePost(post: String, imagePath: String?) {
        view?.run {
            when {
                post.isEmpty() -> {
                    showError(POST_EMPTY_MESSAGE)
                }
                imagePath.isNullOrEmpty() -> {
                    showError(IMAGE_EMPTY_MESSAGE)
                }
                else -> {
                    val feed = Feeds(
                        id = 0,
                        firstName = DEFAULT_NAME,
                        lastName = DEFAULT_LAST_NAME,
                        postBody = post,
                        unixTimestamp = Calendar.getInstance().timeInMillis.toString(),
                        image = imagePath
                    )
                    gotToFeeds(feed)
                }
            }
        }
    }
}
