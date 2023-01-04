package com.mauzerov.sortingdings

class BubbleSort : SortingAlgorithm {
    private var progress = 0

    // Return int from 0 to 100 representing the progress of the algorithm
    override fun getProgress(): Int {
        return progress
    }

    // Bubble sort
    override suspend fun invoke(array: List<Int>): List<Int> {
        val result = array.toMutableList()

        val iterations = result.size * (result.size - 1) / 2

        for (i in 0 until result.size) {
            for (j in 0 until result.size - i - 1) {
                if (result[j] > result[j + 1]) {
                    val temp = result[j]
                    result[j] = result[j + 1]
                    result[j + 1] = temp
                }

                progress = (i * (i + 1) / 2 + j) * 100 / iterations
            }
        }
        return result
    }
}