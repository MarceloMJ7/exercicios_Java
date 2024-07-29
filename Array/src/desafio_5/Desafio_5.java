package desafio_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;

/*
 Um professor deseja ter uma aplicação que calcule a média ponderada de seus
alunos durante o bimestre. O bimestre é composto por várias notas com pesos que
podem variar.
Escreva um programa que permita ao usuário calcular a média ponderada de um
número variável de alunos, com um número variável de avaliações e pesos definidos
pelo usuário. O programa deve solicitar ao usuário a quantidade de avaliações e os
pesos correspondentes para cada uma delas. Em seguida, o programa deve solicitar
as notas de cada avaliação para um aluno. Permita o usuário cadastrar quantos
alunos forem necessários.
Após obter esses dados, o programa deve calcular e imprimir a média ponderada de
cada aluno, informando se o aluno passou ou não, considerando a média 6.0 como
nota de aprovação. Além disso, o programa deve calcular e imprimir a média da
turma e a quantidade de alunos que ficaram acima da média. Para cada aluno, o
programa deve também ordenar as notas em ordem crescente e imprimir as notas
ordenadas, além de identificar a maior e a menor notas.
Exemplo de saída
Quantidade de Avaliações: 3
Pesos: 2, 3, 5
--------------------------------------------
Notas do Aluno 1: 6, 7, 8
Maior Nota: 8
Menor Nota: 6
Média Ponderada Aluno 1: 7.1
Situação: Passou
--------------------------------------------
Notas do Aluno 2: 9, 8, 7
Notas Ordenadas Aluno 2: 7, 8, 9
Maior Nota: 9
Menor Nota: 7
Média Ponderada Aluno 2: 7.7
Situação: Passou
--------------------------------------------
Média da Turma: 7.4
Alunos Acima da Média: 2
 */
public class Desafio_5 {

	public static void main(String[] args) {

		int qtdAlunos = qtdAlunos();
		int qtdAvaliacoes = qtdAvaliacoes();
		ArrayList<Double> pesos = pesoAvaliacoes(qtdAvaliacoes);

		ArrayList<Double> mediasAlunos = new ArrayList<>();
		double somaTotalNotas = 0;
		int alunosAcimaDaMedia = 0;
		
		JOptionPane.showMessageDialog(null, "Programa vai calcular média das notas dos alunos");

		for (int i = 0; i < qtdAlunos; i++) { //Loop vai rodar enquanto ainda existir alunos;
			 JOptionPane.showMessageDialog(null,
			"------------------------------- \n" + "Quantidade de avaliações: " +
					qtdAvaliacoes + "\n"
			 + "Pesos de cada Avaliação: " + pesos + "\n" +
			"------------------------------- \n");
			ArrayList<Double> notaAlunos = notaProvas(qtdAvaliacoes); // Lista de Array que vai receber a nota dos Alunos
			double somaNotas = somarNotas(notaAlunos, pesos); 
			double somaPesos = somarPesos(pesos);
			double mediaPonderada = mediaNotasPonderada(somaNotas, somaPesos);
			mediasAlunos.add(mediaPonderada); //em cada loop será acrescentado o valor da media ponderada na media Alunos

			String situacao = mediaPonderada >= 6.0 ? "Passou" : "Não Passou"; // vamos verificar a situação do aluno, se media for maior que 6 ele passa, else exibe que não passou

			String mensagem = "--------------------------------------------\n" + "Notas do Aluno " + (i + 1) + ": "
					+ notaAlunos + "\n" + "Notas Ordenadas Aluno " + (i + 1) + ": " + notaAlunos + "\n" + "Maior Nota: "
					+ maiorNotaALuno(notaAlunos) + "\n" + "Menor Nota: " + menorNota(notaAlunos) + "\n"
					+ "Média Ponderada Aluno " + (i + 1) + ": " + mediaPonderada + "\n" + "Situação: " + situacao;

			JOptionPane.showMessageDialog(null, mensagem);
		}

		double mediaTurma = calcularMediaTurma(mediasAlunos); // Fora do loop calculamos a media da turma;
		for (double media : mediasAlunos) { // como é apenas uma comparação simples, vou usar o for-each loop, aqui vamos ler a media dos alunos
			if (media > mediaTurma) { // media for maior que Media turma
				alunosAcimaDaMedia++; // acrescenta +1
			}
		}

		JOptionPane.showMessageDialog(null, "-------------------------------------------- \n" + "Média da Turma: "
				+ mediaTurma + "\n" + "Alunos Acima da Média: " + alunosAcimaDaMedia);

	}

	public static double calcularMediaTurma(ArrayList<Double> mediasAlunos) {
		double soma = 0;
		for (double media : mediasAlunos) {
			soma += media;
		}
		return soma / mediasAlunos.size();
	}

	public static Double menorNota(ArrayList<Double> notaAlunos) {

		Collections.sort(notaAlunos);

		return notaAlunos.get(0);
	}

	public static double maiorNotaALuno(ArrayList<Double> notaAlunos) {
		Collections.sort(notaAlunos);

		return notaAlunos.get(notaAlunos.size() - 1);
	}

	public static int qtdAlunos() {
		String xAlunos;
		int alunos = 0;
		boolean verifica = false;

		while (!verifica) {
			try {
				xAlunos = JOptionPane.showInputDialog("Informe a quantidade de alunos da classe");
				alunos = Integer.parseInt(xAlunos);
				verifica = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Digite um número válido");
			}
		}
		return alunos;
	}

	public static int qtdAvaliacoes() { // Função que pergunta a quantidade de avaliações que serão aplicadas
		String cQtdAvaliacao;
		int qtdAvaliacao = 0;
		boolean verifica = false;

		while (!verifica) { // Vou usar um loop para verificar se o user está digitando números
			try { // Try vai tentar converter a String em int
				cQtdAvaliacao = JOptionPane.showInputDialog("Informe a quantidade de avaliações que serão aplicadas:");
				qtdAvaliacao = Integer.parseInt(cQtdAvaliacao);
				verifica = true; // se a pessoa digitar um número a conversão será realizada e assim sairemos do
									// loop
			} catch (NumberFormatException e) { // Caso o user digite uma letra, vai aparecer uma mensagem de erro
				JOptionPane.showMessageDialog(null, "Digite um número válido");
			}
		}

		return qtdAvaliacao;
	}

	public static ArrayList<Double> notaProvas(int avaliacao) { // Nota das provas de acordo com a quantidade de
																// avaliações;
		String xNota;
		double nota;
		ArrayList<Double> notaProva = new ArrayList<>();

		for (int i = 0; i < avaliacao; i++) {
			boolean verifica = false;

			while (!verifica) {
				try {
					xNota = JOptionPane.showInputDialog("Informe a nota da prova " + (i + 1));
					nota = Double.parseDouble(xNota);
					notaProva.add(nota);
					verifica = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Digite números válidos");
				}
			}

		}

		return notaProva;
	}

	public static ArrayList<Double> pesoAvaliacoes(int avaliacao) { // Como não sei qual vai ser o número de provas que
																	// o user vai aplicar, vamos usar um array list
		String xPeso;
		double peso;
		// Criação de uma lista para armazenar valores double
		ArrayList<Double> pesosProvas = new ArrayList<>();

		for (int i = 0; i < avaliacao; i++) {
			boolean verifica = false;

			while (!verifica) {
				try {
					xPeso = JOptionPane.showInputDialog("Informe o peso da prova " + (i + 1));
					peso = Double.parseDouble(xPeso);
					pesosProvas.add(peso);
					verifica = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Digite números válidos");
				}

			}

		}
		return pesosProvas;
	}

	public static double somarNotas(ArrayList<Double> notas, ArrayList<Double> pesos) {
		double soma = 0;
		for (int i = 0; i < notas.size(); i++) {
			soma += notas.get(i) * pesos.get(i);
		}
		return soma;
	}

	public static double somarPesos(ArrayList<Double> pesos) {
		double soma = 0;
		for (double peso : pesos) {
			soma += peso;
		}
		return soma;
	}

	public static double mediaNotasPonderada(double somaNotas, double somaPesos) {
		double resultado = somaNotas / somaPesos;

		return resultado;

	}
}
