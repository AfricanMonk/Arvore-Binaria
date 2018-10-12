package Binaria;

public class Test {
	public static void main(String args[]) {
		 BTree arvore = new BTree();
	        arvore.inserir(15);
			arvore.inserir(3);
			arvore.inserir(1);
			arvore.inserir(45);
			arvore.inserir(40);
			arvore.inserir(17);

			arvore.imprimirPrefixado();
			
			arvore.excluir(40);
			
			arvore.imprimirPrefixado();
	}
}
