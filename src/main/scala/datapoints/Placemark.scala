package datapoints

import java.util.Date
import scala.xml.XML
import scala.xml.Elem
import scala.xml.Node
import java.text.SimpleDateFormat

class Placemark(val date: Date, val point: MapPoint) {

}

object Placemark {
  def read(): List[Placemark] = {
    read(XML.load(this.getClass().getResourceAsStream("/openpaths.kml")))
  }

  def read(root: Elem): List[Placemark] = {
    (for {
      e <- root \\ "Placemark"
      if ((e \ "ExtendedData").size == 1)
    } yield {
      val date = (e \ "ExtendedData" \ "Data" \ "value" head).text
      val coords = (e \ "Point" \ "coordinates" head).text.split(",")
      new Placemark(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date),
          new MapPoint(coords(0).toDouble, coords(1).toDouble)
      )
    }).toList
  }

  private def attr[T](el: Node, name: String): String = {
    val att = el.attribute(name)
    att match {
      case att: Some[Seq[Node]] => att.get.head.toString
      case _ => throw new NoSuchElementException("cannot find attribute '" + name + "' in node " + el)
    }

  }
  private def attr2double(el: Node, name: String): Double = {
    attr(el, name).toDouble
  }

  private def attr2string(el: Node, name: String): String = {
    attr(el, name)
  }

  def main(args: Array[String]) {
    Placemark.read()
  }
}