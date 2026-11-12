package entity;

public class MovimentoConta 
{
	public int codigoConta;
	public double valorMovimento;
	public int tipoMovimento;
	public int statusMovimento;
	
	public MovimentoConta() 
	{
		this.codigoConta = 0;
		this.valorMovimento = 0.00d;
		this.tipoMovimento = 0;
		this.statusMovimento = 0;
	}
	
	public MovimentoConta(int codigoConta, double valorMovimento, int tipoMovimento, int statusMovimento) 
	{
		this.codigoConta = codigoConta;
		this.valorMovimento = valorMovimento;
		this.tipoMovimento = tipoMovimento;
		this.statusMovimento = statusMovimento;
	}

}
