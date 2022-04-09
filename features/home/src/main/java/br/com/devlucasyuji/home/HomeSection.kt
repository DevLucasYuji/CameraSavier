package br.com.devlucasyuji.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.devlucasyuji.components.Icons
import br.com.devlucasyuji.components.animation.Animation
import br.com.devlucasyuji.components.atomic.atoms.ButtonIcon
import br.com.devlucasyuji.components.atomic.organisms.header

@Composable
fun NavController.HomeSection() {
    LazyColumn {
        header(
            title = "Hi Lucas!",
            subTitle = "Welcome back",
            leadingIcon = ButtonIcon(Icons.Hamburger, Animation.SlideToEnd),
            trailingIcon = ButtonIcon(Icons.Magnifier, Animation.SlideToStart)
        )
    }
}
