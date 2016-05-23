package com.wanhe;


public class EasemobConfig {
	
	public static EasemobConfig instance;
	
	public String CLIENT_ID = "YXA6Kj5asJ_YEeWIMmOCIr8nJg";
	public String CLIENT_SECRET = "YXA6YefF-N6XnociYC_E9LGZ9_5I03A";
	public String APP_KEY = "jnhwzhsq/ganide123intelligentcommunity";
	public String _SERVICE = "https://a1.easemob.com/" + APP_KEY + "/";
	public String TOKEN_ID = "";
	
	public static EasemobConfig getInstance(){
		if(instance == null){
			instance = new EasemobConfig();
		}
		return instance;
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getCLIENT_SECRET() {
		return CLIENT_SECRET;
	}

	public void setCLIENT_SECRET(String cLIENT_SECRET) {
		CLIENT_SECRET = cLIENT_SECRET;
	}

	public String getTOKEN_ID() {
		return TOKEN_ID;
	}

	public void setTOKEN_ID(String tOKEN_ID) {
		TOKEN_ID = tOKEN_ID;
	}

	public String getAPP_KEY() {
		return APP_KEY;
	}

	public void setAPP_KEY(String aPP_KEY) {
		APP_KEY = aPP_KEY;
	}

	public String get_SERVICE() {
		return _SERVICE;
	}

	public void set_SERVICE(String _SERVICE) {
		this._SERVICE = _SERVICE;
	}
}