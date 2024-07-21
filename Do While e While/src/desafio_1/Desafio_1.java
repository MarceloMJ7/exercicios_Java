package desafio_1;



import javax.swing.JOptionPane;

/*
 Criar um programa que solicita ao usuário dois números e, em seguida, pergunta qual
é o resultado da multiplicação desses dois números. Enquanto o usuário não acertar
a resposta, o programa deve informar que ele está errado e pedir para tentar
novamente. Quando o usuário acertar, o programa deve informar que o número está
correto e mostrar quantas vezes o usuário tentou até acertar.
 */

public class Desafio_1 {

	public static void main(String[] args) {
		int multiplica;
		boolean continuar = true;
		String resultado;
		int resInt, cont = 1;
		int[] user = userNumeros();
		String numerosSemChaves = arrayToString(user);

		do {
			multiplica = user[0] * user[1];
			resultado = JOptionPane.showInputDialog(
					"Qual é o resultado da multiplicação entre os números " + user[0] + " * " + user[1] + ":");
			resInt = Integer.parseInt(resultado);
			if (resInt != multiplica) {
				cont = cont + 1;
			} else {
				JOptionPane.showMessageDialog(null,
						"Sua resposta está correta, você precisou de " + cont + "º tentativas");
				continuar = false;
			}

		} while (continuar);
	}

	public static int[] userNumeros() {
		String num;
		int numInteiro;
		int[] numerosUser = new int[2];

		for (int pergunta = 0; pergunta < 2; pergunta++) {
			num = JOptionPane.showInputDialog(null, "Digite o número " + (pergunta + 1) + "º");
			numerosUser[pergunta] = Integer.parseInt(num);

		}

		return numerosUser;

	}

	public static String arrayToString(int[] array) { // metodo para tirar as chaves da exibição do array
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1) {
				sb.append(" "); // ou ", " ou qualquer outro delimitador que preferir
			}
		}
		return sb.toString();
	}
}
