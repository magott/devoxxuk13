package devoxx;

import unfiltered.jetty
import util.Properties

object Web extends App {

  val port = Properties.envOrElse("PORT", "1337").toInt
  println("Starting on port:" + port)
  val http = jetty.Http(port)
  http.plan(new LawsPlan).plan(new CalendarPlan).run()

}

