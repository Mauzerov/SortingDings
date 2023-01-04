package com.mauzerov.sortingdings

class InsertSort : SortingAlgorithm {
    private var progress = 0

    override fun getProgress(): Int {
        return progress
    }

    override suspend fun invoke(array: List<Int>): List<Int> {
        val result = array.toMutableList()
        for (i in 1 until result.size) {
            val key = result[i]
            var j = i - 1

            while (j >= 0 && result[j] > key) {
                result[j + 1] = result[j]
                j -= 1
            }
            result[j + 1] = key

            progress = (i * 100 / result.size)
        }
        return result
    }
}