package Binaria;

public class BTree {
	
	public BNo raiz;
	
	public BTree() {
		raiz = null;
	}
	
	//INSERIR
	public void inserir(Integer valor) {
		adicionaBNo(raiz,valor);
	}
	
	public void inserirRaiz(Integer valor) { //Insere na raiz
		if (raiz != null)
			throw new IllegalStateException("A árvore já tem raiz");
		raiz = new BNo(valor, null, null, null);
	}
	
	public void inserirEsq(BNo pai, Integer valor) { //Insere a esquerda
		if (pai.esq != null)
			throw new IllegalStateException("Este no ja tem filho a esquerda");
		BNo No = new BNo(valor, pai, null, null);
	}
	
	public void inserirDir(BNo pai, Integer valor) { //Insere a direita
		if (pai.dir != null)
			throw new IllegalStateException("Este no ja tem filho a direita");
		BNo No = new BNo(valor, pai, null, null);
	}
	
	public void adicionaBNo(BNo pai, int valor) { //Insere de forma sistematica
		if (pai==null) {
			inserirRaiz(valor);
			System.out.println("Raiz: "+valor);
		}
		else if (pai != null) {
		    if (valor < pai.valor) {
		        if (pai.esq != null) {
		        	adicionaBNo(pai.esq, valor);
		        } else {
		            System.out.println(" Adicionando " + valor + " a esquerda de " + pai.valor);
		            pai.esq = new BNo(valor, pai, null, null);
		        }
		    } else if (valor > pai.valor) {
		        if (pai.dir != null) {
		        	adicionaBNo(pai.dir, valor);
		        } else {
		            System.out.println(" Adicionando " + valor + " a direita de " + pai.valor);
		            pai.dir = new BNo(valor, pai, null, null);
		        }
		    }
		}
	}
	
	//BUSCA
	public BNo pesquisando(Integer valor) {
		return gbusca(raiz,valor);
	}
	
	public BNo gbusca(BNo pai, int valor) { //retorna o no buscado
		if(pai!=null) {
			 if (valor < pai.valor) {
			     if (pai.esq.valor == valor) {
			        return pai.esq;
			     } else {
			        gbusca(pai.esq, valor);
			     }
			 } 
			 else if (valor > pai.valor) {
			     if (pai.dir.valor==valor) {
			    	 return pai.dir;
			     } else {
			         gbusca(pai.dir, valor);
			     }
			 }
		}
		return pai;
	}
	
	//EXTERNO E INTERNO
    public boolean isInternal(BNo BNo) {
        
        if(BNo != null && (BNo.getDir() != null || BNo.getEsq() != null)) {
            return true;
        }
        
        return false;
    }
    
    public boolean isExternal(BNo BNo) {
        
        if(BNo != null && (BNo.getDir() == null && BNo.getEsq() == null)) {
            return true;
        }
        
        return false;
    }

    //PROFUNDIDADE
    public Integer profunNo(int valor) { //Profundidade em um certo No
    	return profundidade(pesquisando(valor));
    }
    public Integer profundidade(BNo BNo) {  //De cima p/ baixo
        if(BNo != null) {
            return 1 + profundidade(BNo.getPai());
        }
        return -1;
    }
    
    //ALTURA
    public Integer altNo(int valor) { //Altura em um certo no
    	return altura(pesquisando(valor));
    }
    public Integer altura(BNo BNo){  //De Baixo p/ cima
        if(BNo == null){
            return -1;
        }
        else{
            int a = altura(BNo.getEsq());
            int b = altura(BNo.getDir());
            if(a > b){
                return a + 1;
            }
            else{
                return b + 1;
            }
        }
    }
    
    //REMOVENDO
    public void excluir(int valor) {
    	excluindo(raiz,valor);
    }
    /*
     Dps de excluir, ele salta para o No a direita
     e percorre ate a encontrar o ultimo No a esquerda.
     O No encontrado sera oq substituira oq foi excluido.
     */
    
    public BNo excluindo(BNo No, int valor) { 
    	if(No!=null) {                       
    		if(valor<No.valor) {
    			No.setEsq(excluindo(No.esq,valor));
   		    } else if(valor>No.valor) {
   		    	No.setDir((excluindo(No.dir,valor)));
   		    }else if(No.dir!=null && No.esq!=null) { //Pai de 2 filhos
	   		    System.out.println(" Removeu No " + No.getValor());
	            No.setValor(encontraMinimo(No.getDir()).getValor());
	            No.setDir(removeMinimo(No.getDir()));
   		    }else { //Pai com 1 filho ou nenhum
   		    	System.out.println("Removeu No " + No.getValor());  
                if(No.dir==null && No.esq!=null) {
                	No=No.esq;
                	No.setPai(No.pai.pai);
                }
                else if(No.dir!=null && No.esq==null) {
                	No=No.dir;
                	No.setPai(No.pai.pai);
                }
                else {
                	BNo w=No;
                	No=null;
                }
                	
   		    }
    		
   		}
    	return No;
    }
		

    private BNo removeMinimo(BNo node) {  
        if (node == null) {  
            System.out.println("  DEU RUIM ");  
        } else if (node.getEsq() != null) {  
            node.setEsq((removeMinimo(node.getEsq())));
            return node;  
        } else {  
            return node.getDir();  
        }  
        return null;  
    }  
  
    private BNo encontraMinimo(BNo node) {  
        if (node != null) {  
            while (node.getEsq() != null) {  
                node = node.getEsq();
            }  
        }  
        return node;  
    }

    //TIPOS DE IMPRIMIR
	public void imprimirPrefixado() {
		System.out.println("Prefixado");
		imprimirPrefixado2(raiz);
		System.out.println();
	}
	
	private void imprimirPrefixado2(BNo v) {
		if (v!=null) {
			System.out.println("No: "+v.getValor() +
					" | Profundidade: "+profundidade(v)+
					" | Altura: "+altura(v));
			imprimirPrefixado2(v.esq);
			imprimirPrefixado2(v.dir);
		}
	}
	
	public void imprimirPosfixado() {
		System.out.println("Posfixado");
		imprimirPosfixado2(raiz);
		System.out.println();
	}
	
	private void imprimirPosfixado2(BNo v) {
		if (v!=null) {
			imprimirPosfixado2(v.esq);
			imprimirPosfixado2(v.dir);
			System.out.println("No: "+v.getValor() +
					" | Profundidade: "+profundidade(v)+
					" | Altura: "+altura(v));
		}
	}
	
	public void imprimirInterfixado() {
		System.out.println("Interfixado");
		imprimirInterfixado2(raiz);
		System.out.println();
	}
	
	private void imprimirInterfixado2(BNo v) {
		if (v!=null) {
			imprimirInterfixado2(v.esq);
			System.out.println("No: "+v.getValor() +
					" | Profundidade: "+profundidade(v)+
					" | Altura: "+altura(v));
			imprimirInterfixado2(v.dir);
		}
	}
}
