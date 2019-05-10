package phil.homework.week2day2_threadpool

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val barFiller1 = BarFiller { pbTask1.progress = it }
        val barFiller2 = BarFiller { pbTask2.progress = it }
        val barFiller3 = BarFiller { pbTask3.progress = it }
        val barFiller4 = BarFiller { pbTask4.progress = it }

        btnTaskScheduler.setOnClickListener {
            Thread(barFiller1).start()
            Thread(barFiller2).start()
            Thread(barFiller3).start()
            Thread(barFiller4).start()
        }

        btnNumberChooser.setOnClickListener {
            NumberChooser {
                tvNumber.text = it.toString()
            }.execute()
        }

        val bgDispatcher: CoroutineDispatcher = Dispatchers.IO
        val bgScope = CoroutineScope(bgDispatcher)



        fun showFib(result: Long) {
            pbFibonacci.visibility = View.INVISIBLE
            tvFibonacci.text = result.toString()
        }

        suspend fun calculateFibonacci(int: Int){
            val fib = fibFinder(int)
            showFib(fib)
        }

        btnFibonacci.setOnClickListener {
            tvFibonacci.text=""
            pbFibonacci.visibility = View.VISIBLE
            bgScope.launch {
                calculateFibonacci(etFibonacci.text.toString().toInt())
            }
        }
    }
}
