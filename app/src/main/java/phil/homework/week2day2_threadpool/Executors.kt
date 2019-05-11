package phil.homework.week2day2_threadpool

import java.util.concurrent.*

val RACECONDITIONALS = ThreadPoolExecutor(
    4,
    4,
    1000L,
    TimeUnit.MILLISECONDS,
    ArrayBlockingQueue<Runnable>(4)
)