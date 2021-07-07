package com.cognizant.android.evaluation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cognizant.android.evaluation.databinding.AlbumListRowBinding
import com.cognizant.android.evaluation.models.Albums


class AlbumsAdaptor(private val albums: ArrayList<Albums>): RecyclerView.Adapter<AlbumsAdaptor.DataViewHolder>() {
    private lateinit var binding: AlbumListRowBinding

   inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(album: Albums) {
            itemView.apply {
                binding.listRowText.text = album.title
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding=AlbumListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val view =binding.root
        return DataViewHolder(view)

    }

    override fun getItemCount(): Int = albums.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    fun addAlbums(users: List<Albums>) {
        this.albums.apply {
            clear()
            addAll(users)
        }

    }
}