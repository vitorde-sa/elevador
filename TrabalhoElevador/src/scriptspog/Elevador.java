package scriptspog;
import java.util.concurrent.Semaphore;

public class Elevador extends Thread  {

		boolean portaFechada = true;
		boolean Ocupado = false;
		boolean Ativo = false;	
		
		int State = 0;
		int AndarRequerido = 0;
		int AndarAtual = 0;
		int AndarDestino;
	
		Chamadas chamadas;
		Semaphore mutex;
	
		public Elevador(Chamadas chamadas,Semaphore mutex) {
			this.mutex = mutex;
			this.chamadas = chamadas;
			
			
		}
		
	
		
		public void AbrirPorta() {
			
			if(portaFechada == true)
			{
			 portaFechada = false;
			 
			}
			
			System.out.println(" A porta do elevador foi aberta!");
		}
	
		
		public void FecharPorta() {
			if(portaFechada == false)
			{
			 portaFechada = true;
		
			}
			
			
			System.out.println(" A porta do elevador foi fechada!");
		}
		
		
		public void Ocupado(Passageiro passageiro) {
			
			
			System.out.println("O passageiro " + passageiro.id + " entrou no elevador");
			
		}
		
		
		
		
	
		public void run() {
			
			System.out.println(" O elevador está operante e está no Andar " + AndarAtual);
			
			while (true)
			{
			
				try {
					mutex.acquire();
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				
				
			
				
				if(chamadas.EstaDisponivel() == false)
				{
					
					if(portaFechada == false)
					{
						FecharPorta();
					}
					
					AndarDestino = chamadas.PegarAndarDestino();
					
					if(AndarAtual == AndarDestino)
					{
						if(portaFechada == true)
						{
						//chamadas.MudarAndarAtual(AndarAtual);
							AbrirPorta();
							chamadas.Liberado();
						}
					}
					
					if(AndarAtual < AndarDestino)
					{
						AndarAtual ++;
						
					}
					
					if(AndarAtual > AndarDestino )
					{
						AndarAtual --;
						
					}
					
					System.out.println(" O elevador está no Andar: " + AndarAtual);
				}
				
				
				
				
				
				
				
				mutex.release();
				
				try {
					sleep(500);
				} catch (InterruptedException e) {
			
					e.printStackTrace();
					}
			} 
			
		}
}
	

