package com.stathis.sms13033.ui.choice.repository

import com.stathis.sms13033.R
import com.stathis.sms13033.ui.choice.model.MovementOption

class ChoiceRepo {

    fun getMovementChoices(): List<MovementOption> {
        return listOf(
            MovementOption("Φαρμακείο/ Γιατρός", 1, "", R.drawable.doctor),
            MovementOption("Σουπερ Μάρκετ/ Συνεργείο", 2, "",R.drawable.supermarket),
            MovementOption("Δημόσια Υπηρεσία/ Τράπεζα", 3, "",R.drawable.bank),
            MovementOption("Παροχή Βοήθειας/ Συνοδεία μαθητών στο σχολείο", 4, "",R.drawable.help),
            MovementOption("Τελετή/ Διαζευγμένοι Γονείς", 5, "",R.drawable.ceremony),
            MovementOption("Σωματική Ασκηση/ Κίνηση με κατοικίδιο", 6, "",R.drawable.runner)
        )
    }
}