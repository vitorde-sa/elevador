package scriptspog;
import java.util.concurrent.Semaphore;

public class Passageiro extends Thread{
	
	int id;
  
	 int Andar_Inicial ;
	int Andar_Destino ;
	boolean chegouDestino = false;
	boolean entrou_elevador = false;
	int paciencia = 0;
	int paciencia_limite;
	Chamadas chamadas;
	Semaphore mutex;
	
	public Passageiro(int id, int Andar_Inicial, int Andar_Destino, Chamadas chamadas ,Semaphore mutex) {
		
		this.id = id;
		this.chamadas = chamadas;
		this.Andar_Inicial = Andar_Inicial;
		this.Andar_Destino = Andar_Destino;
		paciencia_limite = 4 + (int)(Math.random() * 8);
		this.mutex = mutex;
		//----------------------------------TENTAR FAZER COM O PRÓPRIO PASSAGEIRO GERANDO SEUS VALORES
	}
	
	
	public void run() {
			
		System.out.println(" O passageiro " + id + " está no andar " + Andar_Inicial + " e quer ir para o andar " + Andar_Destino);
		
	
		
		while (chegouDestino == false)
		{
			paciencia++;
			
			try {
				mutex.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
			
			// Sair Elevador
			if(chamadas.AndarAtual == Andar_Destino && entrou_elevador == true)
			{
				
				System.out.println("O passageiro " + id + 
						" saiu alegremente do elevador no andar:  " + chamadas.AndarDestino);
						//	chamadas.Liberado();
						chegouDestino = true;
								
				
			}
		
			// Chamar Elevador
						if(paciencia == paciencia_limite && chegouDestino == false && entrou_elevador == false)
						{
							//System.out.println("A paciência do passageiro " + id + " se esgotou e ele chamou o elevador ");
							
							System.out.println("O passageiro " + id + 
									" chamou o elevador para o andar: " + Andar_Inicial);
							chamadas.MudarAndarDestino(Andar_Inicial);			
							paciencia = 0;
						
						}

			
			// Entrar Elevador
			if(chamadas.AndarDestino == Andar_Inicial && chamadas.EstaDisponivel() == true)
			{
				System.out.println("O passageiro " + id + 
						" entrou no elevador no andar: " + chamadas.AndarDestino);
				entrou_elevador = true;
				chamadas.MudarAndarDestino(Andar_Destino);	
				
				
			}
			
			
			
			
			
			
			mutex.release();
			
			try {
				sleep(100 + (int)(Math.random() * 1000.0));
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}
	



	
	public void ChamarElevador() {
		
		//elevador.AndarDestino = Andar_Inicial;
		//elevador.Checagem();
		System.out.println(" O elevador foi chamado até o andar " + Andar_Inicial);
	}
	
	
	public Passageiro GetPassageiro() {
		return this;
	}
	
	public  int GetAndarInicial() {
		return Andar_Inicial;
	}
	
	public  int GetAndarDestino() {
		return Andar_Destino;
	}
}
