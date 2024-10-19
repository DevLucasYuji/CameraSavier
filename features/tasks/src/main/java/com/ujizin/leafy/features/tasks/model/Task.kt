package com.ujizin.leafy.features.tasks.model

import com.ujizin.leafy.domain.model.Alarm
import com.ujizin.leafy.domain.model.Plant

data class Task(val plant: Plant, val alarm: Alarm)
