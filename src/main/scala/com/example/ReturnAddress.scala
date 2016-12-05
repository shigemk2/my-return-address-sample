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
    case StartWith(server) =>
      println("Client: is starting...")
      server ! Request("REQ-1")
      server ! RequestComplex("REQ-20")
    case Reply(what) =>
      println("Client: received reply: " + what)
      ReturnAddressDriver.completedStep()
    case ReplyToComplex(what) =>
      println("Client: received reply to complex: " + what)
      ReturnAddressDriver.completedStep()
    case _ =>
      println("Client: received unexpected message")
  }
}

class Server extends Actor {
  val worker = context.actorOf(Props[Worker], "worker")
  def receive = {
    case request: Request =>
      println("Server: received request value: " + request.what)
      sender ! Reply("RESP-1 for " + request.what)
    case request: RequestComplex =>
      println("Server: received request vlaue: " + request.what)
      worker forward request
    case _ =>
      println("Server: received unexpected message")
  }
}

class Worker extends Actor {
  def receive = {
  }
}
