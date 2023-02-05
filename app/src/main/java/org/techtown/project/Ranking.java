package org.techtown.project;

public class Ranking {                    //랭킹의 클래스
    int itemViewType;
    String count;
    String rankingName;
    String ranking;
    int rankingId;

    public Ranking(String count,String rankingName , String ranking,int itemViewType,int rankingId) {
        this.itemViewType = itemViewType;
        this.count = count;
        this.rankingName = rankingName;
        this.ranking = ranking;
        this.rankingId = rankingId;
    }

    public Ranking(String count,String rankingName , String ranking,int itemViewType) {
        this.itemViewType = itemViewType;
        this.count = count;
        this.rankingName = rankingName;
        this.ranking = ranking;
    }
    public int getRankingId() {
        return rankingId;
    }
    public void setRankingId(int rankingId) {
        this.rankingId = rankingId;
    }

    public int getItemViewType() {
        return itemViewType;
    }
    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }

    public String getRankingname() {
        return rankingName;
    }
    public void setRankingname(String rankingName) {
        this.rankingName = rankingName;
    }
    public String getRanking() {
        return ranking;
    }
    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

}
