package controllers

import akka.Done
import akka.stream.scaladsl.Sink
import akka.util.ByteString
import play.api.libs.streams.Accumulator
import play.api.mvc.{BodyParser, RequestHeader, Result}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class DroppingBodyParser @Inject() (implicit ec:ExecutionContext) extends BodyParser[Done] {
  override def apply(v1: RequestHeader): Accumulator[ByteString, Either[Result, Done]] = {
    Accumulator(Sink.ignore).map(Right.apply)
  }
}
