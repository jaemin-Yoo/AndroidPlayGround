package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Movie
import com.example.myapplication.databinding.ListMovieItemBinding

class MovieListAdapter internal constructor(context: Context):
    ListAdapter<Movie, MovieListAdapter.ViewHolder>(
        MovieDiffUtil
    ) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class ViewHolder(private val binding: ListMovieItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings() //데이터가 수정되면 즉각 바인딩
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListMovieItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    companion object MovieDiffUtil: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            //각 아이템들의 고유한 값을 비교해야 한다.
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}