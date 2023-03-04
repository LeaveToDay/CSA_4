import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter N: ")
    val n = scanner.nextInt()
    print("Enter M: ")
    val m = scanner.nextInt()

    print("Enter cache (power of 2 and <= N*M/4): ")
    val k = scanner.nextInt()

    val memory = Array(n) { Array(m) { Color() } }

    for (j in 0 until m) {
        for (i in 0 until n) {
            memory[j][i].c = 0
            memory[j][i].m = 0
            memory[j][i].y = 1
            memory[j][i].k = 0
        }
    }

    val cache = Array(k) { Color(-1, -1, -1, -1) }

    for (i in 0 until k) {
        cache[i].c = -1
        cache[i].m = -1
        cache[i].y = -1
        cache[i].k = -1
    }

    var totalAccesses = 0
    var cacheMisses = 0
    var cacheHits = 0
    for (j in 0 until m) {
        for (i in 0 until n) {
            val address = j * n + i
            val index = cacheIndex(address, k)

            totalAccesses++

            if (cache[index].y == memory[i][j].y &&
                cache[index].c == memory[i][j].c &&
                cache[index].m == memory[i][j].m &&
                cache[index].k == memory[i][j].k
            ) {
                cacheHits++
            } else {
                cacheMisses++
                cache[index] = memory[i][j]
            }
        }
    }

    println("Algorithm 2:")
    println("Total accesses: $totalAccesses")
    println("Cache misses: $cacheMisses")
    println("Cache hits: $cacheHits")
    println("Miss rate: ${cacheMisses.toDouble() / totalAccesses}")
    println("Hit rate: ${cacheHits.toDouble() / totalAccesses}")
}
