package ru.noosle.harry_potter_mvi.ui.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.noosle.harry_potter_mvi.R
import ru.noosle.harry_potter_mvi.databinding.ItemPersonBinding
import ru.noosle.harry_potter_mvi.ui.main.dto.Person
import ru.noosle.harry_potter_mvi.ui.main.formatHogwartsDate
import ru.noosle.harry_potter_mvi.ui.main.setInfoText

class PersonsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Person, PersonsAdapter.MyViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(person: Person) {
            with(binding) {
                ViewCompat.setTransitionName(image, person.name)
                ViewCompat.setTransitionName(name, person.name + "_name")
                ViewCompat.setTransitionName(age, person.name + "_age")
                ViewCompat.setTransitionName(house, person.name + "_house")
                ViewCompat.setTransitionName(patronus, person.name + "_patronus")
                Glide.with(image)
                    .load(person.image)
                    .apply(RequestOptions.circleCropTransform())
                    .error(R.drawable.no_ava)
                    .into(binding.image)
                name.text = person.name
                age.setInfoText(
                    "birthday: %s",
                    person.dateOfBirth?.formatHogwartsDate(),
                    binding.root.context.getString(R.string.birthday_unknown)
                )
                house.setInfoText(
                    "house: %s",
                    person.house,
                    binding.root.context.getString(R.string.house_unknown)
                )
                patronus.setInfoText(
                    "patronus: %s",
                    person.patronus,
                    binding.root.context.getString(R.string.patronus_unknown)
                )
                itemView.setOnClickListener {
                    onClickListener.onClick(person, image, name, age, house, patronus)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class OnClickListener(
        val clickListener: (
            person: Person, image: ImageView, name: TextView,
            age: TextView, house: TextView, patronus: TextView
        ) -> Unit
    ) {
        fun onClick(
            person: Person, image: ImageView, name: TextView, age: TextView, house: TextView,
            patronus: TextView
        ) = clickListener(person, image, name, age, house, patronus)
    }
}