# 大数据案例
## 推荐系统之物品冷启动
新来一个电影（物品），给看过的用户推荐相关电影，这种就是物品冷启动。解决物品冷启动有很多方法，一个最简单的方法就是根据物品特征来推荐相似物品。其步骤为：
1. 预先提取新电影特征
2. 计算新电影和所有其他电影的jaccard距离
3. 选取距离最短的100个作为备选

这里最大的难点在于：电影（物品）集有上百万个，一个一个的计算距离耗时太久。就算是在电影（物品）未上线前预先计算也不行，因为一天可能有上万的电影（物品，如视频）上传。

标准的解决方案是使用支持jaccard距离的LSH(local sensitive hash)。

参考代码中的：loli.data.recommend_cold_start

## 


## 常见的几个公开数据集

### 亚马逊的商品购买（2016）：
http://snap.stanford.edu/data/amazon/productGraph/categoryFiles/

### movielen数据下载：
https://grouplens.org/datasets/movielens/

这里还提供了：
1. 电影（2016）
2. 电影冷启动数据（用户反馈，2018）
3. 电影基于人性的数据（2018）
4. 电影用户对随机set的打分（2019）
5. wiki（2009）
6. 图书（2004）
7. 笑话（Jester，2012）

### kaggle各自比赛的数据（多，但没啥条理性）：
https://www.kaggle.com/datasets