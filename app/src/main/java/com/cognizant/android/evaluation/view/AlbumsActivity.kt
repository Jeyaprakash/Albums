package com.cognizant.android.evaluation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cognizant.android.evaluation.databinding.ActivityAlbumBinding
import com.cognizant.android.evaluation.models.Albums
import com.cognizant.android.evaluation.repository.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsActivity:AppCompatActivity() {
    private val viewModel: AlbumsViewModel by viewModels()
    private lateinit var binding: ActivityAlbumBinding
    private  var adapter: AlbumsAdaptor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.albumsList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.albumsList.setLayoutManager(layoutManager)

        adapter = AlbumsAdaptor(arrayListOf())
        binding.albumsList.adapter = adapter


        viewModel.albums.observe(this,  { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.albumsList.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    result.data?.let { albums -> setAlbums(albums as List<Albums>) }
                }
                Result.Status.LOADING ->
                {
                    binding.progressBar.visibility = View.VISIBLE
                binding.albumsList.visibility = View.GONE
                }
                Result.Status.ERROR -> {
                    binding.albumsList.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    private fun setAlbums(users: List<Albums>) {
        adapter?.apply {
            addAlbums(users)
            notifyDataSetChanged()
        }
    }

}