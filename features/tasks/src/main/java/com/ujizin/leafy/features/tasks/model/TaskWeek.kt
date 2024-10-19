package com.ujizin.leafy.features.tasks.model

import com.ujizin.leafy.domain.model.WeekDay

data class TaskWeek(val weekDay: WeekDay, val tasks: List<Task>)
