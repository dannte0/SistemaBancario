package methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import entity.ContaCorrente;
import entity.MovimentoConta;

public class Metodo 
{
	String fileName;
	Random r = new Random();
	
	//CONTAS
	public ContaCorrente[] cadastraContas(ContaCorrente[] cc) throws IOException
	{
		fileName = "ContasCC2026.txt";
		//Cadastra Contas
		for(int i = 0; i < cc.length; i++)
		{
			cc[i] = new ContaCorrente();
			cc[i].codigo = r.nextInt(20)+1;
			for(int j = 0; j < cc.length; j++)
			{
				if(cc[j]!=null && cc[i].codigo == cc[j].codigo)
				{					
					cc[i].codigo = r.nextInt(20)+1;
				}
			}		
			cc[i].nomeCliente = JOptionPane.showInputDialog("Digite o nome do Cliente");
			cc[i].saldoConta = r.nextDouble(2500)+500.00d;
			cc[i].limiteConta = r.nextDouble(2500)+500;
			cc[i].tipoConta = r.nextInt(4)+1;
		}
		//Ordena Contas por codigo
		cc = ordenaContas(cc);

		//Grava as Contas
		gravaContas(cc, fileName);
	
		return cc;
	}
	
	public void gravaContas(ContaCorrente[] cc, String fileName) throws IOException
	{
		BufferedWriter gravar = new BufferedWriter( new FileWriter( fileName) );
		for (int i = 0; i < cc.length; i++) 
		{
			System.out.println("=====================================================" ); 			
			gravar.newLine();
			System.out.printf("COD: %d\n", Integer.toString(cc[i].codigo));
			gravar.write(Integer.toString(cc[i].codigo)); 
			gravar.newLine();
			System.out.printf("Nome: %s\n", cc[i].nomeCliente);
			gravar.write(cc[i].nomeCliente); 
			gravar.newLine();
			System.out.printf("Saldo: %.2f\n", Double.toString(cc[i].saldoConta));
			gravar.write(String.format("%.2f", cc[i].saldoConta));
			gravar.newLine();
			System.out.printf("Limite: %.2f\n", Double.toString(cc[i].limiteConta));
			gravar.write(String.format("%.2f", cc[i].limiteConta));
			gravar.newLine();
			System.out.printf("Tipo de conta: %d\n", Integer.toString(cc[i].tipoConta));
			gravar.write(Integer.toString(cc[i].tipoConta)); 			
			gravar.newLine();
		}
		gravar.write("=====================================================");
		gravar.close();
	}
	
	public ContaCorrente[] ordenaContas(ContaCorrente[] cc)
	{
		ContaCorrente[] aux = new ContaCorrente[1];
		for(int i = 0; i < cc.length; i++)
		{
			for(int j = i+1; j < cc.length; j++)
			{
				if(cc[i].codigo > cc[j].codigo)
				{
					aux[0] = cc[i];
					cc[i] = cc[j];
					cc[j] = aux[0];
				}
			}
		}
		return cc;
	}
	
	//MOVIMENTOS
	public MovimentoConta[] registraMovimentos(MovimentoConta[] mc, ContaCorrente[] cc) throws IOException
	{
		
		for(int i = 0; i < mc.length; i++)
		{
			mc[i] = new MovimentoConta();
			mc[i].codigoConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo da conta"));
			mc[i].valorMovimento = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da movimentação"));
			mc[i].tipoMovimento = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de movimento da conta"));
			mc[i].statusMovimento = Integer.parseInt(JOptionPane.showInputDialog("Digite o status do movimento da conta"));
		}
		mc = ordenaMovimentos(mc);
		gravaMovimentos(mc);

		return mc;
	}
	public void gravaMovimentos(MovimentoConta[] mc) throws IOException
	{
		fileName = "MovimentosCC2026.txt";
		BufferedWriter gravar = new BufferedWriter( new FileWriter( fileName ) );
		for (int i = 0; i < mc.length; i++) 
		{
			System.out.println("====================================================="); 			
			System.out.println("COD: " + Integer.toString(mc[i].codigoConta)); 
			gravar.write(Integer.toString(mc[i].codigoConta)); 
			gravar.newLine();
			System.out.println(String.format("Valor: %.2f", mc[i].valorMovimento));
			gravar.write(String.format("%.2f", mc[i].valorMovimento));
			gravar.newLine();
			System.out.println(String.format("Tipo de Movimentação: %d", mc[i].tipoMovimento));
			gravar.write(String.format("%d", mc[i].tipoMovimento));
			gravar.newLine();
			System.out.println(String.format("Status: %d", mc[i].statusMovimento));
			gravar.write(String.format("%d", mc[i].statusMovimento));
			gravar.newLine();
		}
		System.out.println("=====================================================");
		gravar.close();
	}
	
	public MovimentoConta[] ordenaMovimentos(MovimentoConta[] mc)
	{
		MovimentoConta[] aux = new MovimentoConta[1];
		for(int i = 0; i < mc.length; i++)
		{
			for(int j = i+1; j < mc.length; j++)
			{
				if(mc[i].codigoConta > mc[j].codigoConta)
				{
					aux[0] = mc[i];
					mc[i] = mc[j];
					mc[j] = aux[0];
				}
			}
		}
		return mc;
	}
	
	
	
	//LEITURA DE ARQUIVOS
	public ContaCorrente[] gravaContas(ContaCorrente[] ca, MovimentoConta[] mc, ContaCorrente[] cc) throws FileNotFoundException, IOException 
	{
		BufferedReader lerContas = new BufferedReader( new FileReader( fileName ) );
		fileName = "MovimentosCC2026.txt";
		BufferedReader lerMovimentos = new BufferedReader( new FileReader( fileName ) );
		BufferedWriter gravarContas = new BufferedWriter( new FileWriter( fileName ) );
		
		for(int i = 0; i < ca.length; i++)
		{
			for(int j = 0; j < mc.length; j++)
			{
				if(mc[j].statusMovimento == 1)
				{
					fileName = "CCAtualizadas2026.txt";
					ca[i] = new ContaCorrente();
					ca[i].codigo = Integer.parseInt(lerContas.readLine());
					ca[i].nomeCliente = lerContas.readLine();
					if(ca[i].saldoConta >= mc[i].valorMovimento)
					{
						ca[i].saldoConta = Double.parseDouble(lerContas.readLine()) - mc[i].valorMovimento;
						ca[i].limiteConta = Double.parseDouble(lerContas.readLine());
						ca[i].tipoConta = Integer.parseInt(lerContas.readLine());
					}
					else
					{
						ca[i].limiteConta = Double.parseDouble(lerContas.readLine());
						ca[i].tipoConta = Integer.parseInt(lerContas.readLine());
						switch(ca[i].tipoConta)
						{
						case 1: //ca[i].saldoConta = 
							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							break;
						}
					}
				}
				else
				{
					switch(mc[j].tipoMovimento)
					{
						case 1: 
							break;
						case 2: 
							break;
						default: JOptionPane.showConfirmDialog(null, "Tipo de movimento inválido.");
					}
				}
			}
		}
		return ca;
	}
	
	
	//Procedimentos de consulta
	public void consultaContas(ContaCorrente[] cc)
	{
		System.out.println("=======================================================================================================================================");
		System.out.println("  COD	    		Nome		   		Saldo			  Limite		 Tipo de Conta");
		System.out.println("=======================================================================================================================================");
		for(int i = 0; i < cc.length; i++)
		{
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("	%d		%s		     		%.2f		      %.2f		   		%d\n", cc[i].codigo, cc[i].nomeCliente, cc[i].saldoConta, cc[i].limiteConta, cc[i].tipoConta);
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			
		}
	}
	
	public void consultaMovimento(MovimentoConta[] mc)
	{
		System.out.println("=======================================================================================================================================");
		System.out.println("  COD	    		Valor			  Tipo de Conta		 Status");
		System.out.println("=======================================================================================================================================");
		for(int i = 0; i < mc.length; i++)
		{
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("	%d		%.2f		     		%.2f		      %d", mc[i].codigoConta, mc[i]);
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			
		}
	}
	
	
}
