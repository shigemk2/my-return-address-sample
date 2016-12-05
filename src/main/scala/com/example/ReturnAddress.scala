package com.example

import akka.actor._

case class Request(what: String)
case class RequestComplex(what: String)
case class Reply(what: String)
case class ReplyToComplex(what: String)
case class StartWith(server: ActorRef)

object ReturnAddressDriver extends CompletableApp(2) {
}

class Client extends Actor {
  def receive = {
  }
}

class Server extends Actor {
  def receive = {
  }
}

class Worker extends Actor {
  def receive = {
  }
}
