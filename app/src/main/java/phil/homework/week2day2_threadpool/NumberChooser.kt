package phil.homework.week2day2_threadpool

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlin.random.Random

class NumberChooser(val callback: (Int) -> Unit): AsyncTask<Int, Int, String>() {

    val callbackHandler = Handler(Looper.getMainLooper())

    override fun doInBackground(vararg params: Int?): String {
        chooseANumber()

        return "Complete"
    }

    private fun postMessage(int: Int){
        callbackHandler.post { callback.invoke(int) }
    }

    private fun chooseANumber(){
        var newNumber = Random.nextInt(7)
        var lastNumber = newNumber
        postMessage(newNumber)
        while(newNumber != 8){
            Thread.sleep(250)
            while(newNumber == lastNumber) newNumber = Random.nextInt(9)
            lastNumber = newNumber
            postMessage(newNumber)
        }
    }

}