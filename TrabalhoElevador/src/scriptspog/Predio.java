package scriptspog;
import java.util.concurrent.Semaphore;


public class Predio {
	 
	
	static int Num_Andares = 5;
	
	public static void main(String[] args) {
	     
		 int Num_Passageiros = 3;		 			
		 Semaphore mutex = new Semaphore(1);
		 Chamadas chamadas = new Chamadas();
		 Elevador elevador = new Elevador(chamadas , mutex);
		
		elevador.start();
	    
	
		System.out.println(" Neste sistema os passageiros possuem um sistema de paciência para chamar o elevador, a paciência de cada um é aleatória, então um pode chamar com mais frequência que outros!");
		
		for (int i =0; i <  Num_Passageiros; i++) {
			
			
			int rng1 = (int) (Math.random() * Num_Andares );
			int rng2 =  (int) (Math.random() * Num_Andares );
			
			if(rng1 == rng2)
			{
				rng2 = (int) (Math.random() * Num_Andares );
			}
			
			Passageiro passageiro = new Passageiro(i ,rng1, rng2, chamadas, mutex);
		
			passageiro.start();
			
			
		}
		
	
			
	}	
		
	

}
	
	
