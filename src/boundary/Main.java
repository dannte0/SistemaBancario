package boundary;

import java.io.IOException;

import javax.swing.JOptionPane;

import entity.ContaCorrente;
import entity.MovimentoConta;
import methods.Metodo;

public class Main
{
	public static void main(String[] args) throws IOException 
	{
		menu();
	}
	
	public static void menu() throws IOException
	{
		int opc = 0, consulta = 0;
		Metodo m = new Metodo();
		ContaCorrente[] contaCorrente = new ContaCorrente[5];
		ContaCorrente[] contaAtualizada = new ContaCorrente[5];
		MovimentoConta[] movimentoConta = new MovimentoConta[3];
		
		while(opc != 9)
		{
			opc = Integer.parseInt(JOptionPane.showInputDialog("Menu Principal\n1 - Cadastra contas correntes\n2 - Cadastra movimento\n3 - Gera contas atualizados\n4 - Consulta cadastros"));
			switch(opc)
			{
				case 1: contaCorrente = m.cadastraContas(contaCorrente);
					break;
				case 2: movimentoConta = m.registraMovimentos(movimentoConta, contaCorrente);
					break;
				case 3:
					break;
				case 4: consulta = Integer.parseInt(JOptionPane.showInputDialog("1 - Contas corrente"
						+ "\n2 - Movimento\n3 - Atualizado\n9 - FIM"));
						switch(consulta)
						{
							case 1 : m.consultaContas(contaCorrente);
								break;
							case 2 : 
								break;
							case 3 :
								break;
							case 4 :
								break;
							default: JOptionPane.showConfirmDialog(null, "Opção inválida");
						}
					break;
				case 9: JOptionPane.showMessageDialog(null, "Programa finalizado.");
					break;
				default: JOptionPane.showMessageDialog(null, "Opção inválida");
			}
		}

	}
}
