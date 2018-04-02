package com.nowcoder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class JedisAdapter implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(ToutiaoUtil.class);

    private JedisPool pool = null;
    public static void print(int index,Object obj){
        System.out.println(String.format("%s,%s",index,obj.toString()));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("localhost",6379);
    }
    private  Jedis getJedis(){
        return pool.getResource();
    }
    public long sadd(String key,String value){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.sadd(key,value);
        }catch (Exception e){
            logger.info("发生异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    public long srem(String key,String value){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.srem(key,value);
        }catch (Exception e){
            logger.info("发生异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    public boolean sismenber(String key,String value){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.sismember(key,value);
        }catch (Exception e){
            logger.info("发生异常"+e.getMessage());
            return false;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    public long scard(String key){
        Jedis jedis = null;
        try {
            jedis=pool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            logger.info("发生异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
   /* public static void main(String[] args){
        Jedis jedis=new Jedis();
        jedis.flushAll();
        jedis.set("hello","world");
        jedis.set("hello","worl1");
        print(1,jedis.get("hello"));
        jedis.set("pv","100");
        jedis.incr("pv");
        print(2,jedis.get("pv"));
        String listNmae="list";
        for(int i=0;i<10;i++){
            jedis.lpush(listNmae,"a"+String.valueOf(i));
        }
        print(3,jedis.lrange(listNmae,0,12));
        print(4,jedis.llen(listNmae));
        print(5,jedis.lpop(listNmae));
        String userKey="user12";
        jedis.hset(userKey,"name","licker");
        jedis.hset(userKey,"age","21");
        //jedis.hsetnx not exist to set
        //sadd set的add,不允许重复
        String likeKeys="newlike1";
        for(int i=0;i<10;i++){
            jedis.sadd(likeKeys,String.valueOf(i));
        }
        print(20,jedis.smembers(likeKeys));
                //sinter交集 sunion并集 sdiff你有我没有

        String rankKey="rankKey";
        jedis.zadd(rankKey,15,"jin");
        jedis.zadd(rankKey,12,"dao");
        jedis.zadd(rankKey,17,"lick");
        ///zincrby增加 zcount

    }*/

}
