package com.itplh.xhs.domain;

import com.itplh.absengine.util.StringUtils;
import com.itplh.xhs.constant.DeviceEnum;
import com.itplh.xhs.domain.desktop.Interaction;
import com.itplh.xhs.constant.JSONPathConstant;
import com.itplh.xhs.constant.URLConstant;
import com.itplh.xhs.domain.desktop.BasicInfo;
import com.itplh.xhs.util.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: tanpenggood
 * @date: 2023-07-21 20:47
 */
@Getter
@Setter
public class UserInfo {

    private String nickname;
    private String fans;
    private String follows;
    private String likeAndCollect;
    private String redId;
    private String desc;
    private String ipLocation;
    private DeviceEnum device; // mobile | desktop
    private List<String> noteDetailUrls = Collections.emptyList();
    private List<Note> notes = Collections.emptyList();

    public void populateInteractInfoForDesktop(String initialStateJson) {
        if (StringUtils.isBlank(initialStateJson)) {
            return;
        }
        // [{"type":"follows","name":"关注","count":"1"},{"type":"fans","name":"粉丝","count":"1288"},{"count":"19753","type":"interaction","name":"获赞与收藏"}]
        List<Interaction> interactions = JSONUtil.extractList(initialStateJson, JSONPathConstant.DESKTOP_HOME_JSON_PATH_INTERACTIONS, Interaction.class);
        for (Interaction interaction : interactions) {
            String type = interaction.getType();
            String count = interaction.getCount();
            if (Objects.equals("fans", type)) {
                this.setFans(count);
            }
            if (Objects.equals("follows", type)) {
                this.setFollows(count);
            }
            if (Objects.equals("interaction", type)) {
                this.setLikeAndCollect(count);
            }
        }
    }

    public void populateBasicInfoForDesktop(String initialStateJson) {
        if (StringUtils.isBlank(initialStateJson)) {
            return;
        }
        BasicInfo basicInfo = JSONUtil.extractBean(initialStateJson, JSONPathConstant.DESKTOP_HOME_JSON_PATH_BASIC_INFO, BasicInfo.class);
        this.setNickname(basicInfo.getNickname());
        this.setDesc(basicInfo.getDesc());
        this.setRedId(basicInfo.getRedId());
        this.setIpLocation(basicInfo.getIpLocation());
    }

    public void populateNotesForDesktop(String initialStateJson) {
        List<Note> notes = JSONUtil.extractList(initialStateJson, JSONPathConstant.DESKTOP_HOME_JSON_PATH_NOTE_CARDS, Note.class);
        this.setNotes(notes);
        this.setNoteDetailUrls(notes.stream().map(n -> URLConstant.PREFIX_DETAIL + n.getNoteId()).collect(Collectors.toList()));
    }

    public boolean isDesktop() {
        return Objects.equals(DeviceEnum.DESKTOP, this.device);
    }

    public boolean isMobile() {
        return Objects.equals(DeviceEnum.MOBILE, this.device);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("nickname:").append(nickname).append("\n");
        builder.append("fans:").append(fans).append(" ").append("follows:").append(follows).append("\n");
        builder.append("likeAndCollect:").append(likeAndCollect).append("\n");
        builder.append("redId:").append(redId).append("\n");
        builder.append("device:").append(device).append(" ").append("ipLocation:").append(ipLocation).append("\n");
        builder.append("desc:").append(desc).append("\n");
        builder.append("noteDetailUrls:").append(noteDetailUrls.size()).append("\n");
        return builder.toString();
    }

}
