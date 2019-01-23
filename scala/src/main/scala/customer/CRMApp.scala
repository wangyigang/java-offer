package customer

import customer.view.CustomerMainView

object CRMApp extends  App {
  //继承自App--app中直接实现了main方法
  new CustomerMainView().mainView()
}
