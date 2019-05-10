package phil.homework.week2day2_threadpool

import kotlin.math.min
import kotlin.random.Random

class BarFiller(val callback: (Int) -> Unit) : Runnable {

    var progress: Int = 0

    private fun startFillBar(callback: (Int) -> Unit) {
        progress = 0;
        while (progress < 100) {
            Thread.sleep(Random.nextLong(25, 50))
            progress++
            callback.invoke(progress)
        }
    }

    override fun run() {
        startFillBar(callback)
    }
}