package phil.homework.week2day2_threadpool

fun fibFinder(n: Int): Long {
    if ( n == 0 ) return 0
    if ( n == 1 ) return 1
    var f1: Long = 0
    var f2: Long = 1
    var temp: Long
    for (i in 2..(n+1)) {
        temp = f2
        f2 += f1
        f1 = temp
    }
    return f2
}