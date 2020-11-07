package com.stathis.sms13033.ui.choice.repository

import com.stathis.sms13033.ui.choice.model.MovementOption

class ChoiceRepo {

    fun getMovementChoices(): List<MovementOption> {
        return listOf(
            MovementOption("Φαρμακείο/ Γιατρός", 1, ""),
            MovementOption("Σουπερ Μάρκετ", 2, ""),
            MovementOption("Δημόσια Υπηρεσία/ Τράπεζα", 3, ""),
            MovementOption("Παροχή Βοήθειας", 4, ""),
            MovementOption("Τελετή/ Διαζευγμένοι Γονείς", 5, ""),
            MovementOption("Σωματική Ασκηση/ Κίνηση με κατοικίδιο", 6, "")
        )
    }
}