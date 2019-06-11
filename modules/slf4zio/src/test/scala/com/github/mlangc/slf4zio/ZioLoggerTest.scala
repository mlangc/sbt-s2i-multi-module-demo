package com.github.mlangc.slf4zio

import com.github.mlangc.sbt.s2i.BaseZioTest

class ZioLoggerTest extends BaseZioTest {
  private val logger = getLogger[ZioLoggerTest]

  "Print a few simple log statements" in {
    unsafeRun {
      for {
        _ <- logger.infoIO("Hello world")
        _ <- logger.debugIO("Goodbye blue moon")
      } yield ()
    }
  }
}
