package com.ujizin.leafy.preferences.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ujizin.leafy.core.ui.components.button.Button
import com.ujizin.leafy.core.ui.components.selector.Selector
import com.ujizin.leafy.core.ui.components.textfield.Placeholder
import com.ujizin.leafy.core.ui.components.textfield.TextField
import com.ujizin.leafy.core.ui.extensions.capitalize
import com.ujizin.leafy.features.preferences.R

@Composable
fun UserSelector(
    modifier: Modifier = Modifier,
    nickname: String,
    onNicknameChanged: (String) -> Unit,
) {
    var showModal by remember { mutableStateOf(false) }
    Selector(
        modifier = modifier,
        title = stringResource(R.string.nickname).capitalize(),
        subTitle = nickname,
        showModal = showModal,
        onModalStateChanged = { showModal = it },
    ) {
        UserTextField(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            label = stringResource(R.string.nickname).capitalize(),
            nickname = nickname,
            onSaveNickname = { nickname ->
                onNicknameChanged(nickname)
                showModal = false
            },
        )
    }
}

@Composable
fun UserTextField(
    modifier: Modifier = Modifier,
    label: String,
    nickname: String,
    onSaveNickname: (String) -> Unit,
) {
    Column(modifier) {
        var newNickname by remember { mutableStateOf(nickname) }

        Text(text = label, style = MaterialTheme.typography.titleMedium)
        TextField(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            value = newNickname,
            placeholder = { Placeholder(text = nickname) },
            onValueChange = { newNickname = it },
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            text = stringResource(R.string.update),
            onClick = { onSaveNickname(newNickname) },
        )
    }
}
