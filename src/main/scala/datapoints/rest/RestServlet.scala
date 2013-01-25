package datapoints.rest

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport
import org.slf4j.LoggerFactory
import net.liftweb.json.NoTypeHints
import net.liftweb.json.Serialization
import net.liftweb.json.Serialization.{ write => swrite }
import datapoints.Placemark

class RestServlet extends ScalatraServlet with ScalateSupport { //with DatabaseSessionSupport

  implicit val formats = Serialization.formats(NoTypeHints)
  val logger = LoggerFactory.getLogger(this.getClass());

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say<a href="hello-scalate">hello to Scalate FROM IDDDD</a>
        .
      </body>
    </html>
  }

  get("/all") {
    val apoints = Placemark.read().map({ pm => Map("date" -> pm.date, "points" -> pm.point) })

    contentType = "application/json"
    swrite(apoints)
  }

  notFound {
    println("MAPPING NOT FOUND in ID", requestPath)
  }
}

