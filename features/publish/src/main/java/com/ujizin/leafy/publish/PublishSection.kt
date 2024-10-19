package com.ujizin.leafy.publish

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ujizin.leafy.core.ui.components.Section
import com.ujizin.leafy.core.ui.components.animated.AnimatedButtonIcon
import com.ujizin.leafy.core.ui.components.animated.animation.Animation
import com.ujizin.leafy.core.ui.components.button.Button
import com.ujizin.leafy.core.ui.components.image.Icons
import com.ujizin.leafy.core.ui.components.textfield.Placeholder
import com.ujizin.leafy.core.ui.components.textfield.TextField
import com.ujizin.leafy.core.ui.extensions.OnClick
import com.ujizin.leafy.core.ui.extensions.paddingScreen
import com.ujizin.leafy.features.publish.R
import com.ujizin.leafy.publish.viewmodel.PublishViewModel

@Composable
fun PublishRoute(
    onBackPressed: OnClick,
    onFinishPublish: OnClick,
    viewModel: PublishViewModel = hiltViewModel(),
) {
    PublishContent(
        onBackPressed = onBackPressed,
        onNextClicked = { title, description ->
            viewModel.sendDraftPlant(title, description, onFinishPublish)
        },
    )
}

@Composable
fun PublishContent(
    onBackPressed: OnClick,
    onNextClicked: (title: String, description: String) -> Unit,
) {
    Section(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        title = stringResource(R.string.publish_title),
        subTitle = stringResource(R.string.publish_description),
        animation = Animation.SlideToTopLargeDuration,
        trailingIcon = { AnimatedButtonIcon(icon = Icons.Back, onClick = onBackPressed) },
    ) {
        var title by rememberSaveable { mutableStateOf("") }
        var description by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp, start = 20.dp, end = 20.dp),
            placeholder = { Placeholder(text = stringResource(R.string.title)) },
            value = title,
            onValueChange = { title = it },
        )
        TextField(
            modifier =
                Modifier.fillMaxWidth()
                    .aspectRatio(1.75F)
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp),
            placeholder = { Placeholder(text = stringResource(R.string.description)) },
            singleLine = false,
            value = description,
            onValueChange = { description = it },
        )
        Spacer(modifier = Modifier.weight(1F))
        Button(
            modifier = Modifier.fillMaxWidth().paddingScreen(vertical = 16.dp),
            enabled = title.isNotBlank() && description.isNotBlank(),
            text = stringResource(R.string.next),
            onClick = { onNextClicked(title, description) },
        )
    }
}
