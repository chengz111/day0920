package com.xinyan.mms.material.dao;
/**
 * 物料管理数据操作DAO接口
 * @author Administrator
 *
 */

import java.util.List;


import com.xinyan.mms.material.pojo.Material;
import com.xinyan.mms.material.pojo.Stock;
import com.xinyan.mms.material.query.MaterialCondition;



public interface MaterialDao {
	
	/**
	 * 查询字典表
	 * @return
	 */
	public List<Object> getAllDictionaryItem();
	
	/**
	 * 添加列表
	 * @param material
	 */
	public void save(Material material, List<Stock> stocks);
	
	
	/**
	 * 查询列表
	 * @return
	 */
	public List<Material> getForlist();
	
	/**
	 * 查询库存列表
	 * @param id
	 * @return
	 */
	public List<Stock> getForStock(String id);
	
	/**
	 * 组合查询物料列表
	 * @param materialCondition
	 * @return
	 */
	public List<Material> getForMaterial(MaterialCondition materialCondition);
	
	/**
	 * 删除列表
	 * @param ids
	 */
	public void delete(String ids);
	
	/**
	 * 查询详细列表
	 * @param id
	 * @return
	 */
	public List<Object> getForMaterials(String id);
	
	/**
	 * 更新列表
	 * @param material
	 * @param stocks
	 */
	public void update(Material material, List<Stock> stocks);
	
	/**
	 * 启动
	 * @param idstr
	 */
	public void start(String idstr);
	
	/**
	 * 停用
	 * @param idstr
	 */
	public void stop(String idstr);
	
	/**
	 * 查询单个物料列表
	 * @param id
	 * @return
	 */
	public Material getMaterial(String id);
}
