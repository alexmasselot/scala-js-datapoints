package datapoints

class Square(latMin: Double, latMax: Double, lonMin: Double, lonMax: Double) {

	def countWithin(l: List[MapPoint]):Int = {
	  l.filter(pm => pm.lat>=latMin && pm.lat<=latMax && pm.lon>=lonMin && pm.lon<=lonMax).size
	}
	
	
	def toPoints:List[MapPoint] = {
	  List(new MapPoint(latMin, lonMin), new MapPoint(latMin, lonMax), new MapPoint(latMax, lonMax), new MapPoint(latMax, lonMin))
	}
}

object Square {
  def max(l: List[MapPoint]): Square = {
    new Square(l.map(_.lat).min, l.map(_.lat).max,
      l.map(_.lon).min, l.map(_.lon).max)
  }

}