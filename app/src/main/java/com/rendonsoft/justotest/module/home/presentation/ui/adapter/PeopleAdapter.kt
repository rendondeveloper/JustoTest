package com.rendonsoft.justotest.module.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rendonsoft.justotest.R
import com.rendonsoft.justotest.databinding.ItemPeopleBinding
import com.rendonsoft.justotest.module.home.presentation.ui.model.UiPeople
import com.rendonsoft.justotest.utils.extension.loadUrl

class PeopleAdapter(
    private val items: List<UiPeople>
) :
    RecyclerView.Adapter<PeopleAdapter.PeopleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(inflater, parent, false)
        return PeopleHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class PeopleHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: UiPeople,
        ) {
            binding.ivProfile.loadUrl(item.urlPicture, R.drawable.usuario)
            binding.item = item
        }
    }
}