package id.sabril.testingPA;



public class Kategori {
	int _id;
	String _namakategori;
	String _keterangan;
	
	public Kategori(){
		this._id = (Integer) null;
		this._namakategori = null;
		this._keterangan = null;
	}
	
	public Kategori(int id, String namakategori, String keterangan){
		this._id = id;
		this._namakategori = namakategori;
		this._keterangan = keterangan;
	}
	
	public int ID() {
		return this._id;
	}
	
	public String getNamaKategori() {
		return this._namakategori;
	}
	
	public String getKeterangan() {
		return this._keterangan;
	}
}
