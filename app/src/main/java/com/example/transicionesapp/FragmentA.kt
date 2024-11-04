package com.example.transicionesapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

class FragmentA : Fragment(R.layout.fragment_a) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuración de transición para navegar a FragmentB
        view.findViewById<Button>(R.id.buttonA).setOnClickListener {
            navigateToFragmentB()
        }

        // Configuración de animación interna para ImageView y TextView
        view.findViewById<Button>(R.id.buttonA).setOnClickListener {
            animateInternalViews(view)
        }

        // Configuración de transición compartida al abrir DetailActivity
        view.findViewById<ImageView>(R.id.imageViewA).setOnClickListener {
            openDetailActivityWithTransition(it)
        }
    }

    private fun navigateToFragmentB() {
        // Configurar transiciones
        exitTransition = TransitionSet().apply {
            addTransition(Slide(Gravity.START))
            addTransition(Fade())
        }

        val fragmentB = FragmentB()
        fragmentB.enterTransition = TransitionSet().apply {
            addTransition(Slide(Gravity.END))
            addTransition(Fade())
        }

        // Reemplazar el fragmento con transición
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentB) // <- Asegúrate de usar "replace"
            .addToBackStack(null)
            .commit()
    }

    private fun animateInternalViews(view: View) {
        val imageView = view.findViewById<ImageView>(R.id.imageViewA)
        val textView = view.findViewById<TextView>(R.id.textViewA)

        // Configurar ChangeBounds
        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())
            duration = 500
        }

        TransitionManager.beginDelayedTransition(view as ViewGroup, transition)

        // Cambiar tamaño y posición
        imageView.layoutParams.width = 200
        imageView.layoutParams.height = 200
        imageView.requestLayout()

        textView.translationX = 100f
        textView.translationY = 50f
    }

    private fun openDetailActivityWithTransition(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("com.example.detailapp://detail"))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(),
            view,
            "imageTransition"
        )
        startActivity(intent, options.toBundle())
    }
}
