package t

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.isDistantFuture
import kotlinx.datetime.isDistantPast
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class InstantTest {


    @Test
    fun testBefore() = runTest {
        val start = Clock.System.now()
        val end = withContext(Dispatchers.Default){
            delay(1.seconds)
            Clock.System.now()
        }

        assertTrue(start.epochSeconds < end.epochSeconds)
    }
}