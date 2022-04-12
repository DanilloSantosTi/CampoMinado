package view

import viewModel.Tabuleiro
import java.awt.GridLayout
import javax.swing.JPanel

class PainelTabuleiro(tabuleiro: Tabuleiro): JPanel() {

    init {
        layout = GridLayout(tabuleiro.qtdLinhas, tabuleiro.qtdColunas)
        tabuleiro.pecorrerCampo { campo ->
            val button = BotaoCampo(campo)
            add(button)
        }
    }
}