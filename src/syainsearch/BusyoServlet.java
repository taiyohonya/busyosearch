package syainsearch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class BusyoServlet
 */
@WebServlet("/BusyoServlet")
public class BusyoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BusyoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// アクセス元のHTMLでitemNameに設定された値を取得して、String型の変数itemNameに代入
		// String busyoId = request.getParameter("busyoId");
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
		String sql = "select \n" + "BUSYO.BUSYO_NAME \n" + "from \n" + " BUSYO \n";
		// 商品情報リスト（Item型のリスト）
		List<Busyo> busyoList = new ArrayList<>();
		// エラーが発生するかもしれない処理はtry-catchで囲みます
		// この場合はDBサーバへの接続に失敗する可能性があります
		try (
				// データベースへ接続します
				Connection con = DriverManager.getConnection(url, user, pass);

				// SQLの命令文を実行するための準備をおこないます
				Statement stmt = con.createStatement();

				// SQLの命令文を実行し、その結果をResultSet型のrsに代入します
				ResultSet rs1 = stmt.executeQuery(sql);) {
			// SQL実行後の処理内容

			// SQL実行結果を商品リストに追加していく。
			while (rs1.next()) {
				// 一つ分の商品情報を入れるためのItemインスタンスを生成
				Busyo busyo = new Busyo();
				// SQLの取得結果をインスタンスに代入
				busyo.setBusyoName(rs1.getString("BUSYO_NAME"));
				System.out.println(rs1.getString("BUSYO_NAME"));
				// 値を格納した商品インスタンスをリストに追加
				busyoList.add(busyo);
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
		}

		// アクセスした人に応答するためのJSONを用意する
		PrintWriter pw = response.getWriter();
		// JSONで出力する
		pw.append(new ObjectMapper().writeValueAsString(busyoList));
		// pw.append(new ObjectMapper().writeValueAsString(busyoId));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		        String busyoCdNew = request.getParameter("busyoCdNew");
				String busyoNameNew = request.getParameter("busyoNameNew");

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
				String sql = "insert into BUSYO \n" +
						"(BUSYO_ID, BUSYO_NAME) \n" +
						"values \n" +
						"('"+busyoCdNew+"','"+busyoNameNew+"')";

				// エラーが発生するかもしれない処理はtry-catchで囲みます
				// この場合はDBサーバへの接続に失敗する可能性があります
				try (
						// データベースへ接続します
						Connection con = DriverManager.getConnection(url, user, pass);
						// SQLの命令文を実行するための準備をおこないます
						Statement stmt = con.createStatement();
					) {
					// SQLの命令文を実行し、その件数をint型のresultCountに代入します
					int resultCount = stmt.executeUpdate(sql);
				} catch (Exception e) {
					throw new RuntimeException(String.format("検索処理の実施中にエラーが発生しました。詳細：[%s]", e.getMessage()), e);
				}

				// アクセスした人に応答するためのJSONを用意する
				PrintWriter pw = response.getWriter();
				// JSONで出力する
				pw.append(new ObjectMapper().writeValueAsString("ok"));
			}

	// {
	//
	// doGet(request, response);
	//
	// }
}
