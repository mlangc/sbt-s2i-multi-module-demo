package com.github.mlangc

import org.slf4j.{Logger, LoggerFactory}
import scalaz.zio.UIO

import scala.reflect.ClassTag

package object slf4zio {
  implicit class ZioLoggerOps(val logger: Logger) extends AnyVal {
    def debugIO(msg: => String): UIO[Unit] = UIO {
      if (logger.isDebugEnabled)
        logger.debug(msg)
    }

    def infoIO(msg: => String): UIO[Unit] = UIO {
      if (logger.isInfoEnabled())
        logger.info(msg)
    }
  }

  def getLogger[A](implicit classTag: ClassTag[A]): Logger =
    LoggerFactory.getLogger(classTag.runtimeClass)
}
