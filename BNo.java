package Binaria;

public class BNo {
	
	public Integer valor;
	public BNo pai, esq, dir;
	
	public BNo(Integer valor, BNo pai, BNo esq, BNo dir) {
		this.valor = valor;
		this.pai = pai;
		this.esq = esq;
		this.dir = dir;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public BNo getPai() {
		return pai;
	}

	public void setPai(BNo pai) {
		this.pai = pai;
	}

	public BNo getEsq() {
		return esq;
	}

	public void setEsq(BNo esq) {
		this.esq = esq;
	}

	public BNo getDir() {
		return dir;
	}

	public void setDir(BNo dir) {
		this.dir = dir;
	}
	
}

