package syainsearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
 * Servlet implementation class LoginEmpServlet
 */
@WebServlet("/LoginEmpServlet")
// http://localhost:8080/syainsearch/LoginEmpServlet
// http://localhost:8080/syainsearch/loginEmp.html
public class LoginEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginEmpServlet() {
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
		// 入力されたユーザーIDとパスワードを取得
		String userId = request.getParameter("userId");
		String password = request.getParameter("pass");

		System.out.println(userId);
		System.out.println(password);

		// アクセスした人に応答するためのJSONを用意する
		PrintWriter pw = response.getWriter();

		String sql = "select \n" + "SYAIN_INFO.SYAIN_ID \n" + ",INFORMATION.PASSWORD \n" +
				",INFORMATION.ROLE \n" +"from \n" + "SYAIN_INFO \n" + ",INFORMATION \n"
				+ "where 1=1 \n" + "and SYAIN_INFO.SYAIN_ID='"+userId+"' \n" + "and INFORMATION.PASSWORD='"+password+"' \n"
				+ "and INFORMATION.SYAIN_ID=SYAIN_INFO.SYAIN_ID \n";

		System.out.println(sql);
		// JDBCドライバの準備
		try {

			// JDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			// ドライバが設定されていない場合はエラーになります
			throw new RuntimeException(String.format("JDBCドライバのロードに失敗しました。詳細:[%s]", e.getMessage()), e);
		}

		// データベースにアクセスするために、データベースのURLとユーザ名とパスワードを指定
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String dbUser = "wt2";
		String dbPass = "wt2";

		// DBへ接続してSQLを実行
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

				// SQLの命令文を実行するための準備をおこないます
				// PreparedStatement stmt = createPreparedStatement(con, userId,
				// password);
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				// ResultSet rs1 = stmt.executeQuery();
				ResultSet rs1 = stmt.executeQuery(sql);

		) {
			// SQLの取得結果がある時（ユーザIDとパスワードが一致しているユーザーがいる）は「ok」という文字列を画面に返却
			// そうでないときは「ng」を返却
			// 返却データを作成
			Map<String, String> responseData = new HashMap<>();
			if (rs1.next()) {
				// ログインの結果
				responseData.put("result", "ok");
				// ユーザーコードとユーザー名（画面でユーザー名を表示したいため）
				responseData.put("userId", rs1.getString("SYAIN_ID"));

				// ユーザー情報をセッションに保存
				HttpSession session = request.getSession();
				session.setAttribute("login", rs1.getString("PASSWORD"));
				session.setAttribute("employeeId", rs1.getString("SYAIN_ID"));
				session.setAttribute("role", rs1.getString("ROLE"));
				System.out.println(session);

			} else {
				responseData.put("result", "ng");

			}
			pw.append(new ObjectMapper().writeValueAsString(responseData));

		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}
	}
}

