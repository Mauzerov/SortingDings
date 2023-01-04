package com.mauzerov.sortingdings

class QuickSort : SortingAlgorithm {
    private var progress = 0

    override fun getProgress(): Int {
        return progress
    }

    // Quick sort
    override suspend fun invoke(array: List<Int>): List<Int> {
        // correct for like 98% of cases
        val expectedIterations = (array.size * 1.3).toInt()
        var iterations = 0

        fun quickSort(array: List<Int>): List<Int> {
            iterations++.also {
                // this may exceed 100% but it's fine
                progress = (it * 100 / expectedIterations).coerceAtMost(100)
            }
            if (array.size < 2) {
                return array
            }
            val pivot = array[array.size / 2]
            val less = array.filter { it < pivot }
            val equal = array.filter { it == pivot }
            val greater = array.filter { it > pivot }

            return quickSort(less) + equal + quickSort(greater)
        }
        progress = 100
        return quickSort(array)
    }
}