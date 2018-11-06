package com.xinyan.mms.material.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xinyan.mms.material.dao.MaterialDao;
import com.xinyan.mms.material.pojo.Category;
import com.xinyan.mms.material.pojo.Material;
import com.xinyan.mms.material.pojo.Stock;
import com.xinyan.mms.material.pojo.Unit;
import com.xinyan.mms.material.pojo.Warehouse;
import com.xinyan.mms.material.query.MaterialCondition;
import com.xinyan.mms.util.JdbcUtil;

/**
 * 物料管理数据操作DAO接口实现类
 * @author Administrator
 *
 */
public class MaterialDaoImpl implements MaterialDao {
	
	private QueryRunner qr = new QueryRunner();
	/**
	 * 查询仓库字典表
	 */
	@Override
	public List<Object> getAllDictionaryItem() {
		
		Connection conn = null;
		List<Object> list = new ArrayList<>();
		conn = JdbcUtil.getConnection();
		String sql1 = "select category_id, category_name from category ";
		String sql2 = "select unit_id, unit_name from unit";
		String sql3 = "select warehouse_id, warehouse_name from warehouse";
		try {
			List<Category> categories = qr.query(conn, sql1, new BeanListHandler<>(Category.class));
			list.add(categories);
			List<Unit> units = qr.query(conn, sql2, new BeanListHandler<>(Unit.class));
			list.add(units);
			List<Warehouse> warehouses = qr.query(conn, sql3, new BeanListHandler<>(Warehouse.class));
			list.add(warehouses);
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
	
	/**
	 * 添加物料信息
	 */
	@Override
	public void save(Material material, List<Stock> stocks) {
		Connection conn = null;
		String sql1 = "insert into material (material_id, category_id, unit_id, material_name, material_spec,"
				+ " material_notes, material_state) values(?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "insert into stock (warehouse_id, material_id, stock_value, stock_number, stock_comments)"
				+ " values (?, ?, ?, ?, ?)";
		conn = JdbcUtil.getConnection();
		try {
			qr.update(conn, sql1, material.getMaterial_id(), material.getCategory_id(), material.getUnit_id(), material.getMaterial_name(), 
					material.getMaterial_spec(), material.getMaterial_notes(), material.getMaterial_state());
			for (Stock stock : stocks) {
				qr.update(conn, sql2, stock.getWarehouse_id(), stock.getMaterial_id(), stock.getStock_value(), stock.getStock_number(), stock.getStock_comments());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
	/**
	 * 查询物料信息列表
	 */
	@Override
	public List<Material> getForlist() {
		Connection conn = null;
		String sql = "select material_id, category_id, unit_id, material_name, material_spec,"
				+ " material_notes, material_state from material";
		conn = JdbcUtil.getConnection();
		try {
			return qr.query(conn, sql, new BeanListHandler<>(Material.class));
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
	/**
	 * 
	 * 查询库存信息
	 * 
	 */
	@Override
	public List<Stock> getForStock(String id) {
		Connection conn = null;
		String sql = "select warehouse_id, material_id, stock_value, stock_number, stock_comments from stock where material_id = ?";
		conn = JdbcUtil.getConnection();
		try {
			return qr.query(conn, sql, new BeanListHandler<>(Stock.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
	/**
	 * 组合查询物料信息
	 */
	@Override
	public List<Material> getForMaterial(MaterialCondition materialCondition) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			List<Object> list = new ArrayList<>();
			StringBuffer sql = new StringBuffer("select material_id, category_id, unit_id, material_name, material_spec, "
					+ " material_notes, material_state from material where 1 = 1 ");
			if(materialCondition != null) {
				if(materialCondition.getId() != null && !materialCondition.getId().equals("")) {
					sql.append(" and material_id = ? ");
					list.add(materialCondition.getId());
				}
				//模糊查询
				if (materialCondition.getName() != null && !materialCondition.getName().equals("")) {
					sql.append(" and material_name like ?");
					list.add("%" + materialCondition.getName() + "%");
				}
				if(materialCondition.getMcategory_id() != 0) {
					sql.append(" and category_id = ? ");
					list.add(materialCondition.getMcategory_id());
				}
			}
			return qr.query(conn, sql.toString(), new BeanListHandler<>(Material.class), list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
	
	/**
	 * 批量删除列表
	 */
	@Override
	public void delete(String ids) {
		Connection conn = null;
		String sql1 = "delete from stock where material_id = ?";
		String sql2 = "delete from material where material_id = ?";
		try {
			conn = JdbcUtil.getConnection();
			
			if(ids != null && !ids.equals("")) {
				String[] idstr = ids.split(",");
				String[] [] array = new String[idstr.length][1];
				for(int i = 0; i < idstr.length; i++) {
						array[i][0] = idstr[i];
				}
				qr.batch(conn, sql1, array);
				qr.batch(conn, sql2, array);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn);
			}
	}
	/**
	 * 查询物料列表和仓库
	 */
	@Override
	public List<Object> getForMaterials(String id) {
		List<Object> lists = new ArrayList<>();
		Connection conn = null;
		String sql1 = "select material_id, category_id, unit_id, material_name, material_spec,"
				+ " material_notes, material_state from material where material_id = ? and material_state = 0 ";
		String sql2 = "select warehouse_id, material_id, stock_value, stock_number, stock_comments from stock where material_id = ?";
		
		conn = JdbcUtil.getConnection();
		
		try {
			Material material = qr.query(conn, sql1, new BeanHandler<>(Material.class), id);
			if(material != null) {
				lists.add(material);
				List<Stock> stocks = qr.query(conn, sql2, new BeanListHandler<>(Stock.class), id);
				lists.add(stocks);
				return lists;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}
	
	/**
	 * 更新物料列表和库存
	 */
	@Override
	public void update(Material material, List<Stock> stocks) {
		Connection conn = null;
		String sql1 = "update material set  category_id = ?, unit_id = ?, material_name = ?, material_spec = ?, "
				+ " material_notes = ?, material_state = ? where material_id = ? ";
		String sql2 = "update stock set stock_value = ?, stock_number = ?, stock_comments = ? where material_id = ? and warehouse_id = ? ";
		conn = JdbcUtil.getConnection();
		
		try {
			qr.update(conn, sql1, material.getCategory_id(), material.getUnit_id(), material.getMaterial_name(), material.getMaterial_spec(), 
						 material.getMaterial_notes(), material.getMaterial_state(), material.getMaterial_id());
			for (Stock stock : stocks) {
				qr.update(conn, sql2, stock.getStock_value(), stock.getStock_number(), stock.getStock_comments(), stock.getMaterial_id(),stock.getWarehouse_id());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	/**
	 * 启动状态
	 */
	@Override
	public void start(String idstr) {
		Connection conn = null;
		String sql = "update material set material_state = 1 where material_id = ? ";
		conn = JdbcUtil.getConnection();
		if(idstr != null && !idstr.equals("")) {
			String[] ids = idstr.split(",");
			for(int i = 0; i < ids.length; i++) {
				try {
					qr.update(conn, sql, ids[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			
		}
		
	}
	/**
	 * 停用状态
	 */
	@Override
	public void stop(String idstr) {
		Connection conn = null;
		String sql = "update material set material_state = 0 where material_id = ? ";
		conn = JdbcUtil.getConnection();
		if(idstr != null && !idstr.equals("")) {
			String[] ids = idstr.split(",");
			for(int i = 0; i < ids.length; i++) {
				try {
					qr.update(conn, sql, ids[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			
		}
		
	}
	/**
	 * 根据id获取单个列表
	 */
	@Override
	public Material getMaterial(String id) {
		Connection conn = null;
		String sql = "select material_id, category_id, unit_id, material_name, material_spec, "
				+ " material_notes, material_state from material where material_id = ? ";
		conn = JdbcUtil.getConnection();
		try {
			return qr.query(conn, sql, new BeanHandler<>(Material.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
