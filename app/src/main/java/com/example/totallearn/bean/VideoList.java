
package com.example.totallearn.bean;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class VideoList {
    private final static String TAG = "VideoList";

    List<VideoEntry> playlist = new ArrayList<VideoEntry>();

    int selected = -1;

    public void select(int position) {
        selected = position;
        Log.d(TAG, "In VideoList:select(),size()=" + size() + ",selected=" + selected);
    }

    public VideoEntry get(int position) {
        if (position < playlist.size() && position >= 0) {
            return playlist.get(position);
        }
        return null;
    }

    public int getSelectedPosition() {
        if (playlist.isEmpty()) {
            selected = -1;
        }
        return selected;
    }

    public VideoEntry getSelectedVideo() {
        Log.d(TAG, "In VideoList:getSelectedVideo(),size()=" + size() + ",selected=" + selected);
        if (selected > -1 && selected < size()) {
            return playlist.get(selected);
        }
        return null;
    }

    public List<VideoEntry> getVideoList() {
        return playlist;
    }

    public void next() {
        if (!playlist.isEmpty()) {
            selected++;
            selected %= playlist.size();
            Log.d(TAG, "In VideoList:next(),size()=" + size() + ",selected=" + selected);
        }

    }

    public void prev() {
        selected--;
        if (selected < 0) {
            selected = playlist.size() - 1;
            Log.d(TAG, "2 In VideoList:prev(),size()=" + size() + ",selected=" + selected);
        }
    }

    public int size() {
        return playlist.size();
    }

    public void clear() {
        playlist.clear();
        selected = -1;
    }

    public void clearWithoutDefault(boolean resetSelected) {
        Log.d(TAG,"In VideoList,clearWithoutDefault(),resetSelected="+resetSelected);
        VideoEntry video = null;
        if (playlist != null && !playlist.isEmpty()) {
            video = playlist.get(playlist.size() - 1);
            Log.d(TAG,"In VideoList,clearWithoutDefault(),video="+video);
        }

        if (resetSelected) {
            clear();
        } else {
            clearForRefresh();
        }
        if (video != null) {
            playlist.add(video);
        }
    }

    public void clearForRefresh() {
        playlist.clear();
    }

    public void add(VideoEntry videoEntry) {
        playlist.add( videoEntry);
    }

    public void addAll(List<VideoEntry> list) {
        playlist.addAll(list);
    }

    public void addOneAtFront(VideoEntry videoEntry) {
        playlist.add(0, videoEntry);
    }

    public void addOneAtPos(VideoEntry videoEntry,int pos) {
        playlist.add(pos,videoEntry);
    }

    public void removeOneAtPos(int pos) {
        VideoEntry videoEntry = playlist.get(pos);
        playlist.remove(videoEntry);
    }

    public void addAllAtFront(List<VideoEntry> videoEntryList) {
        for (int i = videoEntryList.size() - 1; i >= 0; i--) {
            addOneAtFront(videoEntryList.get(i));
        }
    }

    public boolean isLast() {
        return selected == (playlist.size() - 1);
    }

    public boolean isFirst() {
        return selected == 0;
    }

    public boolean isEmpty() {
        return playlist.isEmpty();
    }

    public void setSelectedPosition(int index) {
        selected = index;
        Log.d(TAG, "1 In VideoList:setSelectedPosition(),size()=" + size() + ",selected=" + selected);
    }

}
