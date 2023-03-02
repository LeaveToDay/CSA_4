import java.util.*

data class Color(var c: Int = 0, var m: Int = 0, var y: Int = 1, var k: Int = 0)

fun cacheIndex(address: Int, blockSize: Int): Int {
    return (address / blockSize) % 32
}

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter the dimensions of the main memory (N): ")
    val n = scanner.nextInt()
    print("Enter the dimensions of the main memory (M): ")
    val m = scanner.nextInt()

    print("Enter the block size of the cache (power of 2 and <= N*M/4): ")
    val k = scanner.nextInt()

    val memory = Array(n) { Array(m) { Color() } }

    for (i in 0 until n) {
        for (j in 0 until m) {
            memory[i][j].c = 0
            memory[i][j].m = 0
            memory[i][j].y = 1
            memory[i][j].k = 0
        }
    }

    val cache = Array(k) { Color(0, 0, 0, 0) }

    var totalAccesses = 0
    var cacheMisses = 0
    var cacheHits = 0

    for (i in 0 until n) {
        for (j in 0 until m) {
            val address = i * m + j
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

    println("Algorithm 1:")
    println("Total accesses: $totalAccesses")
    println("Cache misses: $cacheMisses")
    println("Cache hits: $cacheHits")
    println("Miss rate: ${cacheMisses.toDouble() / totalAccesses}")
    println("Hit rate: ${cacheHits.toDouble() / totalAccesses}")
}