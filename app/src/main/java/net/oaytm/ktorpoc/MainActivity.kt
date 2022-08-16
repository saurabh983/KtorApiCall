package net.oaytm.ktorpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()

    val uiScope = CoroutineScope(Dispatchers.Main+job)
    val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        uiScope.launch {
            withContext(bgDispatcher){
                getList()
            }
        }

    }

    suspend fun getList(){
        var result = KtorClient.provideHttpClient()
            .get<List<Example>>("https://jsonplaceholder.typicode.com/posts")
        Log.e("error",""+ result[0].userId)
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}