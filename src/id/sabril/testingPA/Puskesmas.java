package id.sabril.testingPA;

public class Puskesmas {
	int _id;
	String _namapuskesmas;
	String _latitude;
	String _longnitude;
	String _alamat;
	String _telephone;
	String poli_umum;
	String poli_KIA_KB;
	String poli_TBC; 
	String rawat_inap;
	String PONED; 
	String UGD;
	String persalinan;
	String konsultasi_pojok_gizi; 
	String konsultasi_kesehatan; 
	String laboratorium;
	String poli_THT;
	
	public Puskesmas(){
		this._id = (Integer) null;
		this._namapuskesmas = null;
		this._latitude = null;
		this._longnitude = null;
		this._alamat = null;
		this._telephone = null;
	}
	
	public Puskesmas(int id, String namapuskesmas, String _latitude, String _longnitude, String _alamat, String _telephone){
		this._id = id;
		this._namapuskesmas = namapuskesmas;
		this._latitude = _latitude;
		this._longnitude = _longnitude;
		this._alamat = _alamat;
		this._telephone = _telephone;
	}
	
	public Puskesmas(int id, String namapuskesmas, String _latitude, String _longnitude, String _alamat, String _telephone, 
			String poli_umum, String poli_KIA_KB,String poli_TBC, String rawat_inap, String PONED, String UGD,
			String persalinan, String konsultasi_pojok_gizi, String konsultasi_kesehatan, String laboratorium, String poli_THT){
		this._id = id;
		this._namapuskesmas = namapuskesmas;
		this._latitude = _latitude;
		this._longnitude = _longnitude;
		this._alamat = _alamat;
		this._telephone = _telephone;
		this.poli_umum = poli_umum;
		this.poli_KIA_KB = poli_KIA_KB;
		this.poli_TBC = poli_TBC;
		this.rawat_inap = rawat_inap;
		this.PONED = PONED;
		this.UGD = UGD;
		this.persalinan = persalinan;
		this.konsultasi_pojok_gizi = konsultasi_pojok_gizi;
		this.konsultasi_kesehatan = konsultasi_kesehatan;
		this.laboratorium = laboratorium;
		this.poli_THT = poli_THT;
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
	
	public String getTelephone(){
		return this._telephone;
	}
	
	public String getPoli_umum(){
		return this.poli_umum;
	}
	
	
	public String getPoli_KIA_KB(){
		return this.poli_KIA_KB;
	}
	
	public String getPoli_TBC(){
		return this.poli_TBC;
	}
	
	public String getRawatInap(){
		return this.rawat_inap;
	}
	
	public String getPoned(){
		return this.PONED;
	}
	
	public String getUGD(){
		return this.UGD;
	}
	
	public String getPersalinan(){
		return this.persalinan;
	}
	
	public String getKonsultasi_pojok_gizi(){
		return this.konsultasi_pojok_gizi;
	}
	
	public String getKonsultasi_kesehatan(){
		return this.konsultasi_kesehatan;
	}
	
	public String getLaboratorium(){
		return this.laboratorium;
	}
	
	public String getPoli_THT(){
		return this.poli_THT;
	}
	
}
