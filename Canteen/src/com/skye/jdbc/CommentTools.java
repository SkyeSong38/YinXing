package com.skye.jdbc;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.skye.utils.ConnectionFactory;
import com.skye.utils.JsonUtils;
import com.skye.utils.OtherUtils;

public class CommentTools {

	private CommentTools() {

	}

	// 添加评论
	public static boolean addComment(String string) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		int i = 0;
		String sql = "insert into comment (c_id , c_content , c_followId , c_userName) values (?,?,?,?);";

		Comment comment = JsonUtils.getCommentByJson(string);
		try {
			comment.setId(OtherUtils.getId("C"));

			pst = conn.prepareStatement(sql);

			pst.setString(1, comment.getId());
			pst.setString(2, comment.getContent());
			pst.setString(3, comment.getFollowId());
			pst.setString(4, comment.getUserName());

			pst.executeUpdate();
			i = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, conn);
		}

		return i == 0 ? false : true;
	}

	// 通过followId查询6个评论，按照时间顺序排列
	public static String getCommentByDate(String followId, int order) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<Comment> comments = new ArrayList<Comment>();

		String sql = "select c_id , c_content , c_userName , c_good , c_date from comment where c_followId = ? order by c_date desc limit ?,6;";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, followId);
			pst.setInt(2, order);

			rs = pst.executeQuery();

			while (rs.next()) {
				Comment comment = new Comment();

				comment.setId(rs.getString("c_id").toString());
				comment.setUserName(rs.getString("c_userName").toString());
				comment.setContent(rs.getString("c_content").toString());
				comment.setGood(rs.getInt("c_good"));
				comment.setDate(rs.getString("c_date").toString());
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(rs, pst, conn);
		}
		return JsonUtils.getJsonComment(comments);
	}

	// 删除评论
	public static boolean deleteComment(String id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;

		int i = 0;
		String sql = "delete from comment where c_id = ?;";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.executeUpdate();
			i = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(null, pst, conn);
		}
		return i == 0 ? false : true;
	}

	// 点赞
	public static boolean addGood(String id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pst = null;

		int i = 0;

		String sql = "update comment set c_good = c_good +1 where c_id =  ?;";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.executeUpdate();
			i = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(null, pst, conn);
		}

		return i == 0 ? false : true;
	}
}
