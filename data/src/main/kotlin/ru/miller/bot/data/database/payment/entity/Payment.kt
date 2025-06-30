package ru.miller.bot.data.database.payment.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestamp
import ru.miller.bot.data.database.lesson.entity.LessonEntity
import ru.miller.bot.data.database.lesson.entity.LessonTable
import ru.miller.bot.domain.common.model.Id
import ru.miller.bot.domain.lesson.model.Lesson
import ru.miller.bot.domain.payment.model.Payment
import ru.miller.bot.domain.payment.model.Status
import java.util.UUID

object PaymentTable : UUIDTable("payments") {

    val amount = integer("amount")
    val paymentDate = timestamp("payment_date")
    val status = enumerationByName<Status>("status", 20)
    val document = binary("document")

    val lessonId = reference("lesson_id", LessonTable)
}

class PaymentEntity(id: EntityID<UUID>) : UUIDEntity(id) {

    var amount by PaymentTable.amount
    var paymentDate by PaymentTable.paymentDate
    var status by PaymentTable.status
    var document by PaymentTable.document

    var lesson by LessonEntity referencedOn PaymentTable.lessonId

    companion object : UUIDEntityClass<PaymentEntity>(PaymentTable)
}

fun PaymentEntity.toDomain() = Payment(
    id = Id<Payment>(id.value),
    amount = amount,
    paymentDate = paymentDate,
    status = status,
    document = document,
    lessonId = Id<Lesson>(lesson.id.value)
)
