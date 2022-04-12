package view

import viewModel.Campo
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseClickListenner(
    private val campo: Campo,
    private val onButtonRight: (Campo) -> Unit,
    private val onButtonLeft: (Campo) -> Unit
) : MouseListener {
    override fun mousePressed(e: MouseEvent?) {
        when (e?.button) {
            MouseEvent.BUTTON1 -> onButtonLeft(campo)
            MouseEvent.BUTTON3 -> onButtonRight(campo)
        }
    }

    override fun mouseClicked(e: MouseEvent?) {}
    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}

}