package scriptspog;

public class Chamadas {

	int AndarDestino;
	int AndarAtual;
	boolean pedido = false;
	boolean Elevador_Disponivel;
	
	
	
	public Chamadas() {
		
		AndarDestino = 0;
		Elevador_Disponivel = true;
	}
	
	public void Liberado() {
		AndarAtual = AndarDestino;
		
		Elevador_Disponivel = true;

	}
	
	public void MudarAndarDestino(int a) {
	

		if(Elevador_Disponivel == true)
		{
		 AndarDestino = a;
		}
		Elevador_Disponivel = false;
	}
	

	
	public void MudarAndarAtual(int b) {
		
		AndarDestino = b;
		
		Elevador_Disponivel = false;
	}
	
	public int PegarAndarDestino() {
		
		Elevador_Disponivel = false;
		
		return AndarDestino;
	}
	
	public int PegarAndarAtual() {
		
		Elevador_Disponivel = false;
		
		return AndarAtual;
	}

	public boolean EstaDisponivel() {
		
		return Elevador_Disponivel;
	}
	
	
}
