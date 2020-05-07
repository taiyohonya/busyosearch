package syainsearch;

import java.io.Serializable;

public class ResponseBase implements Serializable {
	/** 実行結果 */
	private String result;

	/** エラーコード **/
	private String errorCd;

	/** エラーメッセージ **/
	private String errorMessage;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorCd() {
		return errorCd;
	}

	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
