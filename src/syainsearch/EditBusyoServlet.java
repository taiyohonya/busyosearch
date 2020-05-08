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
 * Servlet implementation class EditBusyoServlet
 */
@WebServlet("/EditBusyoServlet")
public class EditBusyoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditBusyoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);

		String editName = request.getParameter("editName");
		String originName = request.getParameter("q");

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
			String role = (String) session.getAttribute("role");
			if (role.equals("MENBER")) {
				responseData.put("result", "ng");
				System.out.println("NGパターン");
			} else {
				responseData.put("result", "ok");
				System.out.println("OKパターン");
			}
		}

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

		// 実行するSQL文
		String sql = "update BUSYO \n" + "set BUSYO_NAME='" + editName + "' \n" + "where BUSYO.BUSYO_ID='" + originName
				+ "'\n";

		System.out.println(sql);

		// エラーが発生するかもしれない処理はtry-catchで囲みます
		// この場合はDBサーバへの接続に失敗する可能性があります
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(url, user, pass);
				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();) {
			// SQLの命令文を実行し、その件数をint型のresultCountに代入します
			int resultCount = stmt.executeUpdate(sql);
		} catch (Exception e) {
			throw new RuntimeException(String.format("処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// アクセスした人に応答するためのJSONを用意する
		// JSONで出力する
		pw.append(new ObjectMapper().writeValueAsString("responseData"));
	}
}
