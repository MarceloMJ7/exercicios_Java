package desafio_4;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
 Foi conduzida uma pesquisa com "N" indivíduos sobre suas opiniões em relação à
pré-estreia de um filme. Os dados coletados incluíram sexo, idade e uma nota de 0 a
10 referente à opinião sobre o filme. As perguntas foram feitas repetidamente até que
todas as pessoas disponíveis tivessem respondido.
Com base nos resultados da pesquisa, podemos calcular e apresentar as seguintes
informações:
● A média de idade das pessoas que responderam ao questionário;
● Lista de pessoas que deram nota igual a 10;
● A quantidade de homens cujas opiniões sobre o filme foram inferiores a 5;
● A quantidade de mulheres cujas opiniões sobre o filme foram inferiores a 5;
● A quantidade de homens cujas opiniões sobre o filme foram superiores a 7;
● A quantidade de mulheres cujas opiniões sobre o filme foram superiores a 7;
● Média das notas informadas.
Essas informações podem ajudar a compreender melhor a recepção do público em
relação ao filme e a orientar decisões futuras da equipe responsável pela sua
promoção.
Obs.: não esqueça de validar se a nota está entre 0 e 10.
 */
public class Desafio_4 {

	public static void main(String[] args) {
		String continuar;
		int pessoasTotais = 0, qtdMulheres = 0, qtdHomens = 0;
		double totalIdade = 0, mediaIdade = 0, notaHomensMenor5 = 0, notaMulheresMenor5 = 0, notaMulheresMaior7 = 0, notaHomensMaior7 = 0, totalNota = 0, mediaNota = 0;
		ArrayList<String> listaNomesNota10 = new ArrayList<>();

		do {
			String nome = solicitaNome();
			pessoasTotais++;

			int idade = solicitaIdade();
			totalIdade = totalIdade + idade;

			char sexo = solicitaGenero();
			if (sexo == 'f') {
				qtdMulheres++;
			} else {
				qtdHomens++;
			}
			
			int nota = solicitaNota();
			totalNota = totalNota + nota;
			if(nota == 10) {
				listaNomesNota10.add(nome);
			}else if(nota >= 0 && nota <= 5 && sexo == 'f') {
				notaMulheresMenor5++;
			}else if(nota >= 0 && nota <= 5 && sexo == 'm') {
				notaHomensMenor5++;
			}else if(nota >= 7 && sexo == 'f') {
				notaMulheresMaior7++;
			}else if(nota >= 7 && sexo == 'm') {
				notaHomensMaior7++;
			}
			
		
			mediaIdade = pessoasTotais > 0 ? totalIdade / pessoasTotais : 0;	
			mediaNota = pessoasTotais > 0 ? totalNota / pessoasTotais : 0;
			
			continuar = JOptionPane.showInputDialog("Quer responder a pesquisa? (S / N)");
			continuar = continuar.toUpperCase();
		} while (continuar.equals("S"));
		
		String resultado = String.format(
				 "Média da Idade população: %.0f\n " + "Média nota: %.0f\n" + "Qtd homens nota menor que 5: %.0f\n " 
		+ "Qtd mulheres nota menor que 5: %.0f\n" + "Qtd mulheres nota maior que 7: %.0f\n" + "Qtd homens nota maior que 7: %.0f\n",
				 mediaIdade, mediaNota, notaHomensMenor5, notaMulheresMenor5, notaMulheresMaior7, notaHomensMaior7
				);
		
		StringBuilder mensagem = new StringBuilder(
				String.format("Lista de pessoas que deram nota 10 para o filme \n"));
		for (String nome : listaNomesNota10) { // lembrando que cada volta do loop a String "nome" vai conter um valor que será adicionado a lista nomesMenorSalario
			mensagem.append(nome).append("\n");
		}
		
		JOptionPane.showMessageDialog(null, "Informações: \n" + resultado + "\n" + mensagem);

	}

	public static int solicitaNota() {
		String cNota;
		int nota = 0;
		boolean verifica = false;

		while (!verifica) {
			cNota = JOptionPane.showInputDialog("Informe a sua nota para o filme: (0 - 10) ");

			if (isNumeric(cNota)) {
				nota = Integer.parseInt(cNota);
				if (nota <= 10 && nota >= 0) {
					verifica = true;
				} else {
					JOptionPane.showMessageDialog(null, "Informe uma nota válida entre 0 e 10");
				}
			}

		}
		return nota;
	}

	public static char solicitaGenero() {
		String cGenero;
		char genero = 0;
		boolean verifica = false;

		while (!verifica) {
			cGenero = JOptionPane.showInputDialog("Informe seu Gênero: (F / M)");
			cGenero = cGenero.toLowerCase();

			if (cGenero.equals("f") || cGenero.equals("m")) {
				genero = cGenero.charAt(0);
				verifica = true;
			} else {
				JOptionPane.showMessageDialog(null, "Informe os dados corretamente!");
			}
		}

		return genero;
	}

	public static String solicitaNome() {
		String nome = null;
		boolean verifica = false;

		while (!verifica) {
			nome = JOptionPane.showInputDialog("Informe seu nome: ");

			if (isNumeric(nome)) {
				JOptionPane.showMessageDialog(null, "Informe seu nome usando apenas letras!");
			} else {
				verifica = true;
			}
		}

		return nome;
	}

	public static boolean isNumeric(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static int solicitaIdade() {
		String lidade;
		int idade = 0;
		boolean verifica = false;

		while (!verifica) {
			lidade = JOptionPane.showInputDialog("Informe sua idade:");
			if (isNumeric(lidade)) {
				idade = Integer.parseInt(lidade);
				verifica = true;
			} else {
				JOptionPane.showMessageDialog(null, "Informe sua idade corretamente!");
			}
		}

		return idade;
	}
}
