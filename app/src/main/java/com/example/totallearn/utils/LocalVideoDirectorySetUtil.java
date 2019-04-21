
package com.example.totallearn.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.example.totallearn.bean.VideoEntry;
import com.example.totallearn.bean.VideoList;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 此类用于快速的加载视频 并且只是加载带视频的文件的目录
 */

public class LocalVideoDirectorySetUtil {
    private static final String TAG = LocalVideoDirectorySetUtil.class.getSimpleName();
    private static LocalVideoDirectorySetUtil instance;
    private List<String> rootDir = new ArrayList<>();//根目录
    private String defaultDir = "collection";//默认目录

    public Map<String, List<VideoEntry>> map = new HashMap<String, List<VideoEntry>>();//视频list 的hashmap
    private VideoList mVideoListNotInDirectory = new VideoList();//不在目录中
    private VideoList mVideoListInCurrentDirectory = new VideoList();//在当前目录中
    private ArrayList<VideoEntry> dirList = new ArrayList<VideoEntry>();//目录列表
    private ArrayList<VideoEntry> fileList = new ArrayList<VideoEntry>();//文件列表


    private LocalVideoDirectorySetUtil() {
    }

    public static LocalVideoDirectorySetUtil getInstance() {
        if (instance == null) {
            instance = new LocalVideoDirectorySetUtil();
        }
        return instance;
    }

    public VideoList getVideoListInRootDirectory() {
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListInRootDirectory(),mVideoListNotInDirectory=" + mVideoListNotInDirectory);
        if (mVideoListNotInDirectory != null) {
            for (int i = 0; i < mVideoListNotInDirectory.size(); i++) {
                Log.d(TAG, "getVideoListInRootDirectory(),i=" + i + ",video=" + mVideoListNotInDirectory.get(i));
            }
        }
        return mVideoListNotInDirectory;
    }

    public ArrayList<VideoEntry> getDirList() {
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getDirList(),dirList=" + dirList);
        if (dirList != null) {
            for (int i = 0; i < dirList.size(); i++) {
                Log.d(TAG, "getVideoListInRootDirectory(),i=" + i + ",video=" + dirList.get(i));
            }
        }
        return dirList;
    }

    public ArrayList<VideoEntry> getFileList() {
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getDirList(),dirList=" + fileList);
        if (dirList != null) {
            for (int i = 0; i < fileList.size(); i++) {
                Log.d(TAG, "getVideoListInRootDirectory(),i=" + i + ",video=" + fileList.get(i));
            }
        }
        return fileList;
    }

    public VideoList getVideoListInCurDirectory() {
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListInCurDirectory(),mVideoListInCurrentDirectory=" + mVideoListInCurrentDirectory);
        if (mVideoListInCurrentDirectory != null) {
            for (int i = 0; i < mVideoListInCurrentDirectory.size(); i++) {
                Log.d(TAG, "getVideoListInCurDirectory(),i=" + i + ",video=" + mVideoListInCurrentDirectory.get(i));
            }
        }
        return mVideoListInCurrentDirectory;
    }

   /* 为防止报错而注销的


   public void operateFileWithMenu(VideoList playListEntry) {
        LogUtils.d(TAG, "In LocalVideoDirectorySetUtil:opreateFileWithMenu().");
        try {
            if (playListEntry == null) {
                return;
            }
            // 处理有视频的目录文件夹，保证其能够实现
            if (map != null) {
                map.clear();
            }

            //clear
            rootDir.clear();
            //需要展示在根目录下的文件夹集
            dirList.clear();
            mVideoListNotInDirectory.clear();
            fileList.clear();


            List<String> path = ExternalUsbPath.getUsbPath();
            //防止U盘已经插入,打开视频应用,path中存在/dev/null的路径,在RealMainActivity中
            for (String itemPath : path) {
                LogUtils.d(TAG, "operateFileWithMenu:itemPath:" + itemPath);
                if (itemPath.toLowerCase().contains("null")) {
                    ExternalUsbPath.addAllUsbPath(ExternalDevicesState.getUsbVolume(NetVideoApplication.getContext()));
                    path = ExternalUsbPath.getUsbPath();
                    break;
                }
            }
            LogUtils.d(TAG, "In LocalVideoDirectorySetUtil:path=" + path);
            if (path.isEmpty()) {
                return;
            }

            for (String dir : path) {
                rootDir.add(getFileName(dir));
            }

            LogUtils.d(TAG, "In LocalVideoDirectorySetUtil:rootDir=" + rootDir);
            //临时根目录文件集,后面还要做U盘插入先后顺序排序,然后放到rootList中
            List<VideoEntry> tmpRootList = new ArrayList<>();
            //实际跟目录文件集
            List<VideoEntry> rootList = new ArrayList<>();

            List<VideoEntry> list = playListEntry.getVideoList();
            for (VideoEntry videoEntry : list) {
                String videoPath = videoEntry.getPath();
                // 按照路径划分目录，并将目录保存起来
                String[] menus = videoPath.split("/");
                LogUtils.d(TAG, "In LocalVideoDirectorySetUtil:opreateFileWithMenu(),menus=" + menus);
                String dir = menus[menus.length - 2];
                videoEntry.setInDirName(dir);
                if (menus.length == 4) {
                    //文件在根目录下
                    videoEntry.setInDirFlag(false);
                    tmpRootList.add(videoEntry);
                } else {
                    //文件不在根目录下,放到map中 ,key是usbName/文件夹名称
                    String usbName = menus[2];
                    if (map.containsKey(usbName + "/" + dir)) {
                        LogUtils.d(TAG, "In LocalVideoDirectorySetUtil:opreateFileWithMenu(),map.containsKey");
                        map.get(usbName + "/" + dir).add(videoEntry);
                    } else {
                        LogUtils.d(TAG, "In LocalVideoDirectorySetUtil:opreateFileWithMenu(),map not containsKey(menu)");
                        List<VideoEntry> dirlist = new ArrayList<VideoEntry>();
                        dirlist.add(videoEntry);
                        map.put(usbName + "/" + dir, dirlist);
                    }
                }
            }

            //根据usb数量做遍历,将相同usb的文件夹放在一起
            for (String usbName : rootDir) {
                Iterator<String> iterable = map.keySet().iterator();
                if (null == usbName || "".equals(usbName)) continue;
                //排序,先插入的U盘,文件夹在前面显示
                while (iterable.hasNext()) {
                    String key = iterable.next();
                    String[] keys = key.split("/");
                    if (!usbName.equals(keys[0])) continue;
                    VideoEntry videoEntry = map.get(key).get(0);
                    VideoEntry dirVideoEntry = getCopiedVideo(videoEntry);
                    dirVideoEntry.setInDirFlag(true);
                    dirVideoEntry.setInDirName(key);
                    dirList.add(dirVideoEntry);
                }
                //排序,先插入的U盘文件,在前面显示
                for (VideoEntry videoEntry : tmpRootList) {
                    String[] menus = videoEntry.getPath().split("/");
                    String dir = menus[menus.length - 2];
                    if (!usbName.equals(dir)) continue;
                    rootList.add(videoEntry);
                }
            }
            LogUtils.d(TAG, "In LocalVideoDirectorySetUtil:opreateFileWithMenu(),dirlist size:" + dirList.size());
            mVideoListNotInDirectory.addAll(dirList);
            mVideoListNotInDirectory.addAll(rootList);
            fileList.addAll(rootList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //检查本地视频的复制
    public boolean checkLocalVideoIfDuplicated(VideoList videoList, VideoEntry veParam) {//
        if (videoList == null) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:checkLocalVideoIfDuplicated().videoList is null,return");
            return true;
        }
        if (veParam == null) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:checkLocalVideoIfDuplicated().veParam is null,return");
            return true;
        }
        boolean isDuplicated = false;
        for (int i = 0; i < videoList.size(); i++) {
            VideoEntry videoEntry = videoList.get(i);
            if (videoEntry != null) {
                if (veParam.getName().trim().equals(videoEntry.getName().trim())
                        && veParam.getPath().trim().equals(videoEntry.getPath().trim())
                        && veParam.getDuration() == videoEntry.getDuration()) {
                    Log.d(TAG, "In LocalVideoDirectorySetUtil:checkLocalVideoIfDuplicated().videoEntry is Duplicate.");
                    isDuplicated = true;
                    break;
                }
            }
        }
        return isDuplicated;
    }

    //获取视频列表没有复制
    public ArrayList<VideoEntry> getVideoListNotDuplicated(List<VideoEntry> listParam) {
        //LogUtils.d(TAG,"In LocalVideoDirectorySetUtil:checkListDuplicatedVideo().listParam="+listParam);
        ArrayList<VideoEntry> listRet = new ArrayList<VideoEntry>();
        boolean bTemp = false;
        for (int i = 0; i < listParam.size(); i++) {
            VideoEntry videoEntry = listParam.get(i);
            //LogUtils.d(TAG,"In LocalVideoDirectorySetUtil:checkListDuplicatedVideo().videoEntry="+videoEntry);
            bTemp = checkLocalVideoIfDuplicated(mVideoListNotInDirectory, videoEntry);
            if (bTemp) {
                continue;
            } else {
                listRet.add(videoEntry);
            }
        }
        listRet = sortList(listRet);
        Log.d(TAG, "In LocalVideoDirectorySetUtil:checkListDuplicatedVideo().listRet=" + listRet);
        return listRet;
    }

    private String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    public void setInnerDefaultVideo(ArrayList list) {
        if (list == null) {
            Log.d(TAG, "setInnerDefaultVideo(),param list is null.");
            return;
        }
        if (mVideoListNotInDirectory != null) {
            mVideoListNotInDirectory.addAll(getVideoListNotDuplicated(list));
        }
    }

    //复制的视频
    public VideoEntry getCopiedVideo(VideoEntry veParam) {
        if (veParam == null) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:getCopiedVideo().veParam is null,return.");
            return null;
        }
        VideoEntry veRet = null;
        String vePath = veParam.getPath();
        //LogUtils.d(TAG,"In LocalVideoDirectorySetUtil:getCopiedVideo().vePath="+vePath);
        int pos1 = vePath.lastIndexOf('.');
        String strSuffix = vePath.substring(pos1);
        //LogUtils.d(TAG,"In LocalVideoDirectorySetUtil:getCopiedVideo().strSuffix="+strSuffix);
        int pos2 = vePath.lastIndexOf('/');
        String strPathPre = vePath.substring(0, pos2 + 1);
        //LogUtils.d(TAG,"In LocalVideoDirectorySetUtil:getCopiedVideo().strPathPre="+strPathPre);
        String dirName = getVideoDirName(veParam);
        //LogUtils.d(TAG,"In LocalVideoDirectorySetUtil:getCopiedVideo().dirName="+dirName);
        veRet = new VideoEntry();
        veRet.setName(dirName + strSuffix);
        veRet.setPath(strPathPre + veRet.getName());
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getCopiedVideo().veRet=" + veRet);
        return veRet;

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public List<VideoEntry> getVideoListByDirName(String dirName) {
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListByDirName()");
        if (dirName == null) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListByDirName().dirName is null,return.");
            return null;
        }
        if (dirName.trim().isEmpty()) {
            return null;
        }
        List<VideoEntry> videoListRet = null;
        for (Map.Entry<String, List<VideoEntry>> entry : map.entrySet()) {
            //LogUtils.d(TAG,"In LocalVideoDirectorySetUtil:getVideoListByDirName().In for looop:entry.getKey()="+entry.getKey());
            if (dirName.equals(entry.getKey())) {
                Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListByDirName().entry.getKey() equals dirName.");
                videoListRet = entry.getValue();
                break;
            }
        }
//        videoListRet = sortList(videoListRet);
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListByDirName().videoListRet=" + videoListRet);
        return videoListRet;
    }

    public VideoList getCurrentVideoList(VideoEntry veParem) {
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getCurrentVideoList().");
        if (veParem == null) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:getCurrentVideoList(),veParam is null,return.");
            return null;
        }
        String veDir = getVideoDirName(veParem);
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getCurrentVideoList().veDir=" + veDir);

        if (defaultDir.equalsIgnoreCase(veDir) || rootDir.contains(veDir) || "".equals(veDir)) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:getCurrentVideoList(),in if RootDir.");
            return mVideoListNotInDirectory;
        }
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getCurrentVideoList(),in else SubDir.");
        return mVideoListInCurrentDirectory;
    }

    public void resolveSubDirectoryVideoList(String dirName) {
        Log.d(TAG, "In LocalVideoDirectorySetUtil:resolveSubDirectoryVideoList().dirName=" + dirName);
        List<VideoEntry> videoListRet = getVideoListByDirName(dirName);
        if (mVideoListInCurrentDirectory != null && videoListRet != null) {
            mVideoListInCurrentDirectory.clear();
            mVideoListInCurrentDirectory.addAll(videoListRet);
            //mVideoListInCurrentDirectory.addOneAtFront(getCopiedVideo(videoListRet.get(0)));
        }
    }

    public String getVideoDirName(VideoEntry veParam) {
        if (veParam == null) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListByDirName(),veParam is null,return.");
            return null;
        }
        String dirName = "";
        boolean bBreak = false;
        for (Map.Entry<String, List<VideoEntry>> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                VideoEntry videoEntry = entry.getValue().get(i);
                if (videoEntry.getName().equals(veParam.getName())
                        && videoEntry.getPath().equals(veParam.getPath())
                        && videoEntry.getDuration() == veParam.getDuration()) {
                    dirName = entry.getKey();
                    bBreak = true;
                    break;
                }
            }
            if (bBreak) {
                break;
            }
        }
        Log.d(TAG, "In LocalVideoDirectorySetUtil:getVideoListByDirName().dirName=" + dirName);
        return dirName;
    }

    public int getCurVideoPosInVideoList(VideoList videoList, VideoEntry videoEntry) {
        int posRet = -1;
        if (videoList == null || videoEntry == null) {
            Log.d(TAG, "In LocalVideoDirectorySetUtil:getCurVideoPosInVideoList().videoList=" + videoList + ",videoEntry=" + videoEntry);
            return posRet;
        }
        for (int i = 0; i < videoList.size(); i++) {
            VideoEntry veTemp = videoList.get(i);
            if (veTemp != null) {
                if (veTemp.getName().trim().equals(videoEntry.getName().trim())
                        && veTemp.getPath().trim().equals(videoEntry.getPath().trim())
                        && veTemp.getDuration() == videoEntry.getDuration()) {
                    posRet = i;
                    break;
                }
            }
        }
        return posRet;
    }

    public ArrayList<VideoEntry> sortList(List<VideoEntry> list) {
        if (list == null) {
            return null;
        }
        ArrayList<String> strList = new ArrayList<String>();
        ArrayList<VideoEntry> videoListRet = new ArrayList<VideoEntry>();
        int i = 0, j = 0;
        for (i = 0; i < list.size(); i++) {
            strList.add(list.get(i).getName());
        }
        Comparator<Object> comparator = Collator.getInstance(java.util.Locale.CHINA);
        Collections.sort(strList, comparator);
        for (i = 0; i < strList.size(); i++) {
            Log.d(TAG, "sortList(),strList(" + i + ")=" + strList.get(i));
        }
        for (i = 0; i < strList.size(); i++) {
            String strName = strList.get(i).trim();
            for (j = 0; j < list.size(); j++) {
                VideoEntry videoEntry = list.get(j);
                if (videoEntry.getName().trim().equals(strName)) {
                    videoListRet.add(videoEntry);
                    break;
                }
            }
        }
        return videoListRet;
    }

    public ArrayList<VideoEntry> sortDirectory(List<VideoEntry> list) {
        if (list == null) {
            return null;
        }
        Log.d(TAG, "sortDirectory(),list.size()=" + list.size());
        ArrayList<VideoEntry> listDir = new ArrayList<VideoEntry>();
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            VideoEntry videoEntry = list.get(i);
            if (videoEntry.getInDirFlag()) {
                Log.d(TAG, "sortDirectory(),strList(" + i + ")=" + list.get(i));
                listDir.add(videoEntry);
            }
        }
        return sortList(listDir);
    }

}
