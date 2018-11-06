package com.xinyan.mms.material.service;
/**
 * 物料管理业务接口
 * @author Administrator
 *
 */

import java.util.List;


import com.xinyan.mms.material.pojo.Material;
import com.xinyan.mms.material.pojo.Stock;
import com.xinyan.mms.material.query.MaterialCondition;



public interface MaterialService {
	
	/**
	 * 加载页面字典表
	 * @return
	 */
	public List<Object> getDictionaryItem(); 
	
	/**
	 * 添加material
	 * @param material
	 */
	public void addMaterial(Material material, List<Stock> stocks);
	
	/**
	 * 查询物料信息列表
	 * @return
	 */
	public List<Material> getAllMaterial();
	
	/**
	 * 查询库存信息
	 * @param id
	 * @return
	 */
	public List<Stock> getStock(String id);
	
	/**
	 * 组合查询
	 * @param materialCondition
	 * @return
	 */
	public List<Material> getMaterialCondition(MaterialCondition materialCondition);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteMaterial(String ids);
	
	/**
	 * 根据id查询物料信息
	 * @param id
	 * @return
	 */
	public List<Object> getMaterials(String id);
	
	/**
	 * 修改物料信息
	 * @param material
	 * @param stocks
	 */
	public void updateMaterial(Material material, List<Stock> stocks);
	
	/**
	 * 启动
	 * @param idstr
	 */
	public void getStart(String idstr);
	
	/**
	 * 停用
	 * @param idstr
	 */
	public void getStop(String idstr);
	
	/**
	 * 根据id查询单个物料列表
	 * @param id
	 * @return
	 */
	public Material getMaterial(String id);
	
}
