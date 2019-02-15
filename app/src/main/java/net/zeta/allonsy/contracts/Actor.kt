package net.zeta.allonsy.contracts

import net.zeta.allonsy.utils.Messages

interface Actor {
    fun message(actor: Actor?,message: Messages)
}