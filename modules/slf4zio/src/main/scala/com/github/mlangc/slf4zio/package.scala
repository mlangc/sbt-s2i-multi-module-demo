package com.github.mlangc

import org.slf4j.Logger
import scalaz.zio.UIO

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
}
