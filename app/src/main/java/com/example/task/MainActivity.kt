package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.task.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.ItemMovieBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            lifecycleScope.launch{
            val movie = RetrofitInstance.api.getMovies()
            binding.recyclerView.adapter = MovieAdapter(movie)
            LinearSnapHelper().attachToRecyclerView(binding.recyclerView)




              binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                      val center = recyclerView.width/ 2

                      for (i in 0 until recyclerView.childCount){
                          val child  = recyclerView.getChildAt(i)
                          val img = ItemMovieBinding.bind(child).movieImage

                          val childcenter = (child.left +child.right)/2
                          val distance  = abs(center - childcenter) .toFloat()
                          val scale = 0.9f + (1 - distance / recyclerView.width) * 0.2f


                          img.scaleX =scale
                          img.scaleY = scale
                          img.elevation = scale * 20
                      }
                  }
              })
        }

    }
}
