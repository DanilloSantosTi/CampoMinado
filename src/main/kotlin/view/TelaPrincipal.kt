package view

import viewModel.Tabuleiro
import viewModel.TabuleiroEvento
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.SwingUtilities

fun main() {
    TelaPrincipal()
}

class TelaPrincipal : JFrame() {
    private val tabuleiro = Tabuleiro(qtdLinhas = 16, qtdColunas = 16, qtdMinas = 10)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro)

    init {
        tabuleiro.onEvento(this::mostrarResultado)

        add(painelTabuleiro)
        setSize(690, 690)
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    private fun mostrarResultado(evento: TabuleiroEvento) {
        SwingUtilities.invokeLater {
            val msg = when (evento) {
                TabuleiroEvento.VITORIA -> "Você ganhou!! Parabéns!!"
                TabuleiroEvento.DERROTA -> "Você perdeu... :P"
            }

            JOptionPane.showMessageDialog(this, msg)
            tabuleiro.reinicializar()

            painelTabuleiro.repaint()
            painelTabuleiro.validate()
        }
    }
}