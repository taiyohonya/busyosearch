package syainsearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BusyoRemoveServlet
 */
@WebServlet("/BusyoRemoveServlet")
public class BusyoRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BusyoRemoveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String removeBusyoId = request.getParameter("busyoRemove");
		System.out.println(removeBusyoId);

		response.setContentType("text/html;charset=UTF-8");

		// JDBCドライバの準備
		try {

			// JDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			// ドライバが設定されていない場合はエラーになります
			throw new RuntimeException(String.format("JDBCドライバのロードに失敗しました。詳細:[%s]", e.getMessage()), e);
		}

		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "wt2";
		String pass = "wt2";

		// // 実行するSQL文
		String sql = "delete from BUSYO where 1=1 and BUSYO.BUSYO_ID='" + removeBusyoId + "'";

		System.out.println(sql);

		// セッションにユーザー情報問い合わせ
		HttpSession session = request.getSession();
		// セッションから値取得
		String status = (String) session.getAttribute("login");
		System.out.println(status);

		PrintWriter pw = response.getWriter();
		Map<String, Object> responseData = new HashMap<>();

		if (status == null) {// まだログインしてない
			responseData.put("result", "ng");
			System.out.println("NGパターン");
		} else {// ログイン状態
			// メンバー、マネージャー振り分け
			String role = (String) session.getAttribute("role");
			if (role.equals("MENBER")) {
				responseData.put("result", "ng");
				System.out.println("NGパターン");
			} else {
				responseData.put("result", "ok");
				System.out.println("OKパターン");
			}
		}

		// エラーが発生するかもしれない処理はtry-catchで囲みます
		// この場合はDBサーバへの接続に失敗する可能性があります
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(url, user, pass);

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();) {
			// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
			int resultCount = stmt.executeUpdate(sql);
			// SQL実行後の処理内容

		} catch (Exception e) {
			throw new RuntimeException(String.format("処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// アクセスした人に応答するためのJSONを用意する
		// JSONで出力する
		pw.append(new ObjectMapper().writeValueAsString("responseData"));
		// pw.append(new ObjectMapper().writeValueAsString(busyoId));
	}
}
