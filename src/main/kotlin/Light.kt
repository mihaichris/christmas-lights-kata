data class Light(var state: LightState, var brightness: Int = 0) {

    fun turnOn(by: Int = 1) {
        state = LightState.ON
        increaseBrightness(by)
    }

    private fun increaseBrightness(by: Int) {
        brightness += by
    }

    fun turnOff(by: Int = 1) {
        state = LightState.OFF
        decreaseBrightness(by)
    }

    private fun decreaseBrightness(by: Int) {
        if (brightness < by) brightness = 0 else brightness -= by
    }

    fun isOff(): Boolean {
        return state === LightState.OFF
    }

    fun isOn(): Boolean {
        return state === LightState.ON
    }

    fun toggle() {
        increaseBrightness(2)
        if (isOff()) {
            turnOn(0)
            return
        }
        if (isOn()) {
            turnOff(0)
            return
        }
    }
}
