package com.ujizin.leafy.preferences.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.ujizin.leafy.core.ui.extensions.openAppInPlayStore

/** Prepare google review feature in a state, */
@Stable
class GoogleReviewState(
    private val reviewManager: ReviewManager,
    private val reviewInfo: ReviewInfo?,
    private var redirectToPlayStore: Boolean = false,
) {

    fun launch(context: Context) {
        val activity = context.getActivity() ?: return context.openAppInPlayStore()
        when {
            redirectToPlayStore -> context.openAppInPlayStore()
            else ->
                reviewInfo?.let {
                    val reviewFlow = reviewManager.launchReviewFlow(activity, reviewInfo)
                    if (reviewFlow.isComplete) context.openAppInPlayStore()
                    reviewFlow.addOnCompleteListener { redirectToPlayStore = true }
                }
        }
    }
}

@Composable
private fun rememberReviewTask(reviewManager: ReviewManager, onError: () -> Unit): ReviewInfo? {
    var reviewInfo: ReviewInfo? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        reviewManager.requestReviewFlow().addOnCompleteListener {
            if (it.isSuccessful) reviewInfo = it.result else onError()
        }
    }

    return reviewInfo
}

@Composable
fun rememberGoogleReview(): GoogleReviewState {
    val context = LocalContext.current
    var redirectToPlayStore by remember { mutableStateOf(false) }
    val reviewManager = remember { ReviewManagerFactory.create(context) }

    val reviewInfo =
        rememberReviewTask(reviewManager = reviewManager, onError = { redirectToPlayStore = true })

    val googleReviewState =
        remember(reviewManager, reviewInfo, redirectToPlayStore) {
            GoogleReviewState(reviewManager, reviewInfo, redirectToPlayStore)
        }

    return googleReviewState
}

/** Get activity recursively based on: https://stackoverflow.com/a/68423182/11903815 */
fun Context.getActivity(): ComponentActivity? =
    when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }
