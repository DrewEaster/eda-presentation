package com.dreweaster.eda.example1

class View1 {
  def view1Update(data: String) {
    // update view1
  }
}

class View2 {
  def view2Update(data: String) {
    // update view2
  }
}

class View3 {
  def view3Update(data: String) {
    // update view3
  }
}

class Model(var state: String, view1: View1, view2: View2, view3: View3) {

  def changeState(newState: String) {
    state = newState
    view1.view1Update(state)
    view2.view2Update(state)
    view3.view3Update(state)
  }
}
