package enteties;

public class No<T> {
	
	int chave;
	T dado;
	int alturaNo;
	No<T> esq, dir;
	
	public No(int chave, T dado) {
		this.chave = chave;
		this.dado = dado;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	public int getAlturaNo() {
		return alturaNo;
	}

	public void setAlturaNo(int alturaNo) {
		this.alturaNo = alturaNo;
	}

	
}
