package com.example.transicionesapp

import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionSet
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView

class FragmentB : Fragment(R.layout.fragment_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar una animación de entrada para los elementos en FragmentB
        enterTransition = TransitionSet().apply {
            addTransition(Slide(Gravity.END))
            addTransition(Fade())
        }

        // Configurar una animación de salida para cuando se navega desde FragmentB a otro fragmento
        exitTransition = TransitionSet().apply {
            addTransition(Slide(Gravity.START))
            addTransition(Fade())
        }

        // Ejemplo de configuración de elementos en FragmentB
        val textViewB = view.findViewById<TextView>(R.id.textViewB)
        textViewB.text = "Bienvenido a Fragment B"

        val imageViewB = view.findViewById<ImageView>(R.id.imageViewB)
        imageViewB.setImageResource(R.drawable.sample_image)

        // Configuración del botón de regreso al Fragmento A
        val buttonBack = view.findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}
