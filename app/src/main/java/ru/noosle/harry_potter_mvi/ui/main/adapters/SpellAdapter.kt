package ru.noosle.harry_potter_mvi.ui.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.noosle.harry_potter_mvi.databinding.ItemSpellBinding
import ru.noosle.harry_potter_mvi.ui.main.dto.Spell

class SpellAdapter : ListAdapter<Spell, SpellAdapter.MyViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Spell>() {
        override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: ItemSpellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(spell: Spell?) {
            with(binding) {
                name.text = spell?.name
                description.text = spell?.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemSpellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}