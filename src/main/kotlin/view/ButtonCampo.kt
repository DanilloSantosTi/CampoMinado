package view

import viewModel.Campo
import viewModel.CampoEvento
import java.awt.Color
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.SwingUtilities

private val COR_BG_NORMAL = Color(184, 184, 184)
private val COR_BG_MARCACAO = Color(8, 179, 247)
private val COR_BG_EXPLOSAO = Color(189, 66, 68)
private val COR_BG_VERDE = Color(0, 100, 0)

class BotaoCampo(private val campo: Campo) : JButton() {
    init {
        font = font.deriveFont(Font.BOLD)
        background = COR_BG_NORMAL
        isOpaque = true
        border = BorderFactory.createBevelBorder(0)
        addMouseListener(MouseClickListenner(campo, { it.abrir() }, { it.alterarMarcacao() }))

        campo.onEvento(this::aplicarEstilo)
    }

    fun aplicarEstilo(campo: Campo, campoEvento: CampoEvento) {
        when (campoEvento) {
            CampoEvento.EXPLOSAO -> aplicarEstiloExplosao()
            CampoEvento.ABERTURA -> aplicarEstiloAbertura()
            CampoEvento.MARCACAO -> aplicarEstiloMarcacao()
            else -> aplicarEstiloPadrao()
        }

        SwingUtilities.invokeLater {
            repaint()
            validate()
        }
    }

    private fun aplicarEstiloExplosao() {
        background = COR_BG_EXPLOSAO
        text = "X"
    }

    private fun aplicarEstiloAbertura() {
        background = COR_BG_NORMAL
        border = BorderFactory.createLineBorder(Color.GRAY)

        foreground = when (campo.qtdVizinhosMinados) {
            1 -> COR_BG_VERDE
            2 -> Color.BLUE
            3 -> Color.YELLOW
            4,5,6 -> Color.RED
            else -> Color.PINK
        }

        text = if(campo.qtdVizinhosMinados > 0) campo.qtdVizinhosMinados.toString() else ""
    }

    private fun aplicarEstiloMarcacao() {
        background = COR_BG_MARCACAO
        foreground = Color.BLACK
        text = "M"
    }

    private fun aplicarEstiloPadrao() {
        background = COR_BG_NORMAL
        border = BorderFactory.createBevelBorder(0)
        text = ""
    }


}
