package com.ujizin.leafy.core.repository.utils

import com.ujizin.leafy.domain.model.Plant
import java.io.File
import java.util.UUID

fun createDraft() =
    Plant(
        title = "untitled",
        filePath = File.createTempFile(UUID.randomUUID().toString(), ".jpg").path,
        description = "no description",
        favorite = false,
    )
