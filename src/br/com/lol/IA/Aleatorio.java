package br.com.lol.IA;

import java.util.Random;

public class Aleatorio {
			private Random rnd;
			private int min;
			private int max;

			public Aleatorio(int min, int max) {
				this.rnd = new Random(max);
				this.min = min;
				this.max = max;
			}

			public int sorteio() {
				int i = rnd.nextInt(this.max);
				do{
					i = rnd.nextInt(this.max);
				}while(i < this.min);
				return i;
			}
			
			public static void main(String[] args){
				Aleatorio a = new Aleatorio(0, 5);
				System.out.println(a.sorteio());
				System.out.println(a.sorteio());
				System.out.println(a.sorteio());
				System.out.println(a.sorteio());
				
				a = new Aleatorio(10, 15);
				
				System.out.println(a.sorteio());
				System.out.println(a.sorteio());
				System.out.println(a.sorteio());
				System.out.println(a.sorteio());
			}
		}
