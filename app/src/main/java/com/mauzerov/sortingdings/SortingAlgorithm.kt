package com.mauzerov.sortingdings

interface SortingAlgorithm {
    fun getProgress(): Int

    suspend operator fun invoke(array: List<Int>): List<Int>
}