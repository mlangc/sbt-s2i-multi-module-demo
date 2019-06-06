package com.github.mlangc.sbt.s2i.demo.echo

import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.{HttpApp, HttpRoutes, Response, Status}
import scalaz.zio.interop.catz._
import scalaz.zio.interop.catz.implicits._
import scalaz.zio.{Task, ZIO}

object EchoServer extends CatsApp {
  override def run(args: List[String]): ZIO[EchoServer.Environment, Nothing, Int] = {
    val dsl: Http4sDsl[Task] = Http4sDsl[Task]
    import dsl._

    def endpoints: HttpApp[Task] = {
      val service = HttpRoutes.of[Task] {
        case GET -> Root / "echo" / echo => Ok(echo)
        case GET -> Root / "alive" => Task(Response(Status.Ok))
      }

      Router("/" -> service).orNotFound
    }

    for {
      _ <- BlazeServerBuilder[Task]
        .bindHttp(9000)
        .withHttpApp(endpoints)
        .serve
        .compile
        .drain.orDie
    } yield 0
  }
}
