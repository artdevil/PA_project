package id.sabril.testingPA;

public class Puskesmas {
	int _id;
	String _namapuskesmas;
	String _latitude;
	String _longnitude;
	String _alamat;
	
	public Puskesmas(){
		this._id = (Integer) null;
		this._namapuskesmas = null;
		this._latitude = null;
		this._longnitude = null;
		this._alamat = null;
	}
	
	public Puskesmas(int id, String namapuskesmas, String _latitude, String _longnitude, String _alamat){
		this._id = id;
		this._namapuskesmas = namapuskesmas;
		this._latitude = _latitude;
		this._longnitude = _longnitude;
		this._alamat = _alamat;
	}
	
	public int getID(){
		return this._id;
	}
	
	public String getNamaPuskesmas(){
		return this._namapuskesmas;
	}
	
	public String getLatitude(){
		return this._latitude;
	}
	
	public String getLongnitude(){
		return this._longnitude;
	}
	
	public String getAlamat(){
		return this._alamat;
	}
}
