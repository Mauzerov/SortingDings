package com.mauzerov.sortingdings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.mauzerov.sortingdings.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var array = listOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateArrayButton.setOnClickListener {
            binding.arraySizeEditText.text.toString().toIntOrNull()?.let { size ->
                this.array = List(size) { Random.nextInt() }
                binding.arrayTextView.text = array.joinToString()
            }?: run {
                Toast.makeText(this, "Nie Poprawana Wielkość", Toast.LENGTH_SHORT).show()
            }
        }

        binding.sortButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val algorithm: SortingAlgorithm = QuickSort()
                val progressJob = launch(Dispatchers.IO) {
                    while (this.isActive) {
                        runOnUiThread {
                            binding.progressBar.progress = algorithm.getProgress()
                        }
                    }
                }
                val result = algorithm(array)
                runOnUiThread {
                    binding.arrayTextView.text = result.joinToString()
                    binding.progressBar.progress = algorithm.getProgress()
                }
                progressJob.cancel()
            }
        }
    }
}