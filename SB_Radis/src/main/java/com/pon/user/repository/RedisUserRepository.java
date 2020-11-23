/**
 * 
 */
package com.pon.user.repository;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.pon.user.model.User;

/**
 * @author Sanjeev
 *
 */
@Repository
public class RedisUserRepository {

    private HashOperations<Object, Object, Object> hashOperations;

    private RedisTemplate<Object, Object> redisTemplate;

    public RedisUserRepository(RedisTemplate<Object, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }
    
    public void save(User user){
        hashOperations.put("USER", user.getId(), user);
    }
    public List findAll(){
        return hashOperations.values("USER");
    }

    public User findById(String id){
        return (User) hashOperations.get("USER", id);
    }

    public void update(User user){
        save(user);
    }

    public void delete(String id){
        hashOperations.delete("USER", id);
    }

}

