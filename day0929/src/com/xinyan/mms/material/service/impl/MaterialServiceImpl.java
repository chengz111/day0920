package com.xinyan.mms.material.service.impl;

import java.util.List;

import com.xinyan.mms.material.dao.MaterialDao;
import com.xinyan.mms.material.daoImpl.MaterialDaoImpl;
import com.xinyan.mms.material.pojo.Material;
import com.xinyan.mms.material.pojo.Stock;
import com.xinyan.mms.material.query.MaterialCondition;
import com.xinyan.mms.material.service.MaterialService;
/**
 * 物料管理业务接口实现类
 * 
 * @author Administrator
 *
 */
public class MaterialServiceImpl implements MaterialService {
	private MaterialDao meaterialdao = new MaterialDaoImpl();
	/**
	 * 加载页面字典表
	 */
	@Override
	public List<Object> getDictionaryItem() {
		return meaterialdao.getAllDictionaryItem();
	}
	/**
	 * 添加物料信息
	 */
	@Override
	public void addMaterial(Material material, List<Stock> stocks) {
		meaterialdao.save(material, stocks);
	}
	/**
	 * 查询物料信息
	 */
	@Override
	public List<Material> getAllMaterial() {
		
		return meaterialdao.getForlist();
	}
	/**
	 * 查询库存信息
	 */
	@Override
	public List<Stock> getStock(String id) {	
		return meaterialdao.getForStock(id);
	}
	/**
	 * 组合查询库存信息
	 */
	@Override
	public List<Material> getMaterialCondition(MaterialCondition materialCondition) {
		return meaterialdao.getForMaterial(materialCondition);
		
	}
	/**
	 * 批量删除
	 */
	@Override
	public void deleteMaterial(String ids) {
		meaterialdao.delete(ids);
		
		
	}
	/**
	 * 查询物料信息和库存
	 */
	@Override
	public List<Object> getMaterials(String id) {
		return meaterialdao.getForMaterials(id);
	}
	
	/**
	 * 修改物料信息
	 */
	@Override
	public void updateMaterial(Material material, List<Stock> stocks) {
		meaterialdao.update(material, stocks);
		
	}
	/**
	 * 启动
	 */
	@Override
	public void getStart(String idstr) {
		meaterialdao.start(idstr);
		
		
	}
	/**
	 * 停用
	 */
	@Override
	public void getStop(String idstr) {
		meaterialdao.stop(idstr);
	}
	/**
	 * 查询单个物料列表
	 */
	@Override
	public Material getMaterial(String id) {
		
		return meaterialdao.getMaterial(id);
	}
	
	
	
	
	
	

}
