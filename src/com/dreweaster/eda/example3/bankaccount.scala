package com.dreweaster.eda.example3

import java.util.UUID

trait Event

trait EventBus {

  def publish(event: Event)

  def subscribe(eventType: Class[_ <: Event], consumer: Consumer)
}

trait Consumer {
  def on: scala.PartialFunction[scala.Any, scala.Unit]
}

case class AccountCreated(id: UUID, customerId: UUID) extends Event

case class DepositMade(id: UUID, amount: Double) extends Event

case class WithdrawalMade(id: UUID, amount: Double) extends Event

// Producer
class BankAccount(eventBus: EventBus) {

  var id: UUID = _
  var balance: Double = 0.0

  def create(customerId: UUID) {
    id = UUID.randomUUID()
    // TODO: save to db
    eventBus.publish(AccountCreated(id, customerId))
  }

  def deposit(amount: Double) {
    balance = balance + amount
    // TODO: save to db
    eventBus.publish(DepositMade(id, amount))
  }

  def withdraw(amount: Double) {
    balance = balance - amount
    // TODO: save to db
    eventBus.publish(WithdrawalMade(id, amount))
  }
}

// Consumer
class WelcomeEmailService(eventBus: EventBus) extends Consumer {

  eventBus.subscribe(classOf[AccountCreated], this)

  def on = {
    case AccountCreated(id, customerId: UUID) => // TODO: send email here
  }
}
