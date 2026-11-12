package entity;

public class ContaCorrente 
{
	public int codigo;
	public String nomeCliente;
	public double saldoConta; 
	public double limiteConta;
	public int tipoConta;
	
	public ContaCorrente()
	{
		this.codigo = 0;
		this.nomeCliente = "";
		this.saldoConta = 0.00d;
		this.limiteConta = 0.00d;
		this.tipoConta = 0;
	}
	
	public ContaCorrente(int codigo, String nomeCliente, double saldoConta, double limiteConta, int tipoConta)
	{
		this.codigo = codigo;
		this.nomeCliente = nomeCliente;
		this.saldoConta = saldoConta;
		this.limiteConta = limiteConta;
		this.tipoConta = tipoConta;
	}
}
