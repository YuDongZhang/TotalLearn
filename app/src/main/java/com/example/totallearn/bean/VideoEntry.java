
package com.example.totallearn.bean;

import android.util.Log;


import java.io.Serializable;

public class VideoEntry implements Serializable {

    protected static final long serialVersionUID = -7046698969052164019L;

    protected int id = 0;

    protected String name = ""; //视频名称

    protected String path = ""; //视频路径

    protected int duration = 0; //视频总长度

    protected int currentPosition = 0; //视频当前播放长度

    protected int lastPosition = 0;//视频上次播放的长度

    protected boolean bInDirFlag = false;
    protected boolean bInSubDirFlag = false;
    protected String strInDirName = "";
    protected int mediaTrackNum = 2;

    /**
     * 视频engineType 是否发生转变
     * see MyVideoView onPrepared
     */
    private boolean engineTypeTrans = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getInDirFlag() {
        return this.bInDirFlag;
    }

    public void setInDirFlag(boolean bInDir) {
        this.bInDirFlag = bInDir;
    }

    public boolean getInSubDirFlag() {
        return this.bInSubDirFlag;
    }

    public void setInSubDirFlag(boolean bInSubDir) {
        this.bInSubDirFlag = bInSubDir;
    }

    public String getInDirName() {
        return this.strInDirName;
    }

    public void setInDirName(String dirName) {
        this.strInDirName = dirName;
    }

    public int getMediaTrackNum() {
        return mediaTrackNum;
    }

    public void setMediaTrackNum(int trackNum) {
        this.mediaTrackNum = trackNum;
    }

    public int getDuration() {
        if (duration == 0) {
            Log.d("VideoEntry", "getDuration()==0. path=" + getPath());
           // duration = MediaDurationUtil.getExternalVideoDuration(getPath());
            Log.d("VideoEntry", "getExternalVideoDuration=" + duration);
        }
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public boolean isEngineTypeTrans() {
        return engineTypeTrans;
    }

    public void setEngineTypeTrans(boolean engineTypeTrans) {
        this.engineTypeTrans = engineTypeTrans;
    }

    @Override
    public String toString() {
        return "<" + path + ">" + ", isEngineTypeTrans:" + isEngineTypeTrans() + ", lastPosition:" + getLastPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof VideoEntry))
            return false;
        VideoEntry v = ((VideoEntry) o);
        return v.path.equals(path);
    }

}
