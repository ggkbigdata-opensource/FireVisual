package com.fire.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.app.common.App;
import com.fire.app.domain.Block;
import com.fire.app.domain.BlockRepository;
import com.fire.app.domain.BsBuildingInfo;
import com.fire.app.domain.BsBuildingInfoRepository;
import com.fire.app.domain.CrCheckReport;
import com.fire.app.domain.CrCheckReportRepository;
import com.fire.app.domain.User;
import com.fire.app.domain.UserRepository;
import com.fire.app.service.UserService;
import com.fire.app.util.ContextHolderUtils;
import com.fire.app.util.EncryptUtils;

/**
 * @createDate 2017年3月28日下午2:50:29
 * @author wangzhiwang
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BsBuildingInfoRepository buildingInfoRepository;
    @Autowired
    private CrCheckReportRepository checkReportRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Override
    public Boolean selectByUsernameAndPassword(String name, String phone) {

        User user = userRepository.findByUsernameAndPassword(name, phone);
        if (user != null) {
            HttpSession session = ContextHolderUtils.getSession();
            session.setAttribute(App.USER_SESSION_KEY, user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean selectByUsernameAndPassword(String name, String phone, String verifCode) {
        //User user = userRepository.findByUsernameAndPassword(name, verifCode);
        
        User user = userRepository.findByPhoneAndPassword(phone, verifCode);
        if (user != null) {

            //String mobile = user.getMobile();
           // if (phone.equals(mobile)) {

                String token = EncryptUtils.encryptByMD5(new Date().toString());

                user.setToken(token);

                userRepository.save(user);

                HttpSession session = ContextHolderUtils.getSession();
                session.setAttribute(App.USER_SESSION_KEY, user);

                return true;
            //}
            //return false;
        }
        return false;
    }

    @Override
    public void updateDate() {

        List<BsBuildingInfo> infos = buildingInfoRepository.findAll();
        for (BsBuildingInfo info : infos) {
            CrCheckReport report = checkReportRepository.findByReportNum(info.getItemNumber().trim());
            if (report != null && !"".equals(report)) {

                String streetAndCommittee = info.getStreetAndCommittee();
                if (streetAndCommittee.contains("/")) {
                    String blockName = streetAndCommittee.split("/")[1].split("社区")[0] + "社区";

                    Block block = blockRepository.findByNameAndStreetId(blockName,info.getStreetId());
                    if (block != null && !"".equals(block)) {

                        report.setBlockId(block.getId());
                        checkReportRepository.save(report);
                        info.setBlockId(block.getId());
                        info.setBuildingTypeBig(report.getBuildingTypeBig());
                        info.setBuildingTypeSmall(report.getBuildingTypeSmall());
                        info.setHeightType(report.getHeightType());
                        buildingInfoRepository.save(info);
                    }
                }
            }

        }
    }

}
