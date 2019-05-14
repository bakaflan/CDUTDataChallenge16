package com.shoulaxiao.dao;

import com.shoulaxiao.entity.User;

import java.util.List;

public interface IUserDao {

    /**
     * 新增
     * @param user
     */
    void insert(User user);


    /**
     * 批量新增
     * @param users
     */
    void insertAll(List<User> users);

    /**
     * 删除，根据主键
     * @param id
     */
    void deleteById(String id);


    /**
     * 按条件删除
     * @param criteraUser
     */
    void delete(User criteraUser);

    /**
     * 全部删除
     */
    void deleteAll();

    /**
     * 更新
     * @param user
     */
    void updateById(User user);


    /**
     * 多条更新
     * @param criteriaUser
     * @param user
     */
    void update(User criteriaUser, User user);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    User findById(String id);


    /**
     * 全部查询
     * @return
     */
    List<User> findAll();


    /**
     * 按条件查询
     * @param criteriaUser
     * @param skip
     * @param limit
     * @return
     */
    List<User> find(User criteriaUser, int skip, int limit);

    /**
     * 根据条件查询，修改
     * @param criteriaUser 查询条件
     * @param updateUser 修改的值对象
     * @return
     */

    User findAndModify(User criteriaUser, User updateUser);


    /**
     * 查询出来后 删除
     * @param criteriaUser
     * @return
     */
    User findAndRemove(User criteriaUser);

    /**
     * 计数
     * @param criteriaUser
     * @return
     */
    long count(User criteriaUser);
}
