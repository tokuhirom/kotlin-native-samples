import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun callbackBasedApi(callback: (Int) -> Unit) {
    println("callbackBasedApi")
    callback(42)
}

suspend fun coroutineBasedStyle() =
    suspendCoroutine { continuation ->
        callbackBasedApi { result ->
            continuation.resume(result)
        }
    }

fun main(args: Array<String>) {
    runBlocking {
        val result = coroutineBasedStyle()
        println(result)
    }
}
