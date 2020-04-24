package syainsearch;

import java.io.Serializable;

public class Busyo implements Serializable {
	/** 部署ID */
	private String busyoId;

	/** 部署名 */
	private String busyoName;

	public String getBusyoId() {
		return busyoId;
	}

	public void setBusyoId(String busyoId) {
		this.busyoId = busyoId;
	}

	public String getBusyoName() {
		return busyoName;
	}

	public void setBusyoName(String busyoName) {
		this.busyoName = busyoName;
	}


}
