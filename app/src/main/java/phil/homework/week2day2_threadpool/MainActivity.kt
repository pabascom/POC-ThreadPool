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
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val barFillers = listOf(
            BarFiller { pbTask1.progress = it },
            BarFiller { pbTask2.progress = it },
            BarFiller { pbTask3.progress = it },
            BarFiller { pbTask4.progress = it }
        )

        btnTaskScheduler.setOnClickListener {
            barFillers.map { RACECONDITIONALS.submit(it) }
        }

        btnNumberChooser.setOnClickListener {
            NumberChooser {
                tvNumber.text = it.toString()
            }.execute()
        }

        val uiScope = CoroutineScope(Dispatchers.Main)
        var fib = 0L
        fun launchFibFinder(int: Int) {
            uiScope.launch {
                tvFibonacci.text = ""
                pbFibonacci.visibility = View.VISIBLE
                withContext(Dispatchers.IO) {
                    fib = fibFinder(int)
                }
                pbFibonacci.visibility = View.INVISIBLE
                tvFibonacci.text = fib.toString()
            }
        }

        btnFibonacci.setOnClickListener {
            launchFibFinder(etFibonacci.text.toString().toInt())
        }
    }
}
