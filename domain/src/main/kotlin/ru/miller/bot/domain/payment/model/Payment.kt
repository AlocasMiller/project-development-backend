package ru.miller.bot.domain.payment.model

import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.lesson.model.Lesson
import java.time.Instant

class Payment(
    val id: Id<Payment>,
    val amount: Int,
    val status: Status,
    val paymentDate: Instant,
    val document: ByteArray,
    val lessonId: Id<Lesson>,
)
