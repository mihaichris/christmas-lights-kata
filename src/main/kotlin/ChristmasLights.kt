import java.awt.Point

class ChristmasLights(private val size: Int) {
    private var christmasLightsGrid: HashMap<Point, Light> = HashMap(size)

    init {
        for (row in 0 until size) {
            for (col in 0 until size) {
                christmasLightsGrid[Point(row, col)] = Light(LightState.OFF)
            }
        }
    }

    fun getLightsFromRange(from: Point, to: Point): HashMap<Point, Light> {
        val gridLights: HashMap<Point, Light> = HashMap(size)
        loop@ for (y in from.y..to.y) {
            for (x in from.x..to.x) {
                gridLights[Point(x, y)] = christmasLightsGrid.getOrDefault(Point(x, y), Light(LightState.OFF))
                if (y == to.y && x == to.x) {
                    break@loop
                }
            }
        }
        return gridLights
    }

    fun litLightsCount(): Int {
        return christmasLightsGrid.filter { (_, value) -> value.isOn() }.count()
    }

    fun turnOnRangeOfLights(from: Point, to: Point) {
        loop@ for (y in from.y..to.y) {
            for (x in from.x..to.x) {
                christmasLightsGrid[Point(x, y)]?.turnOn()
                if (y == to.y && x == to.x) {
                    break@loop
                }
            }
        }
    }

    fun turnOffRangeOfLights(from: Point, to: Point) {
        loop@ for (y in from.y..to.y) {
            for (x in from.x..to.x) {
                christmasLightsGrid[Point(x, y)]?.turnOff()
                if (y == to.y && x == to.x) {
                    break@loop
                }
            }
        }
    }

    fun toggleRangeOfLights(from: Point, to: Point) {
        loop@ for (y in from.y..to.y) {
            for (x in from.x..to.x) {
                christmasLightsGrid[Point(x, y)]?.toggle()
                if (y == to.y && x == to.x) {
                    break@loop
                }
            }
        }
    }

    fun toggleAllLights() {
        toggleRangeOfLights(Point(0, 0), Point(size - 1, size - 1))
    }

    fun turnOnAllLights() {
        turnOnRangeOfLights(Point(0, 0), Point(size - 1, size - 1))
    }

    fun turnOffAllLights() {
        turnOffRangeOfLights(Point(0, 0), Point(size - 1, size - 1))
    }

    fun totalBrightness(): Int {
        var totalBrightness = 0
        christmasLightsGrid.forEach { (_, light) ->
            totalBrightness += light.brightness
        }
        return totalBrightness
    }
}