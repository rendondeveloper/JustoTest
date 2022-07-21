package com.rendonsoft.justotest.module.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rendonsoft.justotest.databinding.ItemPeopleShimmerBinding
import kotlin.random.Random

class ShimmerAdapter(
    private val listFake : List<Int> = List((1..3).random()) { Random.nextInt(0, 3) }
) :
    RecyclerView.Adapter<ShimmerAdapter.PeopleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleShimmerBinding.inflate(inflater, parent, false)
        return PeopleHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = listFake.size

    class PeopleHolder(private val binding: ItemPeopleShimmerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                picture.startShimmer()
                text1.startShimmer()
                text2.startShimmer()
                text3.startShimmer()
            }
        }
    }
}