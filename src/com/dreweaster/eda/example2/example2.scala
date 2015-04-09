package com.dreweaster.eda.example2

trait EventBus {

  def publish(eventType: String, data: String)

  def subscribe(eventType: String, consumer: Consumer)
}

trait Consumer {
  def on(eventType: String, data: String): Unit
}

class View1(eventBus: EventBus) extends Consumer {

  eventBus.subscribe("modelChangedEvent", this)

  def on(eventType: String, data: String) = eventType match {
    case "modelChangedEvent" => // update view1
  }
}

class View2(eventBus: EventBus) extends Consumer {

  eventBus.subscribe("modelChangedEvent", this)

  def on(eventType: String, data: String) = eventType match {
    case "modelChangedEvent" => // update view2
  }
}

class View3(eventBus: EventBus) extends Consumer {

  eventBus.subscribe("modelChangedEvent", this)

  def on(eventType: String, data: String) = eventType match {
    case "modelChangedEvent" => // update view3
  }
}

class Model(var state: String, eventBus: EventBus) {

  def changeState(newState: String) {
    state = newState
    eventBus.publish("modelChangedEvent", newState)
  }
}

