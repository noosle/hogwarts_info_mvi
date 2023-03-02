package ru.noosle.harry_potter_mvi.ui.main.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import ru.noosle.harry_potter_mvi.R
import ru.noosle.harry_potter_mvi.databinding.FragmentDetailBinding
import ru.noosle.harry_potter_mvi.ui.main.dto.Person
import ru.noosle.harry_potter_mvi.ui.main.formatHogwartsDate


class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        const val personTag = "person"
    }

    private val binding: FragmentDetailBinding by viewBinding()

    @Suppress("DEPRECATION")
    private val person by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(personTag, Person::class.java)
        } else {
            arguments?.getParcelable(personTag)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            ViewCompat.setTransitionName(image, person?.name)
            ViewCompat.setTransitionName(name, person?.name + "_name")
            ViewCompat.setTransitionName(age, person?.name + "_age")
            ViewCompat.setTransitionName(house, person?.name + "_house")
            ViewCompat.setTransitionName(patronus, person?.name + "_patronus")
            person?.let { person ->
                startEnterTransitionAfterLoadingImage(person.image!!, imageView = image)
                name.text = person.name
                if (!person.dateOfBirth.isNullOrEmpty()) {
                    age.text = "birthday: ${person.dateOfBirth.formatHogwartsDate()}"
                } else {
                    age.text = getText(R.string.birthday_unknown)
                }
                if (!person.house.isNullOrEmpty()) {
                    house.text = "house: ${person.house}"
                } else {
                    house.text = getText(R.string.house_unknown)
                }
                if (!person.patronus.isNullOrEmpty()) {
                    patronus.text = "patronus: ${person.patronus}"
                } else {
                    patronus.text = getText(R.string.patronus_unknown)
                }
                if (!person.gender.isNullOrEmpty()) {
                    gender.isVisible = true
                    genderValue.isVisible = true
                    genderValue.text = person.gender
                }
                if (!person.species.isNullOrEmpty()) {
                    species.isVisible = true
                    speciesValue.isVisible = true
                    speciesValue.text = person.species
                }
                if (!person.ancestry.isNullOrEmpty()) {
                    ancestry.isVisible = true
                    ancestryValue.isVisible = true
                    ancestryValue.text = person.ancestry
                }
                if (!person.eyeColour.isNullOrEmpty()) {
                    eyeColour.isVisible = true
                    eyeColourValue.isVisible = true
                    eyeColourValue.text = person.eyeColour
                }
                if (!person.hairColour.isNullOrEmpty()) {
                    hairColour.isVisible = true
                    hairColourValue.isVisible = true
                    hairColourValue.text = person.hairColour
                }
                if (!person.actor.isNullOrEmpty()) {
                    actor.isVisible = true
                    actorValue.isVisible = true
                    actorValue.text = person.actor
                }
                showWithDelayAnimation(500L, additionalDetailsLayout)
                if (person.wand?.wood?.isNotEmpty() == true) {
                    wandInfo.isVisible = true
                    woodValue.text = person.wand.wood
                    coreValue.text = person.wand.core
                    if (person.wand.length != null) {
                        lengthValue.text = person.wand.length.toString()
                    } else {
                        length.isVisible = false
                        lengthValue.isVisible = false
                    }
                    showWithDelayAnimation(1500L, wandInfo)
                }
            }
        }
    }

    private fun showWithDelayAnimation(delay: Long, view: View) {
        view.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(delay)
                .setListener(null)
        }
    }

    private fun startEnterTransitionAfterLoadingImage(
        imageAddress: String,
        imageView: ImageView
    ) {
        Glide.with(this)
            .load(imageAddress)
            .apply(
                RequestOptions().dontTransform()
            )
            .dontAnimate()
            .error(R.drawable.no_ava)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    (view?.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: com.bumptech.glide.request.target.Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    (view?.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                    return false
                }
            })
            .into(imageView)
    }
}