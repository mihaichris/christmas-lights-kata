import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Point
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ChristmasLightsTest {

    private lateinit var christmasLights: ChristmasLights;

    @BeforeEach
    internal fun setUp() {
        christmasLights = ChristmasLights(LIGHTS_GRID_SIZE);
    }

    @Test
    fun allLightsAreOff() {
        val lights = christmasLights.getLightsFromRange(Point(0, 0), Point(LIGHTS_GRID_SIZE - 1, LIGHTS_GRID_SIZE - 1))
        assertEquals(0, christmasLights.litLightsCount())
        assertEquals(LIGHTS_GRID_SIZE * LIGHTS_GRID_SIZE, lights.size)
        for ((_, light) in lights) {
            assertLightOff(light)
        }
    }

    @Test
    fun turnOnFirstLight() {
        val firstLight = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(firstLight)
        assertLightOff(firstLight)
        firstLight.turnOn()
        assertLightOn(firstLight)
        assertEquals(1, christmasLights.litLightsCount())
    }

    @Test
    fun turnOffFirstLight() {
        val firstLight = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(firstLight)
        firstLight.turnOn()
        assertLightOn(firstLight)
        firstLight.turnOff()
        assertLightOff(firstLight)
        assertEquals(0, christmasLights.litLightsCount())
    }

    @Test
    fun toggleFirstLight() {
        val firstLight = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(firstLight)
        assertLightOff(firstLight)
        firstLight.toggle()
        assertLightOn(firstLight)
        assertEquals(1, christmasLights.litLightsCount())
        firstLight.toggle()
        assertLightOff(firstLight)
        assertEquals(0, christmasLights.litLightsCount())
    }

    @Test
    fun turnOnFirst2Lights() {
        val first2Lights = christmasLights.getLightsFromRange(Point(0, 0), Point(1, 0))
        for ((_, light) in first2Lights) {
            assertLightOff(light)
        }
        for ((_, light) in first2Lights) {
            light.turnOn()
        }
        for ((_, light) in first2Lights) {
            assertLightOn(light)
        }
    }

    @Test
    fun turnOffFirstLight2() {
        val first2Lights = christmasLights.getLightsFromRange(Point(0, 0), Point(1, 0))
        for ((_, light) in first2Lights) {
            assertLightOff(light)
        }
        for ((_, light) in first2Lights) {
            light.turnOn()
        }
        for ((_, light) in first2Lights) {
            assertLightOn(light)
        }
        for ((_, light) in first2Lights) {
            light.turnOff()
        }
        for ((_, light) in first2Lights) {
            assertLightOff(light)
        }
    }

    @Test
    fun toggleFirstLight2() {
        val first2Lights = christmasLights.getLightsFromRange(Point(0, 0), Point(1, 0))
        for ((_, light) in first2Lights) {
            assertLightOff(light)
        }
        for ((_, light) in first2Lights) {
            light.toggle()
        }
        for ((_, light) in first2Lights) {
            assertLightOn(light)
        }
    }

    @Test
    fun toggleFirst1000Lights() {
        val first1000LightsBeforeTurningOn = christmasLights.getLightsFromRange(Point(0, 0), Point(999, 0))
        for ((_, light) in first1000LightsBeforeTurningOn) {
            assertLightOff(light)
        }
        christmasLights.toggleRangeOfLights(Point(0, 0), Point(999, 0))
        for ((_, light) in first1000LightsBeforeTurningOn) {
            assertLightOn(light)
        }
    }

    @Test
    fun turnOffMiddleFourLights() {
        christmasLights.turnOnAllLights()
        christmasLights.turnOffRangeOfLights(Point(0, 0), Point(999, 0))
        christmasLights.turnOffRangeOfLights(Point(499, 499), Point(500, 500))
        assertEquals(998996, christmasLights.litLightsCount())
    }

    @Test
    fun turnOnAllLights() {
        christmasLights.turnOnAllLights()
        val lights = christmasLights.getLightsFromRange(Point(0, 0), Point(LIGHTS_GRID_SIZE - 1, LIGHTS_GRID_SIZE - 1))
        for ((_, light) in lights) {
            assertLightOn(light)
        }
    }

    @Test
    fun turnOffAllLights() {
        christmasLights.turnOffAllLights()
        val lights = christmasLights.getLightsFromRange(Point(0, 0), Point(LIGHTS_GRID_SIZE - 1, LIGHTS_GRID_SIZE - 1))
        for ((_, light) in lights) {
            assertLightOff(light)
        }
    }

    @Test
    fun toggleMultipleLights() {
        christmasLights.turnOnRangeOfLights(Point(887, 9), Point(959, 629));
        christmasLights.turnOnRangeOfLights(Point(454, 398), Point(844, 448));
        christmasLights.turnOffRangeOfLights(Point(539, 243), Point(559, 965));
        christmasLights.turnOffRangeOfLights(Point(370, 819), Point(676, 868));
        christmasLights.turnOffRangeOfLights(Point(145, 40), Point(370, 997));
        christmasLights.turnOffRangeOfLights(Point(301, 3), Point(808, 453));
        christmasLights.turnOnRangeOfLights(Point(351, 678), Point(951, 908));
        christmasLights.toggleRangeOfLights(Point(720, 196), Point(897, 994));
        christmasLights.toggleRangeOfLights(Point(831, 394), Point(904, 860));
        assertEquals(230022, christmasLights.litLightsCount())
    }

    @Test
    fun onSetupGridOfLightsTheFirstLightBrightnessIs0() {
        val light: Light? = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(light)
        assertEquals(0, light.brightness)
    }

    @Test
    fun whenTurningOnForTheFirstTimeALightTheBrightnessIs1() {
        val light: Light? = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(light)
        light.turnOn()
        assertEquals(1, light.brightness)
    }

    @Test
    fun whenTurningOnForTheFirstTimeALightAndThenTurningBackOffTheBrightnessIs0() {
        val light: Light? = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(light)
        light.turnOn()
        light.turnOff()
        assertEquals(0, light.brightness)
    }

    @Test
    fun whenToggleOnceALightTheBrightnessIs1() {
        val light: Light? = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(light)
        light.toggle()
        assertEquals(2, light.brightness)
    }

    @Test
    fun whenToggleTwiceALightTheBrightnessIs4() {
        val light: Light? = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(light)
        light.toggle()
        light.toggle()
        assertEquals(4, light.brightness)
    }

    @Test
    fun ifDecreaseMoreThen0TheBrightnessOfTheLightStays0() {
        val light: Light? = christmasLights.getLightsFromRange(Point(0, 0), Point(0, 0))[Point(0, 0)]
        assertNotNull(light)
        light.turnOff(1000)
        assertEquals(0, light.brightness)
    }

    @Test
    fun turningOnAllLightsTheBrightnessIs2000000() {
        christmasLights.toggleAllLights()
        assertEquals(2000000, christmasLights.totalBrightness())
    }

    companion object {
        private const val LIGHTS_GRID_SIZE = 1000;
    }

    private fun assertLightOn(light: Light) {
        assertTrue(light.isOn())
    }

    private fun assertLightOff(light: Light) {
        assertTrue(light.isOff())
    }
}